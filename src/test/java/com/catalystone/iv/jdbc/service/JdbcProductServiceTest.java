package com.catalystone.iv.jdbc.service;

import com.catalystone.iv.IvApplicationTests;
import com.catalystone.iv.jdbc.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JdbcProductServiceTest extends IvApplicationTests {

    @Autowired
    JdbcProductService productService;

    @Test void allUniqueNames() {
        var allProducts = productService.getAllProducts();

        var allUniqueNames = doSomething(allProducts);

        assertThat(allUniqueNames).containsExactlyInAnyOrder(
            "Laptop", "Mouse", "Keyboard", "Apple", "Yoghurt", "Bread", "Milk"
        );
    }

    private List<String> doSomething(List<Product> allProducts) {
        return null;
    }

}