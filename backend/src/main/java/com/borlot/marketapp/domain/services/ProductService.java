package com.borlot.marketapp.domain.services;

import com.borlot.marketapp.domain.models.Product;
import com.borlot.marketapp.domain.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    @Transactional
    public void delete(Long productID){
        productRepository.deleteById(productID);
    }
}
