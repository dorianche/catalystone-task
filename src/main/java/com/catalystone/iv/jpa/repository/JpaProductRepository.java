package com.catalystone.iv.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.catalystone.iv.jpa.model.CategoryCount;
import com.catalystone.iv.jpa.model.JpaProduct;

public interface JpaProductRepository extends JpaRepository<JpaProduct, Long> {

    @Query(value = "SELECT name FROM PRODUCT WHERE id = :productId", nativeQuery = true)
    String nameById(Long productId);

    @Query(value = "SELECT id FROM PRODUCT WHERE name = :productName", nativeQuery = true)
    Long idByName(String productName);

    @Query(value = """
        SELECT id, name, price, category
        FROM PRODUCT
        ORDER BY price DESC
        LIMIT 1 OFFSET :#{#parameter - 1}
        """, nativeQuery = true)
    JpaProduct productWithXHighestPrice(int parameter);

    @Query("""
        SELECT new com.catalystone.iv.jpa.model.CategoryCount(p.category, COUNT(p))
        FROM JpaProduct p
        GROUP BY p.category
        """)
    List<CategoryCount> categoryCount();

    @Query(value = """
        SELECT id
        FROM PRODUCT
        ORDER BY name ASC, price DESC
        """, nativeQuery = true)
    List<Long> allIdsAlphabeticalOrder();

    List<JpaProduct> getProductsByName(String name);

}
