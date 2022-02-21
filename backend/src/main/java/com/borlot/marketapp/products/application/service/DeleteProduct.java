package com.borlot.marketapp.products.application.service;

import com.borlot.marketapp.products.domain.Product;
import com.borlot.marketapp.products.domain.exceptions.ProductNotExistsException;
import com.borlot.marketapp.products.application.port.in.ProductRepository;

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
