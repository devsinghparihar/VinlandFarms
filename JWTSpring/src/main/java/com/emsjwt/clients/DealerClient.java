package com.emsjwt.clients;

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

import com.emsjwt.dtos.CropDetailDTO;
import com.emsjwt.dtos.DealerUpdateDTO;
import com.emsjwt.model.Dealer;
import com.emsjwt.model.Transaction;



@FeignClient(url = "http://localhost:5002/dealer",name = "secureDealerClient")
public interface DealerClient {
	
	@PostMapping("/register")
	public Dealer registerDealer(@RequestBody Dealer dealer);
	@PostMapping("/addRequirement/{requirement}/{email}")
	public Dealer addRequirement(@PathVariable String requirement, @PathVariable String email );
	@PutMapping("/updateDealer/{email}")
	public DealerUpdateDTO updateDealer(@RequestBody DealerUpdateDTO update, @PathVariable String email );
	
	@GetMapping("/getAllDealers")
	public List<Dealer> getAllDealers();
	
	//client Call
	@GetMapping("/searchAllCrops")
	public List<CropDetailDTO> getAllCrops();
	@GetMapping("/searchCropsByType/{type}")
	public List<CropDetailDTO> getCropsByType(@PathVariable String type);
	@PutMapping("/addMoneyToWallet/{id}/{amount}")
	public String addMoneyToWallet(@PathVariable String id, @PathVariable long amount );
	
	@GetMapping("/findDealerById/{Id}")
	public Dealer findDealerrById(@PathVariable String Id);
	@GetMapping("/findDealerByEmail/{email}")
	public Dealer findDealerByEmail(@PathVariable String email);
	
	@DeleteMapping("/deleteDealerById/{id}")
	public Dealer deleteDealerById(@PathVariable String id);
	
//	initiate transaction
	@PutMapping("/buyCrop/{farmerId}/{dealerId}/{cropType}/{quantity}")
	public Transaction buyCrop(@PathVariable String farmerId,@PathVariable String dealerId,@PathVariable String cropType,@PathVariable int quantity);

	
	@GetMapping("/dealerTransactionHistory/{id}")
	public List<Transaction> getDealerHistory(@PathVariable String id);
	@GetMapping("/findRequirements/{id}")
	public List<String> findRequirements(@PathVariable String id);
	@GetMapping("/updateFarmerRating/{farmerId}/{rating}")
	public String updateFarmerRating(@PathVariable String farmerId, @PathVariable int rating);
	

}
