package com.farmer.service;

import java.util.List;

import com.farmer.dtos.UpdateDetailDTO;
import com.farmer.model.Crop;
import com.farmer.model.Farmer;

public interface FarmerService {
	
	Farmer addFarmer(Farmer f);
	List<Farmer> getAll();
	Farmer addCrop(Crop c, String email);
	UpdateDetailDTO updateFarmer(UpdateDetailDTO f,String email);
	
	
}
