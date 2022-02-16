package com.borlot.marketapp.domain.services;

import com.borlot.marketapp.domain.entities.Product;
import com.borlot.marketapp.domain.repository.ProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateProduct {
    private ProductRepository repository;

    public Product execute(Product product){
        return repository.save(product);
    }
}
