package com.manusiaikan.bootcamp.model;

public class Category {
    private Integer category_id;
    private Integer department_id;
    private String name;
    private String description;
    private Department department;

    public Category(){
    }

    public Category(Integer category_id, Integer department_id, String name, String description) {
        this.category_id = category_id;
        this.department_id = department_id;
        this.name = name;
        this.description = description;
    }

    public Category(Integer category_id, Integer department_id, String name, String description, Department department) {
        this.category_id = category_id;
        this.department_id = department_id;
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

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
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
                ", department_id=" + department_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", department=" + department +
                '}';
    }
}
