package com.emsjwt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emsjwt.clients.AdminClient;
import com.emsjwt.clients.DealerClient;
import com.emsjwt.clients.FarmerClient;
import com.emsjwt.model.Admin;
import com.emsjwt.model.Dealer;
import com.emsjwt.model.EmployeeModel;
import com.emsjwt.model.Farmer;
import com.emsjwt.model.Login;
import com.emsjwt.model.UserModel;
import com.emsjwt.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	FarmerClient farmerClient;
	@Autowired
	DealerClient dealerClient;
	@Autowired
	AdminClient adminClient;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login user = this.load(username);
		if(user == null) {
			throw new UsernameNotFoundException("User Not Found with email: "+username);
		}
		
		
		return UserDetailsImpl.getUser(user);
	}
	public Login load(String email) throws UsernameNotFoundException {
		
		if(farmerClient.findFarmerByEmail(email) != null) {
			Farmer user = farmerClient.findFarmerByEmail(email);
			Login  userModel = new Login 
					(user.getEmail(), user.getPassword(), user.getRole());
			return userModel;
		}
		else if(dealerClient.findDealerByEmail(email) != null) {
			Dealer user = dealerClient.findDealerByEmail(email);
			Login  userModel = new Login 
					(user.getEmail(), user.getPassword(), user.getRole());
			return userModel;
		}
		else if(adminClient.findByEmail(email) != null) {
			Admin user = adminClient.findByEmail(email);
			Login userModel = new Login 
					(user.getEmail(), user.getPassword(), user.getRole());
			return userModel;
		}
		return null;
	}

}
