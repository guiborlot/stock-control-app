package com.borlot.marketapp.products.application.service;

import com.borlot.marketapp.products.domain.Product;
import com.borlot.marketapp.products.domain.exceptions.ProductNotExistsException;
import com.borlot.marketapp.products.application.port.in.ProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindProduct {
    private ProductRepository repository;

    public Product execute(Long id){
        Product productFromDB = repository.findById(id);
        if(productFromDB == null){
            throw new ProductNotExistsException();
        }

        return productFromDB;
    }
    
}
