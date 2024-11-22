package org.akzholbek.sneakersshop.dtos;

import java.util.List;

public record AdvertisementPageDto (
        Long id,
        String name,
        String description,
        Double price,
        List<String> imageUrls
){
}
