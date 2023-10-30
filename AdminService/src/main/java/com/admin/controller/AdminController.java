package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dtos.DealerUpdateDTO;
import com.admin.dtos.FarmerRatingDTO;
import com.admin.dtos.FarmerUpdateDTO;
import com.admin.model.Admin;
import com.admin.model.Dealer;
import com.admin.model.Farmer;
import com.admin.model.Transaction;
import com.admin.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

	@Autowired
    private  AdminService adminService;

    

    @PostMapping("/add")
    public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin admin) {
        Admin addedAdmin = adminService.addAdmin(admin);
        return new ResponseEntity<>(addedAdmin, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin, @PathVariable String id) {
        Admin updatedAdmin = adminService.updateAdmin(admin, id);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }
    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<Admin> findByEmail(@PathVariable String email){
    	return new ResponseEntity<Admin>(adminService.findAdminByEmail(email), HttpStatus.OK); 
    }

    @GetMapping("/activeFarmers")
    public ResponseEntity<List<Farmer>> getActiveFarmers() {
        List<Farmer> activeFarmers = adminService.getActiveFarmers();
        return new ResponseEntity<>(activeFarmers, HttpStatus.OK);
    }

    @GetMapping("/inactiveFarmers")
    public ResponseEntity<List<Farmer>> getInactiveFarmers() {
        List<Farmer> inactiveFarmers = adminService.getInactiveFarmers();
        return new ResponseEntity<>(inactiveFarmers, HttpStatus.OK);
    }

    @GetMapping("/activeDealers")
    public ResponseEntity<List<Dealer>> getActiveDealers() {
        List<Dealer> activeDealers = adminService.getActiveDealers();
        return new ResponseEntity<>(activeDealers, HttpStatus.OK);
    }

    @GetMapping("/inactiveDealers")
    public ResponseEntity<List<Dealer>> getInactiveDealers() {
        List<Dealer> inactiveDealers = adminService.getInactiveDealers();
        return new ResponseEntity<>(inactiveDealers, HttpStatus.OK);
    }

    @PutMapping("/updateFarmer/{email}")
    public ResponseEntity<FarmerUpdateDTO> updateFarmer(@Valid @RequestBody FarmerUpdateDTO farmerUpdateDTO, @PathVariable String email) {
        FarmerUpdateDTO updatedFarmer = adminService.updateFarmer(farmerUpdateDTO, email);
        return new ResponseEntity<>(updatedFarmer, HttpStatus.OK);
    }

    @PutMapping("/updateDealer/{email}")
    public ResponseEntity<DealerUpdateDTO> updateDealer(@Valid @RequestBody DealerUpdateDTO dealerUpdateDTO, @PathVariable String email) {
        DealerUpdateDTO updatedDealer = adminService.updateDealer(dealerUpdateDTO, email);
        return new ResponseEntity<>(updatedDealer, HttpStatus.OK);
    }

    @GetMapping("/farmersRatings")
    public ResponseEntity<List<FarmerRatingDTO>> getFarmersRatings() {
        List<FarmerRatingDTO> farmersRatings = adminService.getFarmersRatings();
        return new ResponseEntity<>(farmersRatings, HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteFarmerById/{id}")
	public ResponseEntity<Farmer> deleteFarmerById(@PathVariable String id){
		return new ResponseEntity<Farmer>(adminService.deleteFarmerById(id),HttpStatus.OK);
	}
    @DeleteMapping("/deleteDealerById/{id}")
	public ResponseEntity<Dealer> deleteDealerById(@PathVariable String id){
		return new ResponseEntity<Dealer>(adminService.deleteDealerById(id),HttpStatus.OK);
	}
    @GetMapping("/getAllTransactions")
    public ResponseEntity<List<Transaction>> getAllTransaction(){
    	return new ResponseEntity<>(adminService.getAllTransaction(),HttpStatus.OK);
    }
    	
}

