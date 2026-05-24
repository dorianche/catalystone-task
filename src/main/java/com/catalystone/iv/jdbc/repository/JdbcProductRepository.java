package com.catalystone.iv.jdbc.repository;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.catalystone.iv.jdbc.model.CategoryCount;
import com.catalystone.iv.jdbc.model.Product;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@Repository
@AllArgsConstructor
public class JdbcProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Product byId(long id) {
        var sql = "Select * from PRODUCT where id = :id";
        return jdbcTemplate.queryForObject(sql, Map.of("id", id), (rs, rowNum) -> product(rs));
    }

    public List<Product> allProducts() {
        var sql = "select * from PRODUCT";
        return jdbcTemplate.query(sql, new HashMap<>(), (rs, rowNum) -> product(rs));
    }

    public String nameById(long id) {
        return null;
    }

    public Long idByName(String name) {
        return null;
    }

    public List<CategoryCount> categoryCount() {
    return jdbcTemplate.query(
        """
        SELECT category, COUNT(*) AS count
        FROM PRODUCT
        GROUP BY category
        ORDER BY category
            """,
            (rs, rowNum) -> new CategoryCount(
                rs.getString("category"),
                rs.getInt("count")
            )
        );
    }

    public List<Long> allIdsAlphabeticalOrder() {
        return Collections.emptyList();
    }


    public Product productWithXHighestPrice(int parameter) {
        return null;
    }

    //returns Map<Id, Price> with Id sorted in ASC order
    public Map<Long, Double> getIdPriceMappingInAscOrder(List<Long> ids) {
        return null;
    }

    @SneakyThrows
    private Product product(ResultSet rs) {
        return new Product(rs.getLong("id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("category"));
    }

    public Product create(Product product) {
        var sql = "INSERT INTO PRODUCT (id, name, price, category) VALUES (:id, :name, :price, :category)";
        jdbcTemplate.update(sql, Map.of(
            "id", product.getId(),
            "name", product.getName(),
            "price", product.getPrice(),
            "category", product.getCategory()
        ));
            return product;
    }

    public List<Product> getProductsByName(String name) {
        var sql = """
                SELECT id, name, price, category
                FROM PRODUCT
                WHERE name = :name
                """;

        return jdbcTemplate.query(
                sql,
                Map.of("name", name),
                (rs, rowNum) -> product(rs)
        );
    }

}
