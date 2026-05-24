package com.catalystone.iv.jdbc.repository;

import com.catalystone.iv.IvApplicationTests;
import com.catalystone.iv.jdbc.model.CategoryCount;
import com.catalystone.iv.jdbc.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;

class JdbcProductRepoTest extends IvApplicationTests {

    @Autowired
    private JdbcProductRepository productRepo;

    //This one is for reference
    @Test
    void productById() {
        var actual = productRepo.byId(1L);
        assertThat(actual).isEqualTo(new Product(1L, "Laptop", 10.99, "ELECTRONICS"));
    }

    //This one is for reference
    @Test
    void allNames() {
        assertThat(productRepo.allProducts().stream().map(Product::getName).toList())
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
        assertThat(productRepo.categoryCount()).containsExactlyInAnyOrder(
            new CategoryCount("ELECTRONICS", 4),
            new CategoryCount("FOOD", 4)
        );
    }

    //Use price in descending order as second parameter of sorting
    @Test
    void allProductsIdsInAlphabeticalOrder() {
        assertThat(productRepo.allIdsAlphabeticalOrder()).containsExactly(8L,7L,5L,3L,1L,6L,2L,4L);
    }

    @Test
    void getIdPriceMappingInAscOrder() {
        assertThat(productRepo.getIdPriceMappingInAscOrder(List.of(5L, 2L, 3L, 8L)))
                .containsExactly(entry(2L, 20.49)
                        ,entry(3L, 15.75)
                        ,entry(5L, 29.78)
                        ,entry(8L, 150.00));

    }
}