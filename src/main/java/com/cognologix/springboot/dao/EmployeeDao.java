package com.cognologix.springboot.dao;
import com.cognologix.springboot.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
}
