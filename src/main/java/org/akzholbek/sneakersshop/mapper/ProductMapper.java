package org.akzholbek.sneakersshop.mapper;

import org.akzholbek.sneakersshop.dtos.AdvertisementPageDto;
import org.akzholbek.sneakersshop.entity.Image;
import org.akzholbek.sneakersshop.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public static AdvertisementPageDto toDto(Product product) {
        return new AdvertisementPageDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImages().stream()
                        .map(Image::getImageUrl)
                        .toList()
        );
    }

    public static List<AdvertisementPageDto> toDtoList(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toDto)
                .toList();
    }
}
