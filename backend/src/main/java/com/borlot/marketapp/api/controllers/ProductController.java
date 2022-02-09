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
@CrossOrigin(origins = "http://localhost:4201")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping()
    public List<Product> listProducts() {
        return service
                .listProducts();
    }

    @GetMapping("/{productID}")
    public ResponseEntity<Product> findProduct(@PathVariable Long productID) {
        return service.findProduct(productID).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@Valid @RequestBody Product product) {

        return service.saveProduct(product);
    }

    @PutMapping("/{productID}")
    public ResponseEntity<Product> update(@PathVariable Long productID, @Valid @RequestBody Product product){
        if(service.findProduct(productID).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        product = service.saveProduct(product);

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{productID}")
    public ResponseEntity<Void> delete(@PathVariable Long productID){
        if(service.findProduct(productID).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        service.deleteProduct(productID);
        return ResponseEntity.noContent().build();
    }


}
