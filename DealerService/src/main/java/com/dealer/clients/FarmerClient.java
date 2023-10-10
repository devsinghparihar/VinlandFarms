package com.dealer.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dealer.dtos.CropDetailDTO;
import com.dealer.model.Farmer;


@FeignClient(url = "http://localhost:5001/farmer",name = "farmerClient")
public interface FarmerClient {
	
	@GetMapping("/getAllCrops")
	public List<CropDetailDTO> getAllCrops();
	@GetMapping("/getCropsByType/{type}")
	public List<CropDetailDTO> getCropsByType(@PathVariable String type);
	
	@GetMapping("/findFarmerById/{Id}")
	public Farmer findFarmerById(@PathVariable String Id);
	@GetMapping("/findFarmerByEmail/{email}")
	public Farmer findFarmerByEmail(@PathVariable String email);
	
	@PutMapping("/updateFarmer")
	public Farmer updateFarmer(@RequestBody Farmer farmer );
	
}
