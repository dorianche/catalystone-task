package com.catalystone.iv.jpa.service;

import com.catalystone.iv.jpa.model.JpaProduct;
import com.catalystone.iv.jpa.repository.JpaProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
}
