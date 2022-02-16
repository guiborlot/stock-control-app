package com.borlot.marketapp.api.controllers;

import com.borlot.marketapp.api.dto.ProductDTO;
import com.borlot.marketapp.domain.models.Product;
import com.borlot.marketapp.domain.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4201")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping()
    public List<ProductDTO> listProducts() {
        List<Product> products = service.listProducts();

        return products.stream().map(service::toDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/{productID}")
    public ResponseEntity<ProductDTO> findProduct(@PathVariable Long productID) {
        Product product = service.findProduct(productID).orElse(null);
        if(product == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(service.toDTO(product));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO) {

        if(service.isProductValid(productDTO)){
            Product product = service.fromDTO(productDTO);
            service.saveProduct(product);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
            return ResponseEntity.created(uri).body(productDTO);
        }
        return ResponseEntity.badRequest().build();

    }

    @PutMapping("/{productID}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long productID, @Valid @RequestBody ProductDTO productDTO){

        Product product = service.findProduct(productID).orElse(null);

        if(product == null){
            return ResponseEntity.notFound().build();
        } else if(!service.isProductValid(productDTO)){
            return ResponseEntity.badRequest().build();
        }

        product.setId(productID);
        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());

        product = service.saveProduct(product);

        return ResponseEntity.ok(service.toDTO(product));
    }

    @DeleteMapping("/{productID}")
    public ResponseEntity<Void> delete(@PathVariable Long productID){
        if(!service.findProduct(productID).isPresent()){
            return ResponseEntity.notFound().build();
        }

        service.deleteProduct(productID);
        return ResponseEntity.noContent().build();
    }


}
