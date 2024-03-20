package com.farmer.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.farmer.dtos.CropDetailDTO;
import com.farmer.dtos.UpdateDetailDTO;
import com.farmer.model.Crop;
import com.farmer.model.Farmer;
import com.farmer.model.Transaction;
import com.farmer.service.FarmerServiceImpl;

public class FarmerControllerTest {

    @Mock
    private FarmerServiceImpl farmerService;

    @InjectMocks
    private FarmerController farmerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterFarmer() {
        // Arrange
        Farmer farmerToAdd = new Farmer();
        when(farmerService.addFarmer(farmerToAdd)).thenReturn(farmerToAdd);

        // Act
        ResponseEntity<Farmer> response = farmerController.registerFarmer(farmerToAdd);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmerToAdd, response.getBody());
    }

    @Test
    public void testAddCrop() {
        // Arrange
        Crop cropToAdd = new Crop();
        String email = "farmer@example.com";
        Farmer farmer = new Farmer(); // Create a Farmer object
        when(farmerService.addCrop(cropToAdd, email)).thenReturn(farmer);

        // Act
        ResponseEntity<Farmer> response = farmerController.addCrop(cropToAdd, email);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmer, response.getBody());
    }

    // Add test cases for the remaining methods
    @Test
    public void testUpdateFarmerWithEmail() {
        // Arrange
        UpdateDetailDTO update = new UpdateDetailDTO();
        String email = "farmer@example.com";
        when(farmerService.updateFarmer(update, email)).thenReturn(update);

        // Act
        ResponseEntity<UpdateDetailDTO> response = farmerController.updateFarmer(update, email);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(update, response.getBody());
    }

    @Test
    public void testUpdateFarmer() {
        // Arrange
        Farmer farmerToUpdate = new Farmer();
        when(farmerService.updateFarmer(farmerToUpdate)).thenReturn(farmerToUpdate);

        // Act
        ResponseEntity<Farmer> response = farmerController.updateFarmer(farmerToUpdate);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmerToUpdate, response.getBody());
    }

    @Test
    public void testGetAllFarmers() {
        // Arrange
        List<Farmer> allFarmers = new ArrayList<>(); // Create a list of farmers
        when(farmerService.getAll()).thenReturn(allFarmers);

        // Act
        ResponseEntity<List<Farmer>> response = farmerController.getAllFarmers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allFarmers, response.getBody());
    }

    @Test
    public void testFindFarmerById() {
        // Arrange
        String farmerId = "farmer123";
        Farmer farmer = new Farmer(); // Create a Farmer object
        when(farmerService.findFarmerById(farmerId)).thenReturn(farmer);

        // Act
        ResponseEntity<Farmer> response = farmerController.findFarmerById(farmerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmer, response.getBody());
    }

    @Test
    public void testFindFarmerByEmail() {
        // Arrange
        String email = "farmer@example.com";
        Farmer farmer = new Farmer(); // Create a Farmer object
        when(farmerService.findFarmerByEmail(email)).thenReturn(farmer);

        // Act
        ResponseEntity<Farmer> response = farmerController.findFarmerByEmail(email);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmer, response.getBody());
    }

    @Test
    public void testGetAllCrops() {
        // Arrange
        List<CropDetailDTO> allCrops = new ArrayList<>(); // Create a list of crop details
        when(farmerService.getAllCrops()).thenReturn(allCrops);

        // Act
        ResponseEntity<List<CropDetailDTO>> response = farmerController.getAllCrops();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(allCrops, response.getBody());
    }

    @Test
    public void testGetCropsByType() {
        // Arrange
        String cropType = "Wheat"; // Replace with a valid crop type
        List<CropDetailDTO> cropsByType = new ArrayList<>(); // Create a list of crop details for the specified type
        when(farmerService.getCropsByCropType(cropType)).thenReturn(cropsByType);

        // Act
        ResponseEntity<List<CropDetailDTO>> response = farmerController.getCropsByType(cropType);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cropsByType, response.getBody());
    }

    @Test
    public void testGetFarmerHistory() {
        // Arrange
        String farmerId = "farmer123"; // Replace with a valid farmer ID
        List<Transaction> farmerTransactionHistory = new ArrayList<>(); // Create a list of transactions
        when(farmerService.findTransactionsByFarmerId(farmerId)).thenReturn(farmerTransactionHistory);

        // Act
        ResponseEntity<List<Transaction>> response = farmerController.getFarmerHistory(farmerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmerTransactionHistory, response.getBody());
    }

    @Test
    public void testDeleteFarmerById() {
        // Arrange
        String farmerId = "farmer123"; // Replace with a valid farmer ID
        Farmer farmer = new Farmer(); // Create a Farmer object
        when(farmerService.deleteFarmerById(farmerId)).thenReturn(farmer);

        // Act
        ResponseEntity<Farmer> response = farmerController.deleteFarmerById(farmerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmer, response.getBody());
    }

    @Test
    public void testChangePassword() {
        // Arrange
        String email = "farmer@example.com"; // Replace with a valid email
        String newPassword = "newPassword"; // Replace with the desired new password
        String resultMessage = "Password changed successfully"; // Replace with the expected result message
        when(farmerService.changePassword(email, newPassword)).thenReturn(resultMessage);

        // Act
        ResponseEntity<String> response = farmerController.changePassword(email, newPassword);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(resultMessage, response.getBody());
    }

    @Test
    public void testUpdateRating() {
        // Arrange
        String farmerId = "farmer123"; // Replace with a valid farmer ID
        int rating = 5; // Replace with the desired rating
        String resultMessage = "Rating updated successfully"; // Replace with the expected result message
        when(farmerService.updateRating(farmerId, rating)).thenReturn(resultMessage);

        // Act
        ResponseEntity<String> response = farmerController.updateRating(farmerId, rating);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(resultMessage, response.getBody());
    }

}

