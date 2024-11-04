package com.centre.rediscache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centre.rediscache.model.Employee;
import com.centre.rediscache.response.ResponseEmployee;
import com.centre.rediscache.service.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@PostMapping("/createUser")
	public ResponseEntity<ResponseEmployee> createUser(@RequestBody Employee employee) {
		ResponseEmployee employeeResponse;
		try {
			employeeResponse = employeeService.createEmployee(employee);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
	}

	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<ResponseEmployee> getEmployee(@PathVariable Long id) {
		ResponseEmployee responseEmployee;
		try {
			responseEmployee = employeeService.getEmployeeById(id);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return new ResponseEntity<>(responseEmployee, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public String deleteById(@PathVariable Long id)
	{
	  return employeeService.deleteById(id);
	}
	
	
	@DeleteMapping("/DeleteMultiplerecods/{ids}")
	public String deleteMultipleRecods( @PathVariable List<Long> ids)
	{
		return employeeService.deleteMultipleRecods(ids);
	}

}
