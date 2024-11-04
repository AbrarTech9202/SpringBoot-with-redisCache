package com.centre.rediscache.service;

import java.util.List;

import com.centre.rediscache.model.Employee;
import com.centre.rediscache.response.ResponseEmployee;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IEmployeeService {
	
	public ResponseEmployee createEmployee(Employee employee) throws JsonProcessingException;
	
	public ResponseEmployee getEmployeeById(Long id);
	
	public String deleteById(Long id);
	
	public String deleteMultipleRecods(List<Long> ids);

}
