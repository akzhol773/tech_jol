package org.akzholbek.sneakersshop.service.serviceImpl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.akzholbek.sneakersshop.dtos.AdvertisementPageDto;
import org.akzholbek.sneakersshop.entity.Product;
import org.akzholbek.sneakersshop.mapper.ProductMapper;
import org.akzholbek.sneakersshop.repository.ProductRepository;
import org.akzholbek.sneakersshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    @Override
    public List<AdvertisementPageDto> getAllSneakers() {
        List<Product> products = productRepository.findAll();
        return ProductMapper.toDtoList(products);
    }
}
