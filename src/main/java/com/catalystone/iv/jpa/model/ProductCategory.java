package com.catalystone.iv.jpa.model;

public enum ProductCategory {

    ELECTRONICS("ELECTRONICS"),
    FOOD("FOOD");

    private final String label;

    ProductCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
