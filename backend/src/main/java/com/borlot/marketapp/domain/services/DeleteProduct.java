package com.borlot.marketapp.domain.services;

import com.borlot.marketapp.domain.entities.Product;
import com.borlot.marketapp.domain.exceptions.ProductNotExistsException;
import com.borlot.marketapp.domain.repository.ProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteProduct {
    private ProductRepository repository;

    public void execute(Long id){
        Product productFromDB = repository.findById(id);

        if(productFromDB == null){
            throw new ProductNotExistsException();
        }

        repository.deleteById(id);
    }
}
