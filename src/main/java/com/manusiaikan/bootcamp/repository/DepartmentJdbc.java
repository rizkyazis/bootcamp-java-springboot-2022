package com.manusiaikan.bootcamp.repository;

import com.manusiaikan.bootcamp.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;


import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DepartmentJdbc {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public List<Department> getListDepartmentJdbcTemplate() {
        String sql = "SELECT department_id, name, description FROM public.department ORDER BY department_id";
        return jdbcTemplate.query(sql, (result, rowNum) -> {
            Department department = new Department();
            department.setDescription(result.getString("description"));
            department.setName(result.getString("name"));
            department.setId(result.getInt("department_id"));
            return department;
        });
    }


    public List<Department> getListDepartmentPS() {
        String sql = "SELECT department_id, name, description FROM public.department ORDER BY department_id";
        List<Department> departmentList = null;

        try (PreparedStatement data = dataSource.getConnection().prepareStatement(sql)) {
            departmentList = new ArrayList<>();
            ResultSet result = data.executeQuery();
            while (result.next()) {
                Department department = new Department();
                department.setDescription(result.getString("description"));
                department.setName(result.getString("name"));
                department.setId(result.getInt("department_id"));
                departmentList.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList;
    }

    public List<Department> getListNamedJdbcTemplate() {
        MapSqlParameterSource map = new MapSqlParameterSource();
        return this.namedJdbcTemplate.query(
                "SELECT * FROM department ORDER BY department_id",
                new RowMapper<Department>() {
                    @Override
                    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Department(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3));
                    }
                });
    }

    public Department findDepartmentById(int id) throws EmptyResultDataAccessException {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        return this.namedJdbcTemplate.queryForObject("SELECT * FROM department WHERE department_id=:id", map, new RowMapper<Department>() {
            @Override
            public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Department(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        });
    }

    @Transactional
    public Integer insertDepartment(Department value) throws SQLException{
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name",value.getName());
        map.addValue("description",value.getDescription());

        String sql = "INSERT INTO department (name,description) VALUES (:name,:description)";
        this.namedJdbcTemplate.update(sql,map,keyHolder);
        return (Integer) keyHolder.getKeys().get("department_id");
    }

    @Transactional
    public Boolean updateDepartment(Department value) throws SQLException{
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id",value.getId());
        map.addValue("name",value.getName());
        map.addValue("description",value.getDescription());

        String sql = "UPDATE department SET name=:name, description=:description WHERE department_id = :id";
        this.namedJdbcTemplate.update(sql,map);
        return true;
    }

    @Transactional
    public Boolean deleteDepartment(Integer id) throws SQLException{
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id",id);
        String sql = "DELETE FROM department WHERE department_id =:id";
        this.namedJdbcTemplate.update(sql,map);
        return true;
    }
}


