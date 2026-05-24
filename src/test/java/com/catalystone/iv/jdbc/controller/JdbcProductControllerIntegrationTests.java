package com.catalystone.iv.jdbc.controller;

import com.catalystone.iv.IvApplicationTests;
import com.catalystone.iv.jdbc.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class JdbcProductControllerIntegrationTests extends IvApplicationTests{

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // This one is for reference
    @Test
    void getProductById() throws Exception {
        var responseBody = mockMvc.perform(get("/product/1").accept(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(responseBody).isNotNull().contains("{\"id\":1,\"name\":\"Laptop\",\"price\":10.99,\"category\":\"ELECTRONICS\"}");
    }

    /**
     * Implement method to get category count in following order
     * @throws Exception
     */
    @Test
    void getCategoryCount() throws Exception {
        var responseBody = mockMvc.perform(get("/product/category-count"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(responseBody).contains("{\"ELECTRONICS\":4,\"FOOD\":4}");
    }

    /**
     * Implement method to create a new product
     * @throws Exception
     */
    @Test
    void createProduct() throws Exception {
        var product = new Product(9L, "Microphone", 100.12, "ELECTRONICS");
        var jsonProduct = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                        .contentType(APPLICATION_JSON)
                        .content(jsonProduct))
                .andExpect(MockMvcResultMatchers.status().isOk());

      var createdP = mockMvc.perform(get("/product/9").accept(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        var createdProduct = objectMapper.readValue(createdP, Product.class);

        assertThat(createdProduct.getId()).isEqualTo(9L);
    }

    /**
     *Implement the method to get product by its name;
     */
    @Test
    void getProductWithName() throws Exception {

        var responseBody = mockMvc.perform(get("/product?name=Apple"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        var responseList =  objectMapper.readValue(responseBody, new TypeReference<List<Product>>() {});
        assertThat(responseList).hasSize(2).containsExactlyInAnyOrder(
               new Product(8L, "Apple", 150, "ELECTRONICS"),
                new Product(7L, "Apple", 10.89, "FOOD"));
    }

}
