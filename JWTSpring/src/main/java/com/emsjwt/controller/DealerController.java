package com.emsjwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emsjwt.clients.DealerClient;
import com.emsjwt.dtos.CropDetailDTO;
import com.emsjwt.dtos.DealerUpdateDTO;
import com.emsjwt.model.Dealer;
import com.emsjwt.model.Transaction;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/dealer")
@PreAuthorize("hasRole('DEALER')")
public class DealerController {
	
	@Autowired
    private DealerClient dealerClient;
	@Autowired
	PasswordEncoder encoder;

    

    @PostMapping("/addRequirement/{requirement}/{email}")
    public ResponseEntity<Dealer> addRequirement(@PathVariable String requirement, @PathVariable String email) {
        Dealer dealer = dealerClient.addRequirement(requirement, email);
        return ResponseEntity.ok(dealer);
    }

    @PostMapping("/updateDealer/{email}")
    public ResponseEntity<DealerUpdateDTO> updateDealer(@RequestBody DealerUpdateDTO update, @PathVariable String email) {
//    	String password = encoder.encode(update.getPassword());
//    	update.setPassword(password);
        DealerUpdateDTO updatedDealer = dealerClient.updateDealer(update, email);
        return ResponseEntity.ok(updatedDealer);
    }

    @GetMapping("/getAllDealers")
    public ResponseEntity<List<Dealer>> getAllDealers() {
        List<Dealer> dealers = dealerClient.getAllDealers();
        return ResponseEntity.ok(dealers);
    }

    @GetMapping("/searchAllCrops")
    public ResponseEntity<List<CropDetailDTO>> getAllCrops() {
        List<CropDetailDTO> crops = dealerClient.getAllCrops();
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/searchCropsByType/{type}")
    public ResponseEntity<List<CropDetailDTO>> getCropsByType(@PathVariable String type) {
        List<CropDetailDTO> crops = dealerClient.getCropsByType(type);
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/findDealerById/{Id}")
    public ResponseEntity<Dealer> findDealerById(@PathVariable String Id) {
        Dealer dealer = dealerClient.findDealerrById(Id);
        return ResponseEntity.ok(dealer);
    }

    @GetMapping("/findDealerByEmail/{email}")
    public ResponseEntity<Dealer> findDealerByEmail(@PathVariable String email) {
        Dealer dealer = dealerClient.findDealerByEmail(email);
        return ResponseEntity.ok(dealer);
    }

    @PostMapping("/buyCrop/{farmerId}/{dealerId}/{cropType}/{quantity}")
    public ResponseEntity<Transaction> buyCrop(
            @PathVariable String farmerId,
            @PathVariable String dealerId,
            @PathVariable String cropType,
            @PathVariable int quantity
    ) {
        Transaction transaction = dealerClient.buyCrop(farmerId, dealerId, cropType, quantity);
        return ResponseEntity.ok(transaction);
    }
    
    @PutMapping("/addMoneyToWallet/{id}/{amount}")
	public ResponseEntity<String> addMoneyToWallet(@PathVariable String id, @PathVariable long amount ){
		return new ResponseEntity<String>(dealerClient.addMoneyToWallet(id, amount), HttpStatus.OK); 
		
	}

    @GetMapping("/dealerTransactionHistory/{id}")
    public ResponseEntity<List<Transaction>> getDealerHistory(@PathVariable String id) {
        List<Transaction> history = dealerClient.getDealerHistory(id);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/findRequirements/{id}")
    public ResponseEntity<List<String>> findRequirements(@PathVariable String id) {
        List<String> requirements = dealerClient.findRequirements(id);
        return ResponseEntity.ok(requirements);
    }
}
