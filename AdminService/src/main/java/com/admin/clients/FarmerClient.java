package com.admin.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.admin.dtos.FarmerUpdateDTO;
import com.admin.model.Farmer;




@FeignClient(url = "http://localhost:5001/farmer",name = "farmerClient")
public interface FarmerClient {
	

	
	@GetMapping("/findFarmerById/{Id}")
	public Farmer findFarmerById(@PathVariable String Id);
	@GetMapping("/findFarmerByEmail/{email}")
	public Farmer findFarmerByEmail(@PathVariable String email);
	
	@GetMapping("/getAll")
	public List<Farmer> getAllFarmers();
	
	@PutMapping("/updateFarmer/{email}")
	public FarmerUpdateDTO updateFarmer(@RequestBody FarmerUpdateDTO update, @PathVariable String email );
	@DeleteMapping("/deleteFarmerById/{id}")
	public Farmer deleteFarmerById(@PathVariable String id);
	
}
