package com.borlot.marketapp.config;

import com.borlot.marketapp.domain.models.Product;
import com.borlot.marketapp.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//@Configuration
//public class Instantiation implements CommandLineRunner {
//    @Autowired
//    private ProductRepository productRepository;
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        Product product1 = new Product(null, "Tv", "eletronicos", "1200.00");
//        Product product2 = new Product(null, "Mouse", "perifericos", "100.00");
//        Product product3 = new Product(null, "PC", "eletronicos", "1500.00");
//
//        productRepository.saveAll(Arrays.asList(product1, product2, product3));
//    }
//}
