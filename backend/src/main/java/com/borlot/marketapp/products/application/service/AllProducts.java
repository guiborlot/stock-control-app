package com.borlot.marketapp.products.application.service;

import java.util.List;

import com.borlot.marketapp.products.domain.Product;
import com.borlot.marketapp.products.application.port.in.ProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AllProducts {
    private ProductRepository repository;

    public List<Product> execute(){
        return repository.findAll();
    }
}
