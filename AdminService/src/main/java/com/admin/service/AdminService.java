package com.admin.service;

import java.util.List;

import com.admin.dtos.DealerUpdateDTO;
import com.admin.dtos.FarmerRatingDTO;
import com.admin.dtos.FarmerUpdateDTO;
import com.admin.model.Admin;
import com.admin.model.Dealer;
import com.admin.model.Farmer;
import com.admin.model.Transaction;



public interface AdminService {
	
	Admin addAdmin(Admin admin);
	Admin updateAdmin(Admin admin, String id);
	Admin findAdminByEmail(String email);
	Farmer deleteFarmerById(String id);
	Dealer deleteDealerById(String id);
	List<Farmer> getActiveFarmers();
	List<Farmer> getInactiveFarmers();
	List<Dealer> getActiveDealers();
	List<Dealer> getInactiveDealers();
	FarmerUpdateDTO updateFarmer(FarmerUpdateDTO f,String email);
	DealerUpdateDTO updateDealer(DealerUpdateDTO f,String email);
	List<FarmerRatingDTO> getFarmersRatings();
	List<Transaction> getAllTransaction();
	
	
	
	

}
