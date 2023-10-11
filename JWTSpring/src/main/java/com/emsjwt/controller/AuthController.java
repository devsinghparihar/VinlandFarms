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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emsjwt.clients.AdminClient;
import com.emsjwt.clients.DealerClient;
import com.emsjwt.clients.EmployeeClient;
import com.emsjwt.clients.FarmerClient;
import com.emsjwt.jwt.JwtUtility;
import com.emsjwt.model.Admin;
import com.emsjwt.model.Dealer;
import com.emsjwt.model.EmployeeModel;
import com.emsjwt.model.Farmer;
import com.emsjwt.model.Login;
import com.emsjwt.repository.UserRepository;
import com.emsjwt.request.LoginRequest;
import com.emsjwt.response.JSONResponse;
import com.emsjwt.service.UserDetailsImpl;

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
		JSONResponse res = new JSONResponse(token, user.getUsername(), li);		
		return ResponseEntity.ok(res);
						
													
	}
//	@PostMapping("/register")
//	public ResponseEntity<EmployeeModel> register(@Valid @RequestBody EmployeeModel login){
////		Login user = new Login(login.getUsername(),login.getPassword(),"ROLE_USER");
//		String password = encoder.encode(login.getPassword());
//		login.setPassword(password);
//		return new ResponseEntity<EmployeeModel>(employeeClient.addEmployee(login), HttpStatus.OK);
//		
//	}
//	@GetMapping("/test")
//	public String test() {
//		return "Success";
//	}
	
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

}