package com.emsjwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.emsjwt.clients.FarmerClient;
import com.emsjwt.dtos.CropDetailDTO;
import com.emsjwt.dtos.FarmerUpdateDTO;
import com.emsjwt.model.Crop;
import com.emsjwt.model.Farmer;
import com.emsjwt.model.Transaction;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/farmer")
@PreAuthorize("hasRole('FARMER')")
public class FarmerController {

	@Autowired
    private FarmerClient farmerClient;
	@Autowired
	PasswordEncoder encoder;

   

    

    @PostMapping("/addCrop/{email}")
    public ResponseEntity<Farmer> addCrop(@RequestBody Crop crop, @PathVariable String email) {
        Farmer farmer = farmerClient.addCrop(crop, email);
        return ResponseEntity.ok(farmer);
    }

    @PutMapping("/updateFarmer/{email}")
    public ResponseEntity<FarmerUpdateDTO> updateFarmer(@RequestBody FarmerUpdateDTO update, @PathVariable String email) {
//    	String password = encoder.encode(update.getPassword());
//    	update.setPassword(password);
        FarmerUpdateDTO updatedFarmer = farmerClient.updateFarmer(update, email);
        return ResponseEntity.ok(updatedFarmer);
    }

    @PutMapping("/updateFarmer")
    public ResponseEntity<Farmer> updateFarmer(@RequestBody Farmer farmer) {
        Farmer updatedFarmer = farmerClient.updateFarmer(farmer);
        return ResponseEntity.ok(updatedFarmer);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Farmer>> getAllFarmers() {
        List<Farmer> farmers = farmerClient.getAllFarmers();
        return ResponseEntity.ok(farmers);
    }

    @GetMapping("/findFarmerById/{Id}")
    public ResponseEntity<Farmer> findFarmerById(@PathVariable String Id) {
        Farmer farmer = farmerClient.findFarmerById(Id);
        return ResponseEntity.ok(farmer);
    }

    @GetMapping("/findFarmerByEmail/{email}")
    public ResponseEntity<Farmer> findFarmerByEmail(@PathVariable String email) {
        Farmer farmer = farmerClient.findFarmerByEmail(email);
        return ResponseEntity.ok(farmer);
    }

    @GetMapping("/getAllCrops")
    public ResponseEntity<List<CropDetailDTO>> getAllCrops() {
        List<CropDetailDTO> crops = farmerClient.getAllCrops();
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/getCropsByType/{type}")
    public ResponseEntity<List<CropDetailDTO>> getCropsByType(@PathVariable String type) {
        List<CropDetailDTO> crops = farmerClient.getCropsByType(type);
        return ResponseEntity.ok(crops);
    }

    @GetMapping("/farmerTransactionHistory/{id}")
    public ResponseEntity<List<Transaction>> getFarmerHistory(@PathVariable String id) {
        List<Transaction> history = farmerClient.getFarmerHistory(id);
        return ResponseEntity.ok(history);
    }
}