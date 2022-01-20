package com.manusiaikan.bootcamp.repository;

import com.manusiaikan.bootcamp.model.Category;
import com.manusiaikan.bootcamp.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CategoryRepo {

    @Autowired
    private NamedParameterJdbcTemplate namedTemplate;

    @Autowired
    private DepartmentJdbc departmentJdbc;

    @Transactional
    public List<Category> list() {
        return this.namedTemplate.query(
                "SELECT * FROM category ORDER BY category_id", new RowMapper<Category>() {
                    @Override
                    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Department dept = departmentJdbc.findDepartmentById(rs.getInt(2));
                        return new Category(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),dept);
                    }
                }
        );
    }

    @Transactional
    public Category findById(int id) throws EmptyResultDataAccessException {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        return this.namedTemplate.queryForObject("SELECT * FROM category WHERE category_id=:id ORDER BY category_id", map, new RowMapper<Category>() {
                    @Override
                    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Department dept = departmentJdbc.findDepartmentById(rs.getInt(2));
                        return new Category(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),dept);
                    }
                }
        );
    }

    @Transactional
    public Category insert(Category value) throws SQLException{
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("department_id",value.getDepartment_id());
        map.addValue("name",value.getName());
        map.addValue("description",value.getDescription());

        String sql = "INSERT INTO category (department_id,name,description) VALUES (:department_id,:name,:description)";
        this.namedTemplate.update(sql,map);
        Department dept = departmentJdbc.findDepartmentById(value.getDepartment_id());
        value.setDepartment(dept);
        return value;
    }

    @Transactional
    public Category update(Category value) throws SQLException{
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("department_id",value.getDepartment_id());
        map.addValue("name",value.getName());
        map.addValue("description",value.getDescription());
        map.addValue("category_id",value.getCategory_id());

        String sql = "UPDATE category SET department_id=:department_id, name=:name, description=:description WHERE category_id=:category_id";
        this.namedTemplate.update(sql,map);

        Department dept = departmentJdbc.findDepartmentById(value.getDepartment_id());

        value.setDepartment(dept);

        return value;
    }

    @Transactional
    public void delete(int id) throws SQLException{
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("category_id",id);

        String sql ="DELETE FROM category WHERE category_id=:category_id";
        this.namedTemplate.update(sql,map);

    }

    @Transactional
    public List<Category> listWithDepartment() {
        return this.namedTemplate.query(
                "SELECT category.category_id as category_id, category.department_id as department_id, category.name as category_name, category.description as category_description, department.name as department_name, department.description as department_description FROM public.category INNER JOIN department ON category.department_id=department.department_id;", new RowMapper<Category>() {
                    @Override
                    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Department dept = new Department(rs.getInt(2),rs.getString(5), rs.getString(6));
                        return new Category(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),dept);
                    }
                }
        );
    }
}
