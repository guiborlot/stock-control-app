package com.borlot.marketapp;

import com.borlot.marketapp.domain.models.Product;
import com.borlot.marketapp.domain.repository.ProductRepository;
import com.borlot.marketapp.domain.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class TestProductService {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @TestConfiguration
    static class ProductServiceTestConfiguration{
        @Bean
        public ProductService productService(){
            return new ProductService();
        }
    }

    @Before
    public void setup(){
        List<Product> products = new ArrayList<>();
        products.add(new Product(null, "Computer", "Electronics", 1200.0, 5));
        products.add(new Product(null, "Mouse", "Electronics", 1100.0, 7));
        products.add(new Product(null, "Ferrari", "Cars", 5000000000.0, 1));

        Product product = new Product(0L, "Mouse", "Electronics", 1100.0, 7);

        Mockito.when(productRepository.findAll()).thenReturn(products);
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

    }

    @Test
    public void productServiceTestSaveProduct(){
        Product product = new Product(null, "Mouse", "Electronics", 1100.0, 7);

        assertEquals(productService.saveProduct(product), product);

    }

    @Test
    public void productTestServiceListProduct(){
        List<Product> products = new ArrayList<>();
        products.add(new Product(null, "Computer", "Electronics", 1200.0, 5));
        products.add(new Product(null, "Mouse", "Electronics", 1100.0, 7));
        products.add(new Product(null, "Ferrari", "Cars", 5000000000.0, 1));

        List<Product> expected = productService.listProducts();

        assertEquals(expected, products);
    }

    @Test
    public void productTestServiceFindProduct(){
        Product product = new Product(0L, "Mouse", "Electronics", 1100.0, 7);

        Product expected = productService.findProduct(0L).orElseThrow();

        assertEquals(expected, product);
    }

    @Test
    public void productTestServiceDeleteProduct(){
        Product expected = new Product(0L, "Mouse", "Electronics", 1100.0, 7);

        productService.deleteProduct(expected.getId());

        Mockito.verify(productRepository).deleteById(expected.getId());
    }
}
