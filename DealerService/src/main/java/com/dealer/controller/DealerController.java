package com.dealer.controller;

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

import com.dealer.dtos.UpdateDetailDTO;
import com.dealer.model.Dealer;
import com.dealer.service.DealerServiceImpl;



@RestController
@RequestMapping("/dealer")
public class DealerController {
	
	@Autowired
	DealerServiceImpl dealerService;
	
	@PostMapping("/register")
	public ResponseEntity<Dealer> registerDealer(@RequestBody Dealer dealer){
		return new ResponseEntity<Dealer>(dealerService.addDealer(dealer), HttpStatus.OK); 
		
	}
	@PostMapping("/addRequirement/{requirement}/{email}")
	public ResponseEntity<Dealer> addRequirement(@PathVariable String requirement, @PathVariable String email ){
		return new ResponseEntity<Dealer>(dealerService.addRequirement(requirement, email), HttpStatus.OK); 
		
	}
	@PostMapping("/updateDealer/{email}")
	public ResponseEntity<UpdateDetailDTO> updateDealer(@RequestBody UpdateDetailDTO update, @PathVariable String email ){
		return new ResponseEntity<UpdateDetailDTO>(dealerService.updateDealer(update, email), HttpStatus.OK); 
		
	}
	
	@GetMapping("/getAllDealers")
	public ResponseEntity<List<Dealer>> getAllDealers(){
		return new ResponseEntity<List<Dealer>>(dealerService.getAll(),HttpStatus.OK);
	}
		

}
