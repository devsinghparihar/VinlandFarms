package com.emsjwt.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emsjwt.clients.AdminClient;
import com.emsjwt.clients.DealerClient;
import com.emsjwt.clients.FarmerClient;
import com.emsjwt.dtos.ChangePasswordDTO;
import com.emsjwt.jwt.JwtUtility;
import com.emsjwt.model.Admin;
import com.emsjwt.model.Dealer;
import com.emsjwt.model.Farmer;
import com.emsjwt.model.Login;
import com.emsjwt.repository.UserRepository;
import com.emsjwt.request.LoginRequest;
import com.emsjwt.response.JSONResponse;
import com.emsjwt.service.UserDetailsImpl;
import com.emsjwt.service.UserDetailsServiceImpl;

import jakarta.validation.Valid;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/public")
public class AuthController {
	
	@Autowired
	DaoAuthenticationProvider dap;
	@Autowired
	JwtUtility util;
	
	@Autowired
	FarmerClient farmerClient;
	@Autowired
	DealerClient dealerClient;
	@Autowired
	AdminClient adminClient;
	@Autowired
	UserDetailsServiceImpl userService;
	
	@Autowired
	PasswordEncoder encoder;
	@PostMapping("/signin")
	public ResponseEntity<?> validateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication auth = dap.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
		String token = util.generateToken(auth);
		
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		List<String> li=authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		JSONResponse res = new JSONResponse(token, user.getUsername(), li,user.getId());		
		return ResponseEntity.ok(res);
						
													
	}

	
	@PostMapping("/registerFarmer")
    public ResponseEntity<Farmer> registerFarmer(@Valid @RequestBody Farmer farmer) {
		String password = encoder.encode(farmer.getPassword());
		farmer.setPassword(password);
        Farmer registeredFarmer = farmerClient.registerFarmer(farmer);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredFarmer);
    }
	@PostMapping("/registerDealer")
    public ResponseEntity<Dealer> registerDealer(@Valid @RequestBody Dealer dealer) {
		String password = encoder.encode(dealer.getPassword());
		dealer.setPassword(password);
        Dealer registeredDealer = dealerClient.registerDealer(dealer);
        return ResponseEntity.ok(registeredDealer);
    }
	@PostMapping("/registerAdmin")
    public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin admin) {
		String password = encoder.encode(admin.getPassword());
		admin.setPassword(password);
        Admin addedAdmin = adminClient.addAdmin(admin);
        return ResponseEntity.ok(addedAdmin);
    }
	@GetMapping("/sendOTP/{email}")
	public ResponseEntity<String> sendOTP(@PathVariable String email){
		return new ResponseEntity<String>(userService.sendOTP(email),HttpStatus.OK);
	}
	@PostMapping("/changeFarmerPassword")
	public ResponseEntity<String> changeFarmerPassword(@Valid @RequestBody ChangePasswordDTO password){
		password.setNewPassword(encoder.encode(password.getNewPassword()));
		return new ResponseEntity<String>(userService.changePassword(password),HttpStatus.OK);
	}

}