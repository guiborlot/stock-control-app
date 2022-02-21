package com.borlot.marketapp.products.application.port.in;

import java.util.List;

import com.borlot.marketapp.products.domain.Product;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findAll();
    Product save(Product product);
    void deleteById(Long id);
}
