package com.borlot.marketapp.config;

import com.borlot.marketapp.domain.models.Product;
import com.borlot.marketapp.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public void run(String... args) throws Exception {
        Product product1 = new Product(null, "Computer", "Electronics", 1200.0, 5);
        Product product2 = new Product(null, "Mouse", "Electronics", 200.0, 7);
        Product product3 = new Product(null, "Keyboard", "Electronics", 100.0, 15);
        Product product4 = new Product(null, "Ferrari", "Cars", 5000000.0, 1);

        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4));
    }
}
