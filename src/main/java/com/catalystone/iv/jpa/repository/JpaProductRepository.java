package com.catalystone.iv.jpa.repository;

import com.catalystone.iv.jpa.model.CategoryCount;
import com.catalystone.iv.jpa.model.JpaProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaProductRepository extends JpaRepository<JpaProduct, Long> {

    @Query(value = "write your query", nativeQuery = true)
    String nameById(Long productId);

    @Query(value = "write your query", nativeQuery = true)
    Long idByName(String productName);

    @Query(value = "write your query", nativeQuery = true)
    JpaProduct productWithXHighestPrice(int parameter);

    @Query(value = "write your query", nativeQuery = true)
    List<CategoryCount> categoryCount();

    @Query(value = "write your query", nativeQuery = true)
    List<Long> allIdsAlphabeticalOrder();

}
