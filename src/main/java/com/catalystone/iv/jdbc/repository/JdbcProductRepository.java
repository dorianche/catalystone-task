package com.catalystone.iv.jdbc.repository;

import com.catalystone.iv.jdbc.model.CategoryCount;
import com.catalystone.iv.jdbc.model.Product;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return null;
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

}
