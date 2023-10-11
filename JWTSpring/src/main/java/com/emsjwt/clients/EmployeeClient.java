package com.emsjwt.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emsjwt.model.EmployeeModel;




@FeignClient(url = "http://localhost:9001/employee",name = "khannaEmployeeClient")
public interface EmployeeClient {
	
	@PostMapping("/addEmployee")
	public EmployeeModel addEmployee(@RequestBody EmployeeModel emp);
	
	@GetMapping("/getEmployeeById/{id}")
	public EmployeeModel getEmployeeById(@PathVariable int id) throws SecurityException;
	@GetMapping("/getEmployeeByName/{name}")
	public EmployeeModel getEmployeeByName(@PathVariable String name) throws SecurityException;
	@GetMapping("/getAllEmployees")
	public List<EmployeeModel> getAllEmployees();
	
	

}
