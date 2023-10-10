package com.dealer.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dealer.dtos.TransactionDTO;
import com.dealer.model.Transaction;



@FeignClient(url = "http://localhost:5005/transaction",name = "transactionClient")
public interface TransactionClient {

	@PostMapping("/initiateTransaction")
	public Transaction createTransaction(@RequestBody TransactionDTO dto);
	
	@GetMapping("/dealerTransactionId/{id}")
	public List<Transaction> getDealerHistory(@PathVariable String id);
	
	
	
}
