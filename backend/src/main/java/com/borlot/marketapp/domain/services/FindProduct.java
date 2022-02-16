package com.borlot.marketapp.domain.services;

import com.borlot.marketapp.domain.entities.Product;
import com.borlot.marketapp.domain.exceptions.ProductNotExistsException;
import com.borlot.marketapp.domain.repository.ProductRepository;

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
