package com.borlot.marketapp.api.services;

import com.borlot.marketapp.api.dto.ProductDTO;
import com.borlot.marketapp.domain.entities.Product;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ProductService {
    public ProductDTO toDTO(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName().getValue())
                .category(product.getCategory().getValue())
                .price(product.getPrice().getValue())
                .quantity(product.getQuantity().getValue())
                .build();
    }
}
