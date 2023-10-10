package com.transactions.controller;

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

import com.transactions.dtos.TransactionDTO;
import com.transactions.model.Transaction;
import com.transactions.service.TransactionServiceImpl;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	@Autowired
	TransactionServiceImpl transactionService;
	
	@PostMapping("/initiateTransaction")
	public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO dto){
		return new ResponseEntity<Transaction>(transactionService.initiateTransaction(dto),HttpStatus.OK);
	}
	
	@GetMapping("/farmerTransactionId/{id}")
	public ResponseEntity<List<Transaction>> getFarmerHistory(@PathVariable String id){
		return new ResponseEntity<List<Transaction>>(transactionService.findTransactionsByFarmerId(id),HttpStatus.OK);
	}
	@GetMapping("/dealerTransactionId/{id}")
	public ResponseEntity<List<Transaction>> getDealerHistory(@PathVariable String id){
		return new ResponseEntity<List<Transaction>>(transactionService.findTransactionsByDealerId(id),HttpStatus.OK);
	}
	@GetMapping("/getAllTransactions")
	public ResponseEntity<List<Transaction>> getAllTransactions(){
		return new ResponseEntity<List<Transaction>>(transactionService.allTransactions(),HttpStatus.OK);
	}

}
