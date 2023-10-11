package com.emsjwt.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {
	
	private int eid;
	private String name;
	private String password;
	private String gender;
	private String role;
	private int age;
	private double salary;
	private int departmentId;

}