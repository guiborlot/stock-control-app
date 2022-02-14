package com.borlot.marketapp.integration;

import com.borlot.marketapp.domain.models.Product;
import com.borlot.marketapp.domain.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestProductRequests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void before() {
        addProductOnRepository();
    }

    @AfterEach
    public void after() {
        productRepository.deleteAll();
        productRepository.flush();
    }

    @Test
    public void shouldReturnOk_WhenPerformGet() throws Exception {
        Product product = getProductFromRepository();

        mockMvc.perform(get("/products/", product.getId())).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOk_WhenPerformGetAll() throws Exception {
        List<Product> products = productRepository.findAll();

        MvcResult mvcResult = mockMvc.perform(get("/products")).andExpect(status().isOk()).andReturn();

        assertThat(objectMapper.writeValueAsString(products))
                .isEqualTo(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void shouldReturnCreated_WhenPerformPost() throws Exception {
        Product product = newProduct();

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnBadRequest_WhenPerformPost() throws Exception {
        Product product = newProduct();
        product.setName(null);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldReturnOk_WhenPerformPut() throws Exception {
        Product product = getProductFromRepository();

        mockMvc.perform(put("/products/{productID}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldReturnNotFound_WhenPerformPutInvalidId() throws Exception {
        mockMvc.perform(put("/products/150")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduct())))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnBadRequest_WhenPerformPutInvalidField() throws Exception {
        Product product = getProductFromRepository();
        product.setName(null);

        mockMvc.perform(put("/products/{productID}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnNoContent_WhenPerformDelete() throws Exception {
        Product product = getProductFromRepository();
        mockMvc.perform(delete("/products/{productID}", product.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnNotFound_WhenPerformDeleteInvalidId() throws Exception {
        mockMvc.perform(delete("/products/150"))
                .andExpect(status().isNotFound());
    }


    private Product newProduct() {
        return new Product(null, "Mouse", "Electronics", 1100.0, 7);
    }

    private void addProductOnRepository() {
        productRepository.save(newProduct());
    }

    private Product getProductFromRepository() {
        List<Product> products = productRepository.findAll();
        return products.get(0);
    }

}
