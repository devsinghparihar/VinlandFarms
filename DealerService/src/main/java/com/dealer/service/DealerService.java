package com.dealer.service;

import java.util.List;

import com.dealer.dtos.UpdateDetailDTO;
import com.dealer.exceptions.EmailAlreadyExists;
import com.dealer.exceptions.InvalidTransaction;
import com.dealer.exceptions.ResourceNotFoundException;
import com.dealer.model.Dealer;
import com.dealer.model.Transaction;




public interface DealerService {
	
	Dealer addDealer(Dealer d) throws EmailAlreadyExists  ;
	List<Dealer> getAll() throws ResourceNotFoundException;
	Dealer addRequirement(String requirement, String email) throws ResourceNotFoundException;
	UpdateDetailDTO updateDealer(UpdateDetailDTO f,String email) throws ResourceNotFoundException;
	Dealer findDealerById(String id) throws ResourceNotFoundException ;
	Dealer findDealerByEmail(String email) throws ResourceNotFoundException;
	Dealer deleteDealerById(String id) throws ResourceNotFoundException;
	String addMoneyToWallet(String id,long money)throws ResourceNotFoundException;
	Transaction buyCrop(String farmerId,String dealerId, String cropType, int quantity) throws InvalidTransaction;
	List<Transaction> findTransactionsByDealerId(String id) throws ResourceNotFoundException;
	boolean runScan(String dealerId) throws ResourceNotFoundException;
	List<String> findRequirements(String dealerId) throws ResourceNotFoundException;
	String changePassword(String email, String newPassword) throws ResourceNotFoundException;
	String updateFarmerRating(String farmerId, int rating) throws ResourceNotFoundException;
	

}
