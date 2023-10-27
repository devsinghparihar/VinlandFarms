package com.emsjwt.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emsjwt.dtos.CropDetailDTO;
import com.emsjwt.dtos.FarmerUpdateDTO;
import com.emsjwt.model.Crop;
import com.emsjwt.model.Farmer;
import com.emsjwt.model.Transaction;



@FeignClient(url = "http://localhost:5001/farmer",name = "secureFarmerClient")
public interface FarmerClient {
	
	@PostMapping("/register")
	public Farmer registerFarmer(@RequestBody Farmer farmer);
	@PostMapping("/addCrop/{email}")
	public Farmer addCrop(@RequestBody Crop crop, @PathVariable String email );
	@PutMapping("/updateFarmer/{email}")
	public FarmerUpdateDTO updateFarmer(@RequestBody FarmerUpdateDTO update, @PathVariable String email );
	@PutMapping("/updateFarmer")
	public Farmer updateFarmer(@RequestBody Farmer farmer );
	@GetMapping("/getAll")
	public List<Farmer> getAllFarmers();
	@GetMapping("/findFarmerById/{Id}")
	public Farmer findFarmerById(@PathVariable String Id);
	@GetMapping("/findFarmerByEmail/{email}")
	public Farmer findFarmerByEmail(@PathVariable String email);
	@GetMapping("/getAllCrops")
	public List<CropDetailDTO> getAllCrops();
	@GetMapping("/getCropsByType/{type}")
	public List<CropDetailDTO> getCropsByType(@PathVariable String type);
	@GetMapping("/farmerTransactionHistory/{id}")
	public List<Transaction> getFarmerHistory(@PathVariable String id);
	@GetMapping("/changePassword/{email}/{password}")
	public String changePassword(@PathVariable String email, @PathVariable String password);

}
