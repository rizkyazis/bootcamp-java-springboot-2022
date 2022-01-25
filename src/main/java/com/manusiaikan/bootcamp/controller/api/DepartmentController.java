package com.manusiaikan.bootcamp.controller.api;


import com.manusiaikan.bootcamp.controller.HomeAction;
import com.manusiaikan.bootcamp.model.Category;
import com.manusiaikan.bootcamp.model.Department;
import com.manusiaikan.bootcamp.repository.DepartmentJdbc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.relational.core.sql.SQL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentJdbc departmentJdbc;

    @GetMapping("")
    public List<Department> list() {
        return this.departmentJdbc.getListNamedJdbcTemplate();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Integer id) throws EmptyResultDataAccessException {
        Department dept = this.departmentJdbc.findDepartmentById(id);
        return ResponseEntity.ok(dept);
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Department dept, BindingResult result) {
        Map<String, Object> hasil = new HashMap<>();
        try {
            if (result.hasErrors()) {
                hasil.put("id", null);
                hasil.put("status", result.getAllErrors());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hasil);
            } else {
                hasil.put("id", departmentJdbc.insertDepartment(dept));
                hasil.put("status", "Success added new Department");
                return ResponseEntity.ok(hasil);
            }
        } catch (SQLException e) {
            hasil.put("id", null);
            hasil.put("status", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hasil);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody Department department, BindingResult result) {
        Map<String, Object> hasil = new HashMap<>();
        try {
            if (result.hasErrors()) {
                hasil.put("id", null);
                hasil.put("status", result.getAllErrors());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hasil);
            } else {
                hasil.put("id", this.departmentJdbc.updateDepartment(department));
                hasil.put("status", "Success updated Department");
                return ResponseEntity.ok(hasil);
            }
        } catch (SQLException e) {
            hasil.put("id", null);
            hasil.put("status", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hasil);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Department department) throws SQLException {
        Map<String, Object> hasil = new HashMap<>();
        try {
            this.departmentJdbc.findDepartmentById(department.getId());
            this.departmentJdbc.deleteDepartment(department.getId());
            hasil.put("status", "Success Deleted Department");
            return ResponseEntity.ok(hasil);
        } catch (EmptyResultDataAccessException e) {
            hasil.put("status", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hasil);
        }
    }

}
