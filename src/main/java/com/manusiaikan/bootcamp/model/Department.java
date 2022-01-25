package com.manusiaikan.bootcamp.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class Department {
    private Integer id;

    @NotEmpty
    @Length(min = 4)
    private String name;

    @NotEmpty
    @Length(min = 4)
    private String description;

    public Department() {
        this.id = null;
    }

    public Department(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
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
}
