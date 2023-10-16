package com.farmer.service;

import java.util.List;

import com.farmer.dtos.CropDetailDTO;
import com.farmer.dtos.UpdateDetailDTO;
import com.farmer.model.Crop;
import com.farmer.model.Farmer;
import com.farmer.model.Transaction;

public interface FarmerService {
	
	Farmer addFarmer(Farmer f);
	List<Farmer> getAll();
	Farmer findFarmerById(String id);
	Farmer deleteFarmerById(String id);
	Farmer findFarmerByEmail(String email);
	Farmer addCrop(Crop c, String email);
	UpdateDetailDTO updateFarmer(UpdateDetailDTO f,String email);
	Farmer updateFarmer(Farmer farmer);
	List<CropDetailDTO> getAllCrops();
	List<CropDetailDTO> getCropsByCropType(String cropType);
	List<Transaction> findTransactionsByFarmerId(String id);
	
	
	
}
