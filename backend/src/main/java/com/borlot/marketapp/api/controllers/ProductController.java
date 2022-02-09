package com.borlot.marketapp.api.controllers;

import com.borlot.marketapp.domain.models.Product;
import com.borlot.marketapp.domain.repository.ProductRepository;
import com.borlot.marketapp.domain.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService service;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public List<Product> listProducts() {
        return service
                .listProducts();
    }

    @GetMapping("/{productID}")
    public ResponseEntity<Product> findProduct(@PathVariable Long productID) {
        return service.findProduct(productID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@Valid @RequestBody Product product) {

        return service.saveProduct(product);
    }

    @PutMapping("/{productID}")
    public ResponseEntity<Product> update(@PathVariable Long productID, @Valid @RequestBody Product product){

        return service
                .updateProduct(productID, product);
    }

    @DeleteMapping("/{productID}")
    public ResponseEntity<Void> delete(@PathVariable Long productID){
        return service.deleteProduct(productID);
    }


}
