package com.manusiaikan.bootcamp;

import com.manusiaikan.bootcamp.model.Category;
import com.manusiaikan.bootcamp.model.Department;
import com.manusiaikan.bootcamp.repository.CategoryRepo;
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

	@Autowired
	private CategoryRepo categoryRepo;

	@Test
	void contextLoads() {
	}
}
