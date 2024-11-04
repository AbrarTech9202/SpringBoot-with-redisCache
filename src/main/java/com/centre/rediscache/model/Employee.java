package com.centre.rediscache.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(hidden = true)
	@Column(name = "emp_id")
	private Long id;
	
	@Column(name="Emp_FullName")
	private String fullname;
	
	@Column(name = "Emp_name")
	private String email;
	
	@Column(name="Emp_Dob")
	@Schema( type ="String", pattern = "yyyy-MM-dd" , example = "2021-09-01")
	@JsonFormat(shape = JsonFormat.Shape.STRING , pattern ="yyyy-MM-dd" )
	private LocalDate dob;
	
	@Column(name="Emp_age")
	@Schema(hidden = true)
	private Integer age;
	

}
