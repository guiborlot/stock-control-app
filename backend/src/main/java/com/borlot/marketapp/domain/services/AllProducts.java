package com.borlot.marketapp.domain.services;

import java.util.List;

import com.borlot.marketapp.domain.entities.Product;
import com.borlot.marketapp.domain.repository.ProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AllProducts {
    private ProductRepository repository;

    public List<Product> execute(){
        return repository.findAll();
    }
}
