package com.emsjwt.service;


import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emsjwt.clients.AdminClient;
import com.emsjwt.clients.DealerClient;
import com.emsjwt.clients.EmailClient;
import com.emsjwt.clients.FarmerClient;
import com.emsjwt.dtos.ChangePasswordDTO;
import com.emsjwt.model.Admin;
import com.emsjwt.model.Dealer;
import com.emsjwt.model.EmailModel;
import com.emsjwt.model.Farmer;
import com.emsjwt.model.Login;
import com.emsjwt.model.SendOTP;
import com.emsjwt.repository.OTPRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	FarmerClient farmerClient;
	@Autowired
	DealerClient dealerClient;
	@Autowired
	AdminClient adminClient;
	@Autowired
	EmailClient emailClient;
	@Autowired
	OTPRepository otpRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login user = null;
		try {
			user = this.loadFarmer(username);
		}
		catch(Exception e) {}
		try {
			user = this.loadDealer(username);
		}
		catch(Exception e) {}
		try {
			user = this.loadAdmin(username);
		}
		catch(Exception e) {}
		
		
//		Login user = this.load(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User Not Found with email: "+username);
		}
		
		
		return UserDetailsImpl.getUser(user);
	}
	
	
	
	

	public Login loadFarmer(String email) {
		Farmer user = farmerClient.findFarmerByEmail(email);
		Login  userModel = new Login 
				(user.getEmail(), user.getPassword(), user.getRole(),user.getFarmerId());
		return userModel;
	}
	public Login loadDealer(String email) {
		Dealer user = dealerClient.findDealerByEmail(email);
		Login  userModel = new Login 
				(user.getEmail(), user.getPassword(), user.getRole(),user.getDealerId());
		return userModel;
	}
	public Login loadAdmin(String email) {
		Admin user = adminClient.findByEmail(email);
		Login userModel = new Login 
				(user.getEmail(), user.getPassword(), user.getRole(),user.getAdminId());
		return userModel;
	}
	
	public String sendOTP(String userEmail) {
		try {
		Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        EmailModel email = new EmailModel();
		email.setTo(userEmail);
		email.setSubject("OTP to reset password: VinlandFarms");
		email.setText("Dear User,\r\n"
				+ "\r\n"
				+ "OTP to reset password is : " +otp
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "The Vinland Farms Team");
		emailClient.sendMail(email);
		SendOTP sentOTP = new SendOTP();
		sentOTP.setEmail(userEmail);
		sentOTP.setOtp(otp);
		otpRepo.save(sentOTP);
		return "OTP sent to "+ userEmail;
		}
		catch (Exception e) {
			System.out.println("Failed to send");
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
				
	}
	public String changePassword(ChangePasswordDTO changePasswordDTO) {
			Optional<SendOTP> orignalOTP = otpRepo.findById(changePasswordDTO.getEmail());
			if(orignalOTP.isEmpty()) {
				return "Resend OTP and try again";
			}
			else if(orignalOTP.get().getOtp() != changePasswordDTO.getOtp()) {
				return "OTP does not match try again";
			}else {
				otpRepo.delete(orignalOTP.get());
				return farmerClient.changePassword(changePasswordDTO.getEmail(), changePasswordDTO.getNewPassword());
			}
	}
	
}
