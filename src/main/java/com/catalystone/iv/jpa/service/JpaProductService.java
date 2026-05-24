package com.catalystone.iv.jpa.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.catalystone.iv.jpa.model.CategoryCount;
import com.catalystone.iv.jpa.model.JpaProduct;
import com.catalystone.iv.jpa.repository.JpaProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JpaProductService {

    private final JpaProductRepository productRepo;

    public JpaProduct getById(long id) {
        return productRepo.findById(id).orElse(null);
    }

    public List<JpaProduct> getAllProducts() {
        return productRepo.findAll();
    }

    public Map<Long, Double> getIdPriceMappingInAscOrder(List<Long> ids) {
     return null;
    }

    public Map<String, Integer> getCategoryCount() {
        return productRepo.categoryCount()
            .stream()
            .collect(Collectors.toMap(
                CategoryCount::getCategory,
                cc -> (int) cc.getCount()
));
    }
}
