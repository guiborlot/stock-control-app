package com.borlot.marketapp.domain.services;

import com.borlot.marketapp.domain.models.Product;
import com.borlot.marketapp.domain.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> listProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> findProduct(Long productID) {
        return productRepository.findById(productID);
    }

    @Transactional
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    @Transactional
    public boolean deleteProduct(Long productID){
        boolean productExists = (findProduct(productID).isPresent()) ? true : false;

        productRepository.deleteById(productID);

        return productExists;
    }




}
