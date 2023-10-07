package com.dealer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealer.dtos.UpdateDetailDTO;
import com.dealer.model.Dealer;
import com.dealer.repository.DealerRepository;

@Service
public class DealerServiceImpl implements DealerService {
	
	@Autowired
	DealerRepository dr;

	@Override
	public Dealer addDealer(Dealer d) {
		// TODO Auto-generated method stub
		return dr.save(d);
	}

	@Override
	public List<Dealer> getAll() {
		// TODO Auto-generated method stub
		return dr.findAll();
	}

	@Override
	public Dealer addRequirement(String requirement, String email) {
		// TODO Auto-generated method stub
		Dealer dealer = dr.findByEmail(email);
		dealer.addReqirements(requirement);
		return dr.save(dealer);
	}

	@Override
	public UpdateDetailDTO updateDealer(UpdateDetailDTO f, String email) {
		// TODO Auto-generated method stub
		Dealer farmer = dr.findByEmail(email);
		farmer.setName(f.getName());
		farmer.setAge(f.getAge());
		farmer.setPassword(f.getPassword());
		farmer.setAccountNumber(f.getAccountNumber());
		farmer.setGender(f.getGender());
		farmer.setAccountBalance(f.getAccountBalance());
		dr.save(farmer);
		return f;
	}

}
