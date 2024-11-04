package com.centre.rediscache.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.centre.rediscache.model.Employee;
import com.centre.rediscache.repository.EmployeeRepository;
import com.centre.rediscache.response.ResponseEmployee;
import com.centre.rediscache.util.CommonUtil;
import com.centre.rediscache.util.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.persistence.Id;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	private static final String EMPLOYEE_KEY = "Emp_";

	@Autowired
	private RedisService redisService;

	@Override
	public ResponseEmployee createEmployee(Employee employee) throws JsonProcessingException {
		employee.setAge(CommonUtil.calculateAge(employee.getDob().toString()));
		Employee saveemployee = employeeRepository.save(employee);
		redisService.setValueByKey(EMPLOYEE_KEY + saveemployee.getId().toString(), saveemployee, 10080l);

		return CommonUtil.entityToEmployeeResponse(saveemployee);

	}

	@Override
	public ResponseEmployee getEmployeeById(Long id) {
		Employee employee = redisService.getValueByKey(EMPLOYEE_KEY + id.toString(), Employee.class);
		if (employee != null) {
			return CommonUtil.entityToEmployeeResponse(employee);
		}

		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		return employeeOptional.isPresent()
				? ResponseEmployee.builder().id(employee.getId()).fullname(employee.getFullname())
						.email(employee.getEmail()).dob(employee.getDob()).age(employee.getAge()).build()
				: null;
	}

	@Override
	public String deleteById(Long id) {
		boolean deleteFromCache = redisService.deleteFromCache(EMPLOYEE_KEY + id.toString());
		return deleteFromCache == true ? id.toString() + " is deleted successfully " : "key not found in cache";
	}

	@Override
	public String deleteMultipleRecods(List<Long> ids) {
		 List<String> list = ids.stream().map(id -> EMPLOYEE_KEY+id).toList();
	        long deltetMultipleRecods = redisService.deltetMultipleRecodsFromcache(list);
			return deltetMultipleRecods > 0 ?  "recods deleted !" : "key not found";
	}

	

}
