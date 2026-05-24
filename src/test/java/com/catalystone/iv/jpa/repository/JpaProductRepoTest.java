package com.catalystone.iv.jpa.repository;

import com.catalystone.iv.IvApplicationTests;
import com.catalystone.iv.jpa.model.CategoryCount;
import com.catalystone.iv.jpa.model.JpaProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;

class JpaProductRepoTest extends IvApplicationTests {

    @Autowired
    private JpaProductRepository productRepo;

    //This one is for reference
    @Test
    void productById() {
        var actual = productRepo.findById(1L);
        assertThat(actual).isEqualTo(Optional.of(new JpaProduct(1L, "Laptop", 10.99, "ELECTRONICS")));
    }

    //This one is for reference
    @Test
    void allNames() {
        assertThat(productRepo.findAll().stream().map(JpaProduct::getName).toList())
                .containsExactlyInAnyOrder(
                        "Laptop", "Mouse", "Keyboard", "Apple", "Yoghurt", "Bread", "Milk", "Apple"
                );
    }

    @Test
    void nameById() {
        assertThat(productRepo.nameById(4L)).isEqualTo("Yoghurt");
    }

    @Test
    void idByName() {
        assertThat(productRepo.idByName("Mouse")).isEqualTo(2L);
    }

    @Test
    void productWithXHighestPrice() {
        assertThat(productRepo.productWithXHighestPrice(4)).extracting("id").isEqualTo(4L);
        assertThat(productRepo.productWithXHighestPrice(3)).extracting("id").isEqualTo(5L);
    }

    @Test
    void categoryCount() {
        assertThat(productRepo.categoryCount()).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(
                        new CategoryCount("ELECTRONICS", 4),
                        new CategoryCount("FOOD", 4)
                );
    }

    //Use price in descending order as second parameter of sorting
    @Test
    void allProductsIdsInAlphabeticalOrder() {
        assertThat(productRepo.allIdsAlphabeticalOrder()).containsExactly(8L, 7L, 5L, 3L, 1L, 6L, 2L, 4L);
    }

}