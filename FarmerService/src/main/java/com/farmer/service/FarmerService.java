package com.farmer.service;

import java.util.List;

import com.farmer.dtos.CropDetailDTO;
import com.farmer.dtos.UpdateDetailDTO;

import com.farmer.exceptions.EmailAlreadyExists;
import com.farmer.exceptions.FarmerException;
import com.farmer.exceptions.FarmerNotFoundException;
import com.farmer.exceptions.NoCropsFoundException;
import com.farmer.exceptions.NoTransactionFoundException;
import com.farmer.model.Crop;
import com.farmer.model.Farmer;
import com.farmer.model.Transaction;

public interface FarmerService {
	
	Farmer addFarmer(Farmer f) throws EmailAlreadyExists;
	List<Farmer> getAll() throws FarmerException;
	Farmer findFarmerById(String id) throws FarmerNotFoundException;
	Farmer deleteFarmerById(String id) throws FarmerNotFoundException;
	Farmer findFarmerByEmail(String email) throws FarmerNotFoundException;
	Farmer addCrop(Crop c, String email) ;
	UpdateDetailDTO updateFarmer(UpdateDetailDTO f,String email) throws FarmerNotFoundException;
	Farmer updateFarmer(Farmer farmer) throws FarmerNotFoundException;
	List<CropDetailDTO> getAllCrops();
	List<CropDetailDTO> getCropsByCropType(String cropType) throws NoCropsFoundException;
	List<Transaction> findTransactionsByFarmerId(String id) throws NoTransactionFoundException;
	String changePassword(String email, String newPassword) throws FarmerNotFoundException;
	String updateRating(String farmerId, int rating) throws FarmerNotFoundException;;
	
	
}
