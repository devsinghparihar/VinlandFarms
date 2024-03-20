package com.dealer.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

import com.dealer.clients.FarmerClient;
import com.dealer.dtos.CropDetailDTO;
import com.dealer.dtos.UpdateDetailDTO;
import com.dealer.exceptions.ResourceNotFoundException;
import com.dealer.model.Dealer;
import com.dealer.model.Transaction;
import com.dealer.service.DealerServiceImpl;

public class DealerControllerTest {

    @Mock
    private DealerServiceImpl dealerService;

    @Mock
    private FarmerClient farmerClient;

    @InjectMocks
    private DealerController dealerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterDealer() {
        // Arrange
        Dealer dealerToAdd = new Dealer();
        when(dealerService.addDealer(any(Dealer.class))).thenReturn(dealerToAdd);

        // Act
        ResponseEntity<Dealer> response = dealerController.registerDealer(dealerToAdd);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dealerToAdd, response.getBody());
    }

    @Test
    public void testAddRequirement() {
        // Arrange
        String requirement = "Requirement";
        String email = "dealer@example.com";
        when(dealerService.addRequirement(eq(requirement), eq(email))).thenReturn(new Dealer());

        // Act
        ResponseEntity<Dealer> response = dealerController.addRequirement(requirement, email);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdateDealer() {
        // Arrange
        UpdateDetailDTO updateDetail = new UpdateDetailDTO();
        String email = "dealer@example.com";
        when(dealerService.updateDealer(any(UpdateDetailDTO.class), eq(email))).thenReturn(updateDetail);

        // Act
        ResponseEntity<UpdateDetailDTO> response = dealerController.updateDealer(updateDetail, email);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updateDetail, response.getBody());
    }

    @Test
    public void testAddMoneyToWallet() {
        // Arrange
        String dealerId = "dealer123";
        long amount = 100;
        when(dealerService.addMoneyToWallet(eq(dealerId), eq(amount))).thenReturn("Added");

        // Act
        ResponseEntity<String> response = dealerController.addMoneyToWallet(dealerId, amount);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Added", response.getBody());
    }

    @Test
    public void testGetAllDealers() {
        // Arrange
        List<Dealer> dealers = new ArrayList<>();
        when(dealerService.getAll()).thenReturn(dealers);

        // Act
        ResponseEntity<List<Dealer>> response = dealerController.getAllDealers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dealers, response.getBody());
    }

    @Test
    public void testGetAllCrops() {
        // Arrange
        List<CropDetailDTO> crops = new ArrayList<>();
        when(farmerClient.getAllCrops()).thenReturn(crops);

        // Act
        ResponseEntity<List<CropDetailDTO>> response = dealerController.getAllCrops();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(crops, response.getBody());
    }

    @Test
    public void testGetCropsByType() throws ResourceNotFoundException {
        // Arrange
        String cropType = "Type";
        List<CropDetailDTO> crops = new ArrayList<>();
        when(farmerClient.getCropsByType(cropType)).thenReturn(crops);

        // Act
        ResponseEntity<List<CropDetailDTO>> response = dealerController.getCropsByType(cropType);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(crops, response.getBody());
    }

    @Test
    public void testFindDealerById() {
        // Arrange
        String dealerId = "dealer123";
        Dealer dealer = new Dealer();
        when(dealerService.findDealerById(dealerId)).thenReturn(dealer);

        // Act
        ResponseEntity<Dealer> response = dealerController.findDealerById(dealerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dealer, response.getBody());
    }

    @Test
    public void testFindDealerByEmail() {
        // Arrange
        String email = "dealer@example.com";
        Dealer dealer = new Dealer();
        when(dealerService.findDealerByEmail(email)).thenReturn(dealer);

        // Act
        ResponseEntity<Dealer> response = dealerController.findDealerByEmail(email);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dealer, response.getBody());
    }
    @Test
    public void testBuyCrop() {
        // Arrange
        String farmerId = "farmer123";
        String dealerId = "dealer123";
        String cropType = "Wheat";
        int quantity = 10;
        Transaction transaction = new Transaction();
        when(dealerService.buyCrop(farmerId, dealerId, cropType, quantity)).thenReturn(transaction);

        // Act
        ResponseEntity<Transaction> response = dealerController.buyCrop(farmerId, dealerId, cropType, quantity);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaction, response.getBody());
    }

    @Test
    public void testGetDealerHistory() {
        // Arrange
        String dealerId = "dealer123";
        List<Transaction> transactions = new ArrayList<>();
        when(dealerService.findTransactionsByDealerId(dealerId)).thenReturn(transactions);

        // Act
        ResponseEntity<List<Transaction>> response = dealerController.getDealerHistory(dealerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
    }

    @Test
    public void testFindRequirements() {
        // Arrange
        String dealerId = "dealer123";
        List<String> requirements = new ArrayList<>();
        when(dealerService.findRequirements(dealerId)).thenReturn(requirements);

        // Act
        ResponseEntity<List<String>> response = dealerController.findRequirements(dealerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(requirements, response.getBody());
    }

    @Test
    public void testDeleteDealerById() {
        // Arrange
        String dealerId = "dealer123";
        Dealer dealer = new Dealer();
        when(dealerService.deleteDealerById(dealerId)).thenReturn(dealer);

        // Act
        ResponseEntity<Dealer> response = dealerController.deleteDealerById(dealerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dealer, response.getBody());
    }

    @Test
    public void testUpdateFarmerRating() {
        // Arrange
        String farmerId = "farmer123";
        int rating = 5;
        when(dealerService.updateFarmerRating(farmerId, rating)).thenReturn("Rating Updated");

        // Act
        ResponseEntity<String> response = dealerController.updateFarmerRating(farmerId, rating);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Rating Updated", response.getBody());
    }

    @Test
    public void testChangePassword() {
        // Arrange
        String email = "dealer@example.com";
        String password = "newPassword";
        when(dealerService.changePassword(email, password)).thenReturn("Password Changed");

        // Act
        ResponseEntity<String> response = dealerController.changePassword(email, password);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Password Changed", response.getBody());
    }
}

