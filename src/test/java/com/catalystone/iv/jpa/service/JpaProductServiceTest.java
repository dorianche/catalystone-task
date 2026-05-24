package com.catalystone.iv.jpa.service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.catalystone.iv.IvApplicationTests;
import com.catalystone.iv.jpa.model.JpaProduct;

class JpaProductServiceTest extends IvApplicationTests {

    @Autowired
    JpaProductService productService;

    @Test void allUniqueNames() {
        var allProducts = productService.getAllProducts();

        var allUniqueNames = doSomething(allProducts);

        assertThat(allUniqueNames).containsExactlyInAnyOrder(
            "Laptop", "Mouse", "Keyboard", "Apple", "Yoghurt", "Bread", "Milk"
        );
    }

    private List<String> doSomething(List<JpaProduct> allProducts) {
        return allProducts.stream()
                .map(JpaProduct::getName)
                .distinct()
                .toList();
    }


    @Test
    void getIdPriceMappingInAscOrder() {
        assertThat(productService.getIdPriceMappingInAscOrder(List.of(5L, 2L, 3L, 8L)))
                .containsExactly(entry(2L, 20.49)
                        ,entry(3L, 15.75)
                        ,entry(5L, 29.78)
                        ,entry(8L, 150.00));

    }

}