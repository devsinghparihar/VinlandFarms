package com.emsjwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emsjwt.clients.AdminClient;
import com.emsjwt.dtos.DealerUpdateDTO;
import com.emsjwt.dtos.FarmerRatingDTO;
import com.emsjwt.dtos.FarmerUpdateDTO;
import com.emsjwt.model.Admin;
import com.emsjwt.model.Dealer;
import com.emsjwt.model.Farmer;
import com.emsjwt.model.Transaction;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
	@Autowired
    private AdminClient adminClient;
	@Autowired
	PasswordEncoder encoder;

    

    @PutMapping("/update/{id}")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin, @PathVariable String id) {
    	String password = encoder.encode(admin.getPassword());
    	admin.setPassword(password);
        Admin updatedAdmin = adminClient.updateAdmin(admin, id);
        return ResponseEntity.ok(updatedAdmin);
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<Admin> findByEmail(@PathVariable String email) {
        Admin foundAdmin = adminClient.findByEmail(email);
        return ResponseEntity.ok(foundAdmin);
    }

    @GetMapping("/activeFarmers")
    public ResponseEntity<List<Farmer>> getActiveFarmers() {
        List<Farmer> activeFarmers = adminClient.getActiveFarmers();
        return ResponseEntity.ok(activeFarmers);
    }

    @GetMapping("/inactiveFarmers")
    public ResponseEntity<List<Farmer>> getInactiveFarmers() {
        List<Farmer> inactiveFarmers = adminClient.getInactiveFarmers();
        return ResponseEntity.ok(inactiveFarmers);
    }

    @GetMapping("/activeDealers")
    public ResponseEntity<List<Dealer>> getActiveDealers() {
        List<Dealer> activeDealers = adminClient.getActiveDealers();
        return ResponseEntity.ok(activeDealers);
    }

    @GetMapping("/inactiveDealers")
    public ResponseEntity<List<Dealer>> getInactiveDealers() {
        List<Dealer> inactiveDealers = adminClient.getInactiveDealers();
        return ResponseEntity.ok(inactiveDealers);
    }

    @PutMapping("/updateFarmer/{email}")
    public ResponseEntity<FarmerUpdateDTO> updateFarmer(@RequestBody FarmerUpdateDTO farmerUpdateDTO, @PathVariable String email) {
//    	 
        FarmerUpdateDTO updatedFarmer = adminClient.updateFarmer(farmerUpdateDTO, email);
        return ResponseEntity.ok(updatedFarmer);
    }

    @PutMapping("/updateDealer/{email}")
    public ResponseEntity<DealerUpdateDTO> updateDealer(@RequestBody DealerUpdateDTO dealerUpdateDTO, @PathVariable String email) {
//    	String password = encoder.encode(dealerUpdateDTO.getPassword());
//    	dealerUpdateDTO.setPassword(password);
        DealerUpdateDTO updatedDealer = adminClient.updateDealer(dealerUpdateDTO, email);
        return ResponseEntity.ok(updatedDealer);
    }

    @GetMapping("/farmersRatings")
    public ResponseEntity<List<FarmerRatingDTO>> getFarmersRatings() {
        List<FarmerRatingDTO> farmersRatings = adminClient.getFarmersRatings();
        return ResponseEntity.ok(farmersRatings);
    }

    @DeleteMapping("/deleteFarmerById/{id}")
	public ResponseEntity<Farmer> deleteFarmerById(@PathVariable String id){
		return new ResponseEntity<Farmer>(adminClient.deleteFarmerById(id),HttpStatus.OK);
	}
    @GetMapping("/getAllTransactions")
    public ResponseEntity<List<Transaction>> getAllTransaction(){
    	return new ResponseEntity<List<Transaction>>(adminClient.getAllTransaction(),HttpStatus.OK);
    }
}
