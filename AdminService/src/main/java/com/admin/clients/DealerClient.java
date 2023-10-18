package com.admin.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.admin.dtos.DealerUpdateDTO;
import com.admin.model.Dealer;
import com.admin.model.Transaction;





@FeignClient(url = "http://localhost:5002/dealer",name = "dealerClient")
public interface DealerClient {
	@GetMapping("/getAllDealers")
	public List<Dealer> getAllDealers();
	@GetMapping("/findDealerById/{Id}")
	public Dealer findFarmerById(@PathVariable String Id);
	
	@GetMapping("/dealerTransactionHistory/{id}")
	public List<Transaction> getDealerHistory(@PathVariable String id);
	
	@PutMapping("/updateDealer/{email}")
	public DealerUpdateDTO updateDealer(@RequestBody DealerUpdateDTO update, @PathVariable String email );
	@DeleteMapping("/deleteDealerById/{id}")
	public Dealer deleteDealerById(@PathVariable String id);
	
	
}
