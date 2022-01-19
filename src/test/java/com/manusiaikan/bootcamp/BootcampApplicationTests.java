package com.manusiaikan.bootcamp;

import com.manusiaikan.bootcamp.model.Department;
import com.manusiaikan.bootcamp.repository.DepartmentJdbc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

@SpringBootTest
class BootcampApplicationTests {

	@Autowired
	private DepartmentJdbc departmentJdbc;

	@Test
	void contextLoads() {
	}

	@Test
	void testFindIdDepartment() {
		try {
			Department dep = this.departmentJdbc.findDepartmentById(1);
			System.out.println(dep.getName());
		} catch (EmptyResultDataAccessException erda) {
			System.out.println("datanya kosong!");
		}
	}

	@Test
	void testListDepartment(){
		List<Department> list = this.departmentJdbc.getListNamedJdbcTemplate();
		list.forEach(data->{
			System.out.println(data.getName());
		});
	}

	@Test
	void testInsertDataDepartment() {
		Department department = new Department(null, "IT", "IT");
		department = this.departmentJdbc.insertDepartment(department);
		System.out.println(department.toString());
	}

	@Test
	void testUpdateDataDepartment() {
		Department department = new Department(2, "IT", "Mengurus Tekologi");
		department = this.departmentJdbc.updateDepartment(department);

		Department checkDepartment = this.departmentJdbc.findDepartmentById(2);

		Assertions.assertEquals(department.getName(),checkDepartment.getName());
		Assertions.assertEquals(department.getDescription(),checkDepartment.getDescription());
		System.out.println(department.toString());
	}
}
