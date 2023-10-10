package com.farmer.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.farmer.dtos.TransactionDTO;
import com.farmer.model.Transaction;





@FeignClient(url = "http://localhost:5005/transaction",name = "transactionClient")
public interface TransactionClient {

	@PostMapping("/initiateTransaction")
	public Transaction createTransaction(@RequestBody TransactionDTO dto);
	
	@GetMapping("/farmerTransactionId/{id}")
	public List<Transaction> getFarmerHistory(@PathVariable String id);
	
	
	
}
