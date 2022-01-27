package com.manusiaikan.bootcamp.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class Product {
    private Integer id;

    @NotEmpty
    @Length(min = 4)
    private String name;

    @NotEmpty
    @Length(min = 4)
    private String description;

    @NotEmpty
    @Length(min = 4)
    private Double price;

    public Product(Integer id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}


