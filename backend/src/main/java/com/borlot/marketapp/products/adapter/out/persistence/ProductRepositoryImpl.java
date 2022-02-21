package com.borlot.marketapp.products.adapter.out.persistence;

import com.borlot.marketapp.products.domain.Product;
import com.borlot.marketapp.products.application.port.in.ProductRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private final ProductJpaRepository repository;
    private final ProductMapper productMapper;

    public ProductRepositoryImpl(final ProductJpaRepository repository, ProductMapper productMapper) {
        this.repository = repository;
        this.productMapper = productMapper;
    }



    @Override
    public Product findById(Long id) {
        return productMapper.toDomainEntity(repository.findById(id).orElse(null));
    }

    @Override
    public List<Product> findAll() {
        return productMapper.toDomainEntityList(repository.findAll());
    }

    @Override
    public Product save(Product product) {
        return productMapper.toDomainEntity(repository.save(productMapper.mapToJpaEntity(product)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
