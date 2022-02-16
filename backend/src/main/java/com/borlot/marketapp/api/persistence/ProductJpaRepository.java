package com.borlot.marketapp.api.persistence;

import com.borlot.marketapp.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long>{
    
}
