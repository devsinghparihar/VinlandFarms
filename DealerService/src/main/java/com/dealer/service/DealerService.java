package com.dealer.service;

import java.util.List;

import com.dealer.dtos.UpdateDetailDTO;
import com.dealer.model.Dealer;
import com.dealer.model.Transaction;



public interface DealerService {
	
	Dealer addDealer(Dealer d);
	List<Dealer> getAll();
	Dealer addRequirement(String requirement, String email);
	UpdateDetailDTO updateDealer(UpdateDetailDTO f,String email);
	Dealer findDealerById(String id);
	Dealer findDealerByEmail(String email);
	Dealer deleteDealerById(String id);
	String addMoneyToWallet(String id,long money);
	Transaction buyCrop(String farmerId,String dealerId, String cropType, int quantity);
	List<Transaction> findTransactionsByDealerId(String id);
	boolean runScan(String dealerId);
	List<String> findRequirements(String dealerId);
	

}
