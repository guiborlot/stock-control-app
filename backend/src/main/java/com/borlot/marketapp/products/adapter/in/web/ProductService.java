package com.borlot.marketapp.products.adapter.in.web;

import com.borlot.marketapp.products.adapter.in.web.ProductDTO;
import com.borlot.marketapp.products.domain.Product;

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
