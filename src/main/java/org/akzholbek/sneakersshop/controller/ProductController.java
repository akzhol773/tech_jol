package org.akzholbek.sneakersshop.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.akzholbek.sneakersshop.dtos.AdvertisementPageDto;
import org.akzholbek.sneakersshop.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {

    ProductService productService;

    @Operation(
            summary = "GET ALL SNEAKERS",
            description = "Returns list of visible sneakers",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Equipment list received"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping("/products")
    public List<AdvertisementPageDto> getAllVisibleEquipments() {
        return productService.getAllSneakers();
    }


}
