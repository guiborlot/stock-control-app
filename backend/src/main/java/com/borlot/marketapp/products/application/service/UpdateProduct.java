package com.borlot.marketapp.products.application.service;

import com.borlot.marketapp.products.domain.Product;
import com.borlot.marketapp.products.application.port.in.ProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateProduct {
    private ProductRepository repository;

    public Product execute(Product product){
        return repository.save(product);
    }
}
