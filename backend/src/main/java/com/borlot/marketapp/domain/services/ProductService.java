package com.borlot.marketapp.domain.services;

import com.borlot.marketapp.api.dto.ProductDTO;
import com.borlot.marketapp.domain.models.Product;
import com.borlot.marketapp.domain.repository.ProductRepository;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ProductService {
    
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

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
    public void deleteProduct(Long productID){
        productRepository.deleteById(productID);
    }

    public Product fromDTO(ProductDTO productDTO){
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getCategory(), productDTO.getPrice(), productDTO.getQuantity());
    }

    public ProductDTO toDTO(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    public boolean isProductValid(ProductDTO productDTO){
        return productDTO.getName() != null && productDTO.getCategory() != null && productDTO.getPrice() != null && productDTO.getQuantity() != null;
    }


}
