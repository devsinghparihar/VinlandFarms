package com.farmer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmer.dtos.UpdateDetailDTO;
import com.farmer.model.Crop;
import com.farmer.model.Farmer;
import com.farmer.repository.FarmerRepository;

@Service
public class FarmerServiceImpl implements FarmerService {
	@Autowired
	FarmerRepository fr;
	
	@Override
	public Farmer addFarmer(Farmer f) {
		
		return fr.save(f);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Farmer> getAll() {
		// TODO Auto-generated method stub
		return fr.findAll();
	}

	@Override
	public Farmer addCrop(Crop c, String email) {
		// TODO Auto-generated method stub
		Farmer farmer = fr.findByEmail(email);
		farmer.addCropToInventory(c);
		return fr.save(farmer);
	}

	@Override
	public UpdateDetailDTO updateFarmer(UpdateDetailDTO f, String email) {
		// TODO Auto-generated method stub
		Farmer farmer = fr.findByEmail(email);
		farmer.setName(f.getName());
		farmer.setAge(f.getAge());
		farmer.setPassword(f.getPassword());
		farmer.setAccountNumber(f.getAccountNumber());
		farmer.setGender(f.getGender());
		farmer.setLocation(f.getLocation());
		fr.save(farmer);
		// TODO Auto-generated method stub
		return f;
		
	}

	

	

	

	

}
