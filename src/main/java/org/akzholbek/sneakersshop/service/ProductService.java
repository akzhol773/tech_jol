package org.akzholbek.sneakersshop.service;

import org.akzholbek.sneakersshop.dtos.AdvertisementPageDto;

import java.util.List;


public interface ProductService {
    List<AdvertisementPageDto> getAllSneakers();
}
