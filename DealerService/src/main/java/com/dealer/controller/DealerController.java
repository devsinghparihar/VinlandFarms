package com.dealer.controller;

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

import com.dealer.clients.FarmerClient;
import com.dealer.dtos.CropDetailDTO;
import com.dealer.dtos.UpdateDetailDTO;
import com.dealer.exceptions.ResourceNotFoundException;
import com.dealer.model.Dealer;
import com.dealer.model.Transaction;
import com.dealer.service.DealerServiceImpl;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/dealer")
//@EnableFeignClients(basePackages = "com.dealer.clients")

public class DealerController {
	
	@Autowired
	DealerServiceImpl dealerService;
	
	@Autowired
	FarmerClient farmerClient;
	
	@PostMapping("/register")
	public ResponseEntity<Dealer> registerDealer(@RequestBody Dealer dealer){
		return new ResponseEntity<Dealer>(dealerService.addDealer(dealer), HttpStatus.OK); 
		
	}
	@PostMapping("/addRequirement/{requirement}/{email}")
	public ResponseEntity<Dealer> addRequirement(@PathVariable String requirement, @PathVariable String email ){
		return new ResponseEntity<Dealer>(dealerService.addRequirement(requirement, email), HttpStatus.OK); 
		
	}
	@PutMapping("/updateDealer/{email}")
	public ResponseEntity<UpdateDetailDTO> updateDealer(@RequestBody UpdateDetailDTO update, @PathVariable String email ){
		return new ResponseEntity<UpdateDetailDTO>(dealerService.updateDealer(update, email), HttpStatus.OK); 
		
	}
	@PutMapping("/addMoneyToWallet/{id}/{amount}")
	public ResponseEntity<String> addMoneyToWallet(@PathVariable String id, @PathVariable long amount ){
		return new ResponseEntity<String>(dealerService.addMoneyToWallet(id, amount), HttpStatus.OK); 
		
	}
	
	@GetMapping("/getAllDealers")
	public ResponseEntity<List<Dealer>> getAllDealers(){
		return new ResponseEntity<List<Dealer>>(dealerService.getAll(),HttpStatus.OK);
	}
	
	//client Call
	@GetMapping("/searchAllCrops")
	public ResponseEntity<List<CropDetailDTO>> getAllCrops(){
		return new ResponseEntity<List<CropDetailDTO>>(farmerClient.getAllCrops(),HttpStatus.OK);
	}
	@GetMapping("/searchCropsByType/{type}")
	public ResponseEntity<List<CropDetailDTO>> getCropsByType(@PathVariable String type) throws ResourceNotFoundException {
		return new ResponseEntity<List<CropDetailDTO>>(farmerClient.getCropsByType(type),HttpStatus.OK);
	}
	
	
	@GetMapping("/findDealerById/{Id}")
	public ResponseEntity<Dealer> findDealerById(@PathVariable String Id){
		return new ResponseEntity<Dealer>(dealerService.findDealerById(Id),HttpStatus.OK);
	}
	@GetMapping("/findDealerByEmail/{email}")
	public ResponseEntity<Dealer> findDealerByEmail(@PathVariable String email){
		return new ResponseEntity<Dealer>(dealerService.findDealerByEmail(email),HttpStatus.OK);
	}
	
//	initiate transaction
	@PutMapping("/buyCrop/{farmerId}/{dealerId}/{cropType}/{quantity}")
	public ResponseEntity<Transaction> buyCrop(@PathVariable String farmerId,@PathVariable String dealerId,@PathVariable String cropType,@PathVariable int quantity){
		return new ResponseEntity<Transaction>(dealerService.buyCrop(farmerId, dealerId, cropType, quantity), HttpStatus.OK);
	}
	
	@GetMapping("/dealerTransactionHistory/{id}")
	public ResponseEntity<List<Transaction>> getDealerHistory(@PathVariable String id){
		return new ResponseEntity<List<Transaction>>(dealerService.findTransactionsByDealerId(id),HttpStatus.OK);
	}
	@GetMapping("/findRequirements/{id}")
	public ResponseEntity<List<String>> findRequirements(@PathVariable String id){
		return new ResponseEntity<List<String>>(dealerService.findRequirements(id),HttpStatus.OK);
	}
	@DeleteMapping("/deleteDealerById/{id}")
	public ResponseEntity<Dealer> deleteDealerById(@PathVariable String id){
		return new ResponseEntity<Dealer>(dealerService.deleteDealerById(id),HttpStatus.OK);
	}
	
	
		

}
