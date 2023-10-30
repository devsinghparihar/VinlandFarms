package com.admin.service;

import java.util.List;

import com.admin.dtos.DealerUpdateDTO;
import com.admin.dtos.FarmerRatingDTO;
import com.admin.dtos.FarmerUpdateDTO;
import com.admin.exceptions.EmailAlreadyExists;
import com.admin.exceptions.ResourceNotFoundException;
import com.admin.model.Admin;
import com.admin.model.Dealer;
import com.admin.model.Farmer;
import com.admin.model.Transaction;



public interface AdminService {
	
	Admin addAdmin(Admin admin) throws EmailAlreadyExists;
	Admin updateAdmin(Admin admin, String id) throws ResourceNotFoundException;
	Admin findAdminByEmail(String email) throws ResourceNotFoundException;
	Farmer deleteFarmerById(String id) throws ResourceNotFoundException;
	Dealer deleteDealerById(String id) throws ResourceNotFoundException;
	List<Farmer> getActiveFarmers() ;
	List<Farmer> getInactiveFarmers();
	List<Dealer> getActiveDealers();
	List<Dealer> getInactiveDealers();
	FarmerUpdateDTO updateFarmer(FarmerUpdateDTO f,String email) throws ResourceNotFoundException;
	DealerUpdateDTO updateDealer(DealerUpdateDTO f,String email) throws ResourceNotFoundException;
	List<FarmerRatingDTO> getFarmersRatings();
	List<Transaction> getAllTransaction();
	
	
	
	

}
