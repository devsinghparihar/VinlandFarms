package com.farmer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmer.dtos.CropDetailDTO;
import com.farmer.dtos.UpdateDetailDTO;
import com.farmer.model.Crop;
import com.farmer.model.Farmer;
import com.farmer.model.Transaction;
import com.farmer.service.FarmerServiceImpl;

@RestController
@RequestMapping("/farmer")
@CrossOrigin(origins = "http://localhost:3000")
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
	@PutMapping("/updateFarmer/{email}")
	public ResponseEntity<UpdateDetailDTO> updateFarmer(@RequestBody UpdateDetailDTO update, @PathVariable String email ){
		return new ResponseEntity<UpdateDetailDTO>(farmerService.updateFarmer(update, email), HttpStatus.OK); 
		
	}
	@PutMapping("/updateFarmer")
	public ResponseEntity<Farmer> updateFarmer(@RequestBody Farmer farmer ){
		return new ResponseEntity<Farmer>(farmerService.updateFarmer(farmer), HttpStatus.OK); 
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Farmer>> getAllFarmers(){
		return new ResponseEntity<List<Farmer>>(farmerService.getAll(),HttpStatus.OK);
	}
	@GetMapping("/findFarmerById/{Id}")
	public ResponseEntity<Farmer> findFarmerById(@PathVariable String Id){
		return new ResponseEntity<Farmer>(farmerService.findFarmerById(Id),HttpStatus.OK);
	}
	@GetMapping("/findFarmerByEmail/{email}")
	public ResponseEntity<Farmer> findFarmerByEmail(@PathVariable String email){
		return new ResponseEntity<Farmer>(farmerService.findFarmerByEmail(email),HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getAllCrops")
	public ResponseEntity<List<CropDetailDTO>> getAllCrops(){
		return new ResponseEntity<List<CropDetailDTO>>(farmerService.getAllCrops(),HttpStatus.OK);
	}
	@GetMapping("/getCropsByType/{type}")
	public ResponseEntity<List<CropDetailDTO>> getCropsByType(@PathVariable String type){
		return new ResponseEntity<List<CropDetailDTO>>(farmerService.getCropsByCropType(type),HttpStatus.OK);
	}
	@GetMapping("/farmerTransactionHistory/{id}")
	public ResponseEntity<List<Transaction>> getFarmerHistory(@PathVariable String id){
		return new ResponseEntity<List<Transaction>>(farmerService.findTransactionsByFarmerId(id),HttpStatus.OK);
	}
	@DeleteMapping("/deleteFarmerById/{id}")
	public ResponseEntity<Farmer> deleteFarmerById(@PathVariable String id){
		return new ResponseEntity<Farmer>(farmerService.deleteFarmerById(id),HttpStatus.OK);
	}

}
