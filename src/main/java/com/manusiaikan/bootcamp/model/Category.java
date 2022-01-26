package com.manusiaikan.bootcamp.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class Category {
    private Integer id;

    @NotEmpty
    @Length(min = 4)
    private String name;

    @NotEmpty
    @Length(min = 4)
    private String description;

    private Department department;

    public Category(){
    }

    public Category(String name, String description, Department department) {
        this.name = name;
        this.description = description;
        this.department = department;
    }

    public Category(Integer id, String name, String description, Department department) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", department=" + department +
                '}';
    }
}
