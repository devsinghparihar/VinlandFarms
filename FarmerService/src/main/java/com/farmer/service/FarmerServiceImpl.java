package com.farmer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmer.clients.EmailClient;
import com.farmer.clients.TransactionClient;
import com.farmer.dtos.CropDetailDTO;
import com.farmer.dtos.EmailModel;
import com.farmer.dtos.UpdateDetailDTO;
import com.farmer.exceptions.EmailAlreadyExists;
import com.farmer.exceptions.FarmerException;
import com.farmer.exceptions.FarmerNotFoundException;
import com.farmer.exceptions.NoCropsFoundException;
import com.farmer.exceptions.NoTransactionFoundException;
import com.farmer.model.Crop;
import com.farmer.model.Farmer;
import com.farmer.model.Transaction;
import com.farmer.repository.FarmerRepository;

@Service
public class FarmerServiceImpl implements FarmerService {
	@Autowired
	FarmerRepository fr;
	
	@Autowired
	EmailClient emailClient;
	
	@Autowired 
	TransactionClient transactionClient;
	
	
	
	@Override
	public Farmer addFarmer(Farmer f) throws EmailAlreadyExists {
		if(fr.findByEmail(f.getEmail()) !=null ) {
			throw new EmailAlreadyExists("User with email"+f.getEmail()+" already exists");
		}
		EmailModel email = new EmailModel();
		email.setTo(f.getEmail());
		email.setSubject("Welcome to Vinland Farms");
		email.setText("Dear Farmer,\r\n"
				+ "\r\n"
				+ "Welcome to Vinland Farms! We are thrilled to have you as part of our community of farmers.\r\n"
				+ "\r\n"
				+ "With Vinland Farms, you can easily post your crops, connect with dealers, and showcase your produce to a wide audience. We are here to support you on your farming journey.\r\n"
				+ "\r\n"
				+ "If you have any questions or need assistance, please don't hesitate to reach out. We're here to help.\r\n"
				+ "\r\n"
				+ "Thank you for choosing Vinland Farms. We look forward to working together.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "The Vinland Farms Team");
		emailClient.sendMail(email);  //log email sent or not 
		return fr.save(f);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Farmer> getAll() throws FarmerException {
		// TODO Auto-generated method stub
		if(fr.findAll().isEmpty()) {
			throw new FarmerException("No Farmer has registered yet");
		}
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
	public UpdateDetailDTO updateFarmer(UpdateDetailDTO f, String email) throws FarmerNotFoundException {
		// TODO Auto-generated method stub
		if(fr.findByEmail(email) == null) {
			throw new FarmerNotFoundException("No Farmer with email "+email+" found");
		}
		Farmer farmer = fr.findByEmail(email);
		farmer.setName(f.getName());
		farmer.setAge(f.getAge());
		farmer.setGender(f.getGender());
		farmer.setLocation(f.getLocation());
		fr.save(farmer);
		// TODO Auto-generated method stub
		return f;
		
	}

	@Override
	public List<CropDetailDTO> getAllCrops() throws NoCropsFoundException {
		// TODO Auto-generated method stub
		List<Farmer> all = fr.findAll();
		List<CropDetailDTO> allCrops = new ArrayList<>();
		for(Farmer f: all) {
			
			for(Crop c : f.getCrops()) {
				CropDetailDTO d = new CropDetailDTO(); 
				d.setFarmerId(f.getFarmerId());
				d.setName(f.getName());
				d.setEmail(f.getEmail());
				d.setLocation(f.getLocation());
				d.setAccountNumber(f.getAccountNumber());
				d.setCropType(c.getCropType());
				d.setCropQuantity(c.getCropQuantity());
				d.setCropPrice(c.getCropPrice());
				allCrops.add(d);
				
			}
			
			
		}
		if(allCrops.isEmpty()) {
			throw new NoCropsFoundException("No Crops Found in inventory");
		}
		return allCrops;
	}

	@Override
	public List<CropDetailDTO> getCropsByCropType(String cropType) throws NoCropsFoundException {
		// TODO Auto-generated method stub
		List<CropDetailDTO> filtertedCrops = new ArrayList<>();
		for(CropDetailDTO details : this.getAllCrops()) {
			if(details.getCropType().equalsIgnoreCase(cropType)) {
				filtertedCrops.add(details);
			}
		}
		if(filtertedCrops.isEmpty()) {
			throw new NoCropsFoundException("No Crops of type "+cropType+" found in inventory");
		}
		return filtertedCrops;
	}

	@Override
	public Farmer findFarmerById(String id) throws FarmerNotFoundException {
		// TODO Auto-generated method stub
		if(fr.findById(id).isEmpty()) {
			throw new FarmerNotFoundException("Farmer with id "+id+" does not exist");
		}
		return fr.findById(id).get();
	}

	@Override
	public Farmer findFarmerByEmail(String email) throws FarmerNotFoundException {
		// TODO Auto-generated method stub
		if(fr.findByEmail(email) == null) {
			throw new FarmerNotFoundException("No Farmer with email "+email+" found");
		}
		return fr.findByEmail(email);
	}

	@Override
	public Farmer updateFarmer(Farmer farmer) throws FarmerNotFoundException {
		String id = farmer.getFarmerId();
		if(fr.findById(id).isEmpty()) {
			throw new FarmerNotFoundException("Farmer with id "+id+" does not exist");
		}
		// TODO Auto-generated method stub
		return fr.save(farmer);
	}

	@Override
	public List<Transaction> findTransactionsByFarmerId(String id) throws NoTransactionFoundException {
		// TODO Auto-generated method stub
		if(transactionClient.getFarmerHistory(id).isEmpty()) {
			throw new NoTransactionFoundException("No Transaction is associated with this farmer");
		}
		return transactionClient.getFarmerHistory(id);
	}

	@Override
	public Farmer deleteFarmerById(String id) throws FarmerNotFoundException {
		// TODO Auto-generated method stub
		if(fr.findById(id).isEmpty()) {
			throw new FarmerNotFoundException("Farmer with id "+id+" does not exist");
		}
		Farmer farmer = fr.findById(id).get();
		fr.deleteById(id);
		return farmer;
	}

	@Override
	public String changePassword(String email, String newPassword) throws FarmerNotFoundException {
		// TODO Auto-generated method stub
		if(fr.findByEmail(email) == null) {
			throw new FarmerNotFoundException("No Farmer with email "+email+" found");
		}
		Farmer farmer = fr.findByEmail(email);
		farmer.setPassword(newPassword);
		fr.save(farmer);
		return "Password changed Successfully";
	}

	
	

	

	

	

}
