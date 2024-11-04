package com.centre.rediscache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centre.rediscache.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
