package com.borlot.marketapp.controllers;


import com.borlot.marketapp.api.controllers.ProductController;
import com.borlot.marketapp.domain.models.Product;
import com.borlot.marketapp.domain.services.ProductService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductController.class)
public class TestProductController {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void setup() {
        standaloneSetup(productController);
    }

    @Test
    public void shouldReturnOK_WhenListProduct() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(null, "Computer", "Electronics", 1200.0, 5));
        products.add(new Product(null, "Mouse", "Electronics", 1100.0, 7));
        products.add(new Product(null, "Ferrari", "Cars", 5000000000.0, 1));

        when(productService.listProducts()).thenReturn(products);

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/products")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnOK_WhenFindProduct() {
        Optional<Product> product = Optional.of(new Product(1L, "Mouse", "Electronics", 1100.0, 7));

        when(this.productService.findProduct(1L))
                .thenReturn(product);

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/products/{productID}", 1L)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnNotFound_WhenFindProduct() {
        when(this.productService.findProduct(1L))
                .thenReturn(Optional.empty());

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/products/{productID}", 1L)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void shouldReturnCreated_WhenAddProduct() {
        Product product = new Product(1L, "Mouse", "Electronics", 1100.0, 7);

        when(this.productService.saveProduct(product))
                .thenReturn(product);

        given()
                .body(product)
                .contentType(ContentType.JSON)
                .when()
                .post("/products")
                .then()
                .statusCode(HttpStatus.CREATED.value());

    }

    @Test
    public void shouldReturnBadRequest_WhenAddProduct() {
        Product product = new Product(1L, null, "Electronics", 1100.0, 8);

        when(this.productService.saveProduct(product))
                .thenReturn(null);

        given()
                .body(product)
                .contentType(ContentType.JSON)
                .when()
                .post("/products")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
