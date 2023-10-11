package com.emsjwt.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emsjwt.model.EmployeeModel;
import com.emsjwt.model.UserDto;




@FeignClient(url = "http://localhost:9595/Hr",name = "hrClient")
public interface HrClient {
	
	@GetMapping("/getAllEmployees")
	public List<UserDto>getAllEmployees() throws SecurityException;
	
	@GetMapping("/getEmployeeById/{id}")
	public UserDto getEmployeeById(@PathVariable int id ) throws SecurityException;
		
	@PostMapping("/addEmployee")
	public UserDto addEmployee(@RequestBody EmployeeModel emp) throws SecurityException;	
	@GetMapping("/getEmployeesByDepartmentId/{id}")
	public List<UserDto> getEmployeesByDepartmentId(@PathVariable int id) throws SecurityException;
	@DeleteMapping("/deleteEmployeeById/{id}")
	public String deleteEmployeeById(@PathVariable int id) throws SecurityException;

}
