package com.borlot.marketapp.domain.services;

import com.borlot.marketapp.domain.entities.Product;
import com.borlot.marketapp.domain.exceptions.ProductExistsException;
import com.borlot.marketapp.domain.repository.ProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaveNewProduct {
    
    private ProductRepository repository;

    public Product execute(Product product){
        Product productFromDB = repository.findById(product.getId());
        if(productFromDB != null){
            throw new ProductExistsException();
        }

        return repository.save(product);
    }


}
