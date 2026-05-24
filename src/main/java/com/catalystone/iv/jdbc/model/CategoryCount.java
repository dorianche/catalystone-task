package com.catalystone.iv.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryCount {
    String category;
    int count;
}