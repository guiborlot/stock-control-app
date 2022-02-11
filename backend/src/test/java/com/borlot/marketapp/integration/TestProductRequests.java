package com.borlot.marketapp.integration;

import com.borlot.marketapp.domain.models.Product;
import com.borlot.marketapp.domain.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void before(){
        addProductOnRepository();
    }

    @Test
    public void shouldReturnOk_WhenPerformGet() throws Exception {
        Product product = productRepository.findById(1L).orElse(null);
        Product expected = newProduct();
        expected.setId(1L);

        mockMvc.perform(get("/products")).andExpect(status().isOk());
        assertThat(product).isEqualTo(expected);
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
        Product product = productRepository.findById(1L).orElse(null);

        MvcResult result = mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Product(null, "Keyboard", "Electronics", 1350.0, 15))))
                .andExpect(status().isOk())
                .andReturn();


        assertThat(objectMapper.writeValueAsString(product))
                .isNotEqualTo(result.getResponse().getContentAsString());

    }

    @Test
    public void shouldReturnNotFound_WhenPerformPutInvalidId() throws Exception {
        mockMvc.perform(put("/products/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Product(null, "Keyboard", "Electronics", 1350.0, 15))))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnBadRequest_WhenPerformPutInvalidField() throws Exception {
        mockMvc.perform(put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Product(null, null, "Electronics", 1350.0, 15))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnNoContent_WhenPerformDelete() throws Exception {
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnNotFound_WhenPerformDeleteInvalidId() throws Exception {
        mockMvc.perform(delete("/products/150"))
                .andExpect(status().isNotFound());
    }



    private Product newProduct(){
         return new Product(1L, "Mouse", "Electronics", 1100.0, 7);
    }

    private void addProductOnRepository(){
        productRepository.saveAndFlush(newProduct());
    }

}
