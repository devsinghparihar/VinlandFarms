package com.transactions.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.transactions.model.Transaction;
import java.util.List;


public interface TransactionRepository extends MongoRepository<Transaction, String>  {

	List<Transaction> findByDealerId(String dealerId);
	List<Transaction> findByFarmerId(String farmerId);
}
