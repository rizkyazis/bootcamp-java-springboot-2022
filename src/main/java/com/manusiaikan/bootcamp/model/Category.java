package com.manusiaikan.bootcamp.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class Category {
    private Integer category_id;

    @NotEmpty
    @Length(min = 4)
    private String name;

    @NotEmpty
    @Length(min = 4)
    private String description;

    @NotEmpty
    private Department department;

    public Category(){
    }

    public Category(String name, String description, Department department) {
        this.name = name;
        this.description = description;
        this.department = department;
    }

    public Category(Integer category_id, String name, String description, Department department) {
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.department = department;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", department=" + department +
                '}';
    }
}
