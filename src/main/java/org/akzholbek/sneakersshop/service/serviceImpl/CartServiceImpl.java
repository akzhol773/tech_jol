package org.akzholbek.sneakersshop.service.serviceImpl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.akzholbek.sneakersshop.dtos.CartRequestDto;
import org.akzholbek.sneakersshop.dtos.CartResponseDto;
import org.akzholbek.sneakersshop.entity.Cart;
import org.akzholbek.sneakersshop.entity.CartItem;
import org.akzholbek.sneakersshop.entity.Product;
import org.akzholbek.sneakersshop.entity.User;
import org.akzholbek.sneakersshop.exception.CartNotFoundException;
import org.akzholbek.sneakersshop.exception.InsufficientStockException;
import org.akzholbek.sneakersshop.exception.ItemNotFoundException;
import org.akzholbek.sneakersshop.exception.ProductNotFoundException;
import org.akzholbek.sneakersshop.exception.UserNotFoundException;
import org.akzholbek.sneakersshop.mapper.CartMapper;
import org.akzholbek.sneakersshop.repository.CartItemRepository;
import org.akzholbek.sneakersshop.repository.CartRepository;
import org.akzholbek.sneakersshop.repository.ProductRepository;
import org.akzholbek.sneakersshop.repository.UserRepository;
import org.akzholbek.sneakersshop.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartServiceImpl implements CartService {
    CartRepository cartRepository;
    UserRepository userRepository;
    CartItemRepository cartItemRepository;
    ProductRepository productRepository;

    @Override
    @Transactional
    public String addToCart(CartRequestDto cartRequest) {

        Product product = productRepository.findById(cartRequest.productId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + cartRequest.productId()));

        User user = userRepository.findById(cartRequest.userId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + cartRequest.userId()));

        if (cartRequest.quantity() > product.getStockQuantity()) {
            throw new InsufficientStockException(
                    "Requested quantity " + cartRequest.quantity() +
                            " exceeds available stock of " + product.getStockQuantity()
            );
        }


        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return newCart;
                });

        CartItem cartItem = cartItemRepository.findCartItemByCartIdAndProductId(cart.getId(), cartRequest.productId())
                .orElseGet(() -> {
                    CartItem newCartItem = new CartItem();
                    newCartItem.setCart(cart);
                    newCartItem.setProduct(product);
                    newCartItem.setQuantity(0);
                    return newCartItem;
                });


        cartItem.setQuantity(cartItem.getQuantity() + cartRequest.quantity());
        updateCartTotals(cart);

        cartItemRepository.save(cartItem);
        cartRepository.save(cart);

        return "Item added to the cart";
    }


    @Override
    public List<CartResponseDto> getCartItems(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user ID: " + userId));
        CartResponseDto cartResponse = CartMapper.toCartResponseDto(cart);
        return List.of(cartResponse);
    }

    @Override
    @Transactional
    public String removeItemFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user ID: " + userId));

        CartItem itemToRemove = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException("Item not found in the cart for product ID: " + productId));

        cart.getCartItems().remove(itemToRemove);
        updateCartTotals(cart);
        cartRepository.save(cart);
        return "Item deleted from the cart";
    }




    @Transactional
    public void updateCartTotals(Cart cart) {
        double totalAmount = cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        cart.setTotalAmount(totalAmount);
        cart.setTaxAmount(totalAmount * 0.5);
        cartRepository.save(cart);
    }
}



