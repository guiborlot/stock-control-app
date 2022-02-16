package com.borlot.marketapp.api.controllers;

import com.borlot.marketapp.api.dto.ProductDTO;
import com.borlot.marketapp.api.services.ProductService;
import com.borlot.marketapp.domain.entities.Product;
import com.borlot.marketapp.domain.repository.ProductRepository;
import com.borlot.marketapp.domain.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4201")
public class ProductController {

    private final ProductRepository repository;

    private final ProductService service;

    public ProductController(ProductRepository repository, ProductService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping()
    public List<ProductDTO> listProducts() {
        AllProducts allProducts = new AllProducts(repository);
        List<Product> products = allProducts.execute();
        Stream<ProductDTO> productDTO = products.stream().map(ProductDTO::new);

        return productDTO.collect(Collectors.toList());
    }

    @GetMapping("/{productID}")
    public ResponseEntity<ProductDTO> findProduct(@PathVariable Long productID) {
        FindProduct findProduct = new FindProduct(repository);
        Product product = findProduct.execute(productID);
        if(product == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(service.toDTO(product));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@Valid @RequestBody ProductDTO productDTO) {

        SaveNewProduct saveNewProduct = new SaveNewProduct(repository);
        Product product = saveNewProduct.execute(productDTO.toProduct());
        return new ProductDTO(product);

    }

    @PutMapping("/{productID}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long productID, @Valid @RequestBody ProductDTO productDTO){

        FindProduct findProduct = new FindProduct(repository);
        UpdateProduct updateProduct = new UpdateProduct(repository);
        Product product = findProduct.execute(productID);

        product = productDTO.toProduct();
        product.setId(productID);

        updateProduct.execute(product);

        return ResponseEntity.ok(service.toDTO(product));
    }

    @DeleteMapping("/{productID}")
    public ResponseEntity<Void> delete(@PathVariable Long productID){
        DeleteProduct deleteProduct = new DeleteProduct(repository);
        deleteProduct.execute(productID);
        return ResponseEntity.noContent().build();
    }


}
