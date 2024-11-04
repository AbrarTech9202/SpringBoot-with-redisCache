package com.centre.rediscache.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import com.centre.rediscache.model.Employee;
import com.centre.rediscache.response.ResponseEmployee;

public class CommonUtil {

	public static int calculateAge(String dob) {
		LocalDate birthday = null;
		try {

			DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			birthday = LocalDate.parse(dob, ofPattern);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return Period.between(birthday, LocalDate.now()).getYears();
	}
	
	public static ResponseEmployee entityToEmployeeResponse(Employee employee)
	{
		if(employee == null)
		{
			return null;
		}
		
		return ResponseEmployee.builder()
				.id(employee.getId())
				.fullname(employee.getFullname())
				.email(employee.getEmail())
				.dob(employee.getDob())
				.age(employee.getAge())
				.build();
	}

}
