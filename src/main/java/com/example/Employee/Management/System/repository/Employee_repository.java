package com.example.Employee.Management.System.repository;

import com.example.Employee.Management.System.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Employee_repository extends JpaRepository<Employee,Long> {
}
