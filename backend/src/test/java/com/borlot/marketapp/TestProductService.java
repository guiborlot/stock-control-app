package com.borlot.marketapp;

import com.borlot.marketapp.domain.models.Product;
import com.borlot.marketapp.domain.repository.ProductRepository;
import com.borlot.marketapp.domain.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestProductService {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Test
    public void productServiceTestSaveProduct(){
        Product product = new Product(null, "Mouse", "Electronics", 1100.0, 7);

        productService.saveProduct(product);

        verify(productRepository).save(product);

    }

    @Test
    public void productTestServiceListProduct(){
        List<Product> products = new ArrayList<>();
        products.add(new Product(null, "Computer", "Electronics", 1200.0, 5));
        products.add(new Product(null, "Mouse", "Electronics", 1100.0, 7));
        products.add(new Product(null, "Ferrari", "Cars", 5000000000.0, 1));

        when(productRepository.findAll()).thenReturn(products);
        List<Product> expected = productService.listProducts();

        assertEquals(expected, products);
    }

    @Test
    public void productTestServiceFindProduct(){
        Optional<Product> product = Optional.of(new Product(0L, "Mouse", "Electronics", 1100.0, 7));

        when(productRepository.findById(0L)).thenReturn(product);
        Optional<Product> expected = productService.findProduct(0L);

        assertEquals(expected, product);
    }

    @Test
    public void productTestServiceDeleteProduct(){
        Product expected = new Product(0L, "Mouse", "Electronics", 1100.0, 7);

        productService.deleteProduct(expected.getId());

        verify(productRepository).deleteById(expected.getId());
    }
}
