package com.admin.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.clients.DealerClient;
import com.admin.clients.FarmerClient;
import com.admin.clients.TransactionClient;
import com.admin.dtos.DealerUpdateDTO;
import com.admin.dtos.FarmerRatingDTO;
import com.admin.dtos.FarmerUpdateDTO;
import com.admin.exceptions.EmailAlreadyExists;
import com.admin.exceptions.ResourceNotFoundException;
import com.admin.model.Admin;
import com.admin.model.Dealer;
import com.admin.model.Farmer;
import com.admin.model.Transaction;
import com.admin.repository.AdminRepository;


@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminRepository ar;
	
	@Autowired
	FarmerClient farmerClient;
	@Autowired
	DealerClient dealerClient;
	
	@Autowired 
	TransactionClient transactionClient;

	@Override
	public Admin addAdmin(Admin admin) throws EmailAlreadyExists {
		if(ar.findByEmail(admin.getEmail()) !=null ) {
			throw new EmailAlreadyExists("User with email"+admin.getEmail()+" already exists");
		}
		// TODO Auto-generated method stub
		return ar.save(admin);
	}

	@Override
	public Admin updateAdmin(Admin admin, String id) throws ResourceNotFoundException{
		// TODO Auto-generated method stub
		if(ar.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("Admin not found with this id");
		}
		Admin a = ar.findById(id).get();
		a.setGender(admin.getGender());
		a.setName(admin.getName());
		a.setPassword(admin.getPassword());
		return ar.save(a);
	}

	@Override
	public List<Farmer> getActiveFarmers() {
		// TODO Auto-generated method stub
		List<Farmer> activeFarmers = new ArrayList<>();
		for(Farmer f: farmerClient.getAllFarmers()) {
			if(!(f.getCrops().isEmpty())) {
				activeFarmers.add(f);
			}
		}
		return activeFarmers;
	}

	@Override
	public List<Farmer> getInactiveFarmers() {
		// TODO Auto-generated method stub
		List<Farmer> inActiveFarmers = new ArrayList<>();
		for(Farmer f: farmerClient.getAllFarmers()) {
			if(f.getCrops().isEmpty()) {
				inActiveFarmers.add(f);
			}
		}
		return inActiveFarmers;
	}

	@Override
	public List<Dealer> getActiveDealers() {
		Set<Dealer> activeDealers = new HashSet<>();
		for(Transaction t: transactionClient.getAllTransactions()) {
			activeDealers.add(dealerClient.findFarmerById(t.getDealerId()));
		}
		
		List<Dealer> avail = new ArrayList<>();
		
		avail.addAll(activeDealers);
		
		// TODO Auto-generated method stub
		return avail;
	}

	@Override
	public List<Dealer> getInactiveDealers() {
		List<Dealer> inActiveDealers = new ArrayList<>();
		for(Dealer d: dealerClient.getAllDealers()) {
			if(transactionClient.getDealerHistory(d.getDealerId()).isEmpty()) {
				inActiveDealers.add(d);
			}
		}
		// TODO Auto-generated method stub
		return inActiveDealers;
	}

	@Override
	public FarmerUpdateDTO updateFarmer(FarmerUpdateDTO f, String email) {
		// TODO Auto-generated method stub
		if(farmerClient.findFarmerByEmail(email) == null) {
			throw new ResourceNotFoundException("Farmer with email: "+email+" not found");
		}
		return farmerClient.updateFarmer(f, email);
	}

	@Override
	public DealerUpdateDTO updateDealer(DealerUpdateDTO f, String email) {
	
		// TODO Auto-generated method stub
	
		return dealerClient.updateDealer(f, email);
	}

	@Override
	public List<FarmerRatingDTO> getFarmersRatings() {
		List<FarmerRatingDTO> ratings = new ArrayList<>();
		for(Farmer f: farmerClient.getAllFarmers()) {
			FarmerRatingDTO frd = new FarmerRatingDTO();
			frd.setName(f.getName());
			frd.setEmail(f.getEmail());
			frd.setLocation(f.getLocation());
			frd.setRating(f.getRating());
			ratings.add(frd);
		}
		
		// TODO Auto-generated method stub
		return ratings;
	}

	@Override
	public Admin findAdminByEmail(String email) {
		// TODO Auto-generated method stub
		if(ar.findByEmail(email) == null) {
			throw new ResourceNotFoundException("Admin with this email not found");
		}
		return ar.findByEmail(email).get();
	}

	@Override
	public Farmer deleteFarmerById(String id) {
		// TODO Auto-generated method stub
		if(farmerClient.findFarmerById(id)==null) {
			throw new ResourceNotFoundException("Farmer with this id not found");
		}
		return farmerClient.deleteFarmerById(id);
	}
	@Override
	public Dealer deleteDealerById(String id) {
		// TODO Auto-generated method stu
		if (dealerClient.findFarmerById(id) == null) {
			throw new ResourceNotFoundException("Dealer not found");
		}
		return dealerClient.deleteDealerById(id);
	}

	@Override
	public List<Transaction> getAllTransaction() {
		// TODO Auto-generated method stub
		return transactionClient.getAllTransactions();
	}

}
