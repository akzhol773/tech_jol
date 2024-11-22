package org.akzholbek.sneakersshop.repository;

import org.akzholbek.sneakersshop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findCartItemByCartIdAndProductId(Long cartId, Long productId);
}