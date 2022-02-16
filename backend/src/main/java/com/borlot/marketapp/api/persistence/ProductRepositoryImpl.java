package com.borlot.marketapp.api.persistence;

import com.borlot.marketapp.domain.entities.Product;
import com.borlot.marketapp.domain.repository.ProductRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private final ProductJpaRepository repository;

    public ProductRepositoryImpl(final ProductJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
