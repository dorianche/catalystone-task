package com.catalystone.iv.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.catalystone.iv.jpa.model.CategoryCount;
import com.catalystone.iv.jpa.model.JpaProduct;

public interface JpaProductRepository extends JpaRepository<JpaProduct, Long> {

    @Query(value = "write your query", nativeQuery = true)
    String nameById(Long productId);

    @Query(value = "write your query", nativeQuery = true)
    Long idByName(String productName);

    @Query(value = "write your query", nativeQuery = true)
    JpaProduct productWithXHighestPrice(int parameter);

    @Query("""
        SELECT new com.catalystone.iv.jpa.model.CategoryCount(p.category, COUNT(p))
        FROM JpaProduct p
        GROUP BY p.category
        """)
    List<CategoryCount> categoryCount();

    @Query(value = "write your query", nativeQuery = true)
    List<Long> allIdsAlphabeticalOrder();

    List<JpaProduct> getProductsByName(String name);

}
