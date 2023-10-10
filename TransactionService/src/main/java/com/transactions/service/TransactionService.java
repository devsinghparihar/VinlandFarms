package com.transactions.service;

import java.util.List;

import com.transactions.dtos.TransactionDTO;
import com.transactions.model.Transaction;

public interface TransactionService {
	
	Transaction initiateTransaction(TransactionDTO transaction);
	List<Transaction> allTransactions();
	List<Transaction> findTransactionsByFarmerId(String id);
	List<Transaction> findTransactionsByDealerId(String id);
	
	

}
