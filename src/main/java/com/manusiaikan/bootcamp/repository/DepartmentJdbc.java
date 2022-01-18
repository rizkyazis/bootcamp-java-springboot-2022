package com.manusiaikan.bootcamp.repository;

import com.manusiaikan.bootcamp.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentJdbc {
    @Autowired
    private DataSource dataSource;

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
}


