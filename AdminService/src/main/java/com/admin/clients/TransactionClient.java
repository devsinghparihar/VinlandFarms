package com.admin.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.admin.model.Transaction;





@FeignClient(url = "http://localhost:5005/transaction",name = "transactionClient")
public interface TransactionClient {

		
	
	@GetMapping("/dealerTransactionId/{id}")
	public List<Transaction> getDealerHistory(@PathVariable String id);
	
	@GetMapping("/getAllTransactions")
	public List<Transaction> getAllTransactions();
	
	
	
}
