package com.borlot.marketapp.domain.services;

import com.borlot.marketapp.domain.models.Product;
import com.borlot.marketapp.domain.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> listProducts(){
        return productRepository.findAll();
    }

    public ResponseEntity<Product> findProduct(Long productID) {
        return productRepository.findById(productID).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    @Transactional
    public ResponseEntity<Void> deleteProduct(Long productID){
        if(!productRepository.existsById(productID)) {
            return ResponseEntity.notFound().build();
        }

        productRepository.deleteById(productID);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    public ResponseEntity<Product> updateProduct(Long productID, Product product){

        if(!productRepository.existsById(productID)) {
            return ResponseEntity.notFound().build();
        }
        product = saveProduct(product);

        return ResponseEntity.ok(product);
    }




}
