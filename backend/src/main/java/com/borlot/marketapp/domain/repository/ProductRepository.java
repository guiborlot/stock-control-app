package com.borlot.marketapp.domain.repository;

import java.util.List;

import com.borlot.marketapp.domain.entities.Product;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findAll();
    Product save(Product product);
    void deleteById(Long id);
}
