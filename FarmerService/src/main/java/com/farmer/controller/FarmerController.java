package com.farmer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmer.dtos.UpdateDetailDTO;
import com.farmer.model.Crop;
import com.farmer.model.Crop;
import com.farmer.model.Farmer;
import com.farmer.service.FarmerServiceImpl;

@RestController
@RequestMapping("/farmer")
public class FarmerController {
	
	@Autowired
	FarmerServiceImpl farmerService;
	
	@PostMapping("/register")
	public ResponseEntity<Farmer> registerFarmer(@RequestBody Farmer farmer){
		return new ResponseEntity<Farmer>(farmerService.addFarmer(farmer), HttpStatus.OK); 
		
	}
	@PostMapping("/addCrop/{email}")
	public ResponseEntity<Farmer> addCrop(@RequestBody Crop crop, @PathVariable String email ){
		return new ResponseEntity<Farmer>(farmerService.addCrop(crop, email), HttpStatus.OK); 
		
	}
	@PostMapping("/updateFarmer/{email}")
	public ResponseEntity<UpdateDetailDTO> updateFarmer(@RequestBody UpdateDetailDTO update, @PathVariable String email ){
		return new ResponseEntity<UpdateDetailDTO>(farmerService.updateFarmer(update, email), HttpStatus.OK); 
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Farmer>> getAllFarmers(){
		return new ResponseEntity<List<Farmer>>(farmerService.getAll(),HttpStatus.OK);
	}
	

}
