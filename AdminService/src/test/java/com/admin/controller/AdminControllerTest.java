package com.admin.controller;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.admin.dtos.DealerUpdateDTO;
import com.admin.dtos.FarmerRatingDTO;
import com.admin.dtos.FarmerUpdateDTO;
import com.admin.model.Admin;
import com.admin.model.Dealer;
import com.admin.model.Farmer;
import com.admin.model.Transaction;
import com.admin.service.AdminService;

@SpringBootTest
public class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    public void setUp() {
    	 MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddAdmin() {
        // Arrange
        Admin adminToAdd = new Admin();
        when(adminService.addAdmin(any(Admin.class))).thenReturn(adminToAdd);

        // Act
        ResponseEntity<Admin> response = adminController.addAdmin(adminToAdd);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adminToAdd, response.getBody());
    }

    @Test
    public void testUpdateAdmin() {
        // Arrange
        Admin adminToUpdate = new Admin();
        String adminId = "admin123";
        when(adminService.updateAdmin(eq(adminToUpdate), eq(adminId))).thenReturn(adminToUpdate);

        // Act
        ResponseEntity<Admin> response = adminController.updateAdmin(adminToUpdate, adminId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adminToUpdate, response.getBody());
    }

    @Test
    public void testFindByEmail() {
        // Arrange
        String email = "admin@example.com";
        Admin admin = new Admin();
        when(adminService.findAdminByEmail(eq(email))).thenReturn(admin);

        // Act
        ResponseEntity<Admin> response = adminController.findByEmail(email);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(admin, response.getBody());
    }
    @Test
    public void testGetActiveFarmers() {
        // Arrange
        List<Farmer> activeFarmers = new ArrayList<>(); // Create a list of active farmers
        when(adminService.getActiveFarmers()).thenReturn(activeFarmers);

        // Act
        ResponseEntity<List<Farmer>> response = adminController.getActiveFarmers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(activeFarmers, response.getBody());
    }

    @Test
    public void testGetInactiveFarmers() {
        // Arrange
        List<Farmer> inactiveFarmers = new ArrayList<>(); // Create a list of inactive farmers
        when(adminService.getInactiveFarmers()).thenReturn(inactiveFarmers);

        // Act
        ResponseEntity<List<Farmer>> response = adminController.getInactiveFarmers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(inactiveFarmers, response.getBody());
    }

    @Test
    public void testGetActiveDealers() {
        // Arrange
        List<Dealer> activeDealers = new ArrayList<>(); // Create a list of active dealers
        when(adminService.getActiveDealers()).thenReturn(activeDealers);

        // Act
        ResponseEntity<List<Dealer>> response = adminController.getActiveDealers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(activeDealers, response.getBody());
    }

    @Test
    public void testGetInactiveDealers() {
        // Arrange
        List<Dealer> inactiveDealers = new ArrayList<>(); // Create a list of inactive dealers
        when(adminService.getInactiveDealers()).thenReturn(inactiveDealers);

        // Act
        ResponseEntity<List<Dealer>> response = adminController.getInactiveDealers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(inactiveDealers, response.getBody());
    }

    @Test
    public void testUpdateFarmer() {
        // Arrange
        FarmerUpdateDTO farmerUpdateDTO = new FarmerUpdateDTO();
        String email = "farmer@example.com";
        when(adminService.updateFarmer(eq(farmerUpdateDTO), eq(email))).thenReturn(farmerUpdateDTO);

        // Act
        ResponseEntity<FarmerUpdateDTO> response = adminController.updateFarmer(farmerUpdateDTO, email);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmerUpdateDTO, response.getBody());
    }

    @Test
    public void testUpdateDealer() {
        // Arrange
        DealerUpdateDTO dealerUpdateDTO = new DealerUpdateDTO();
        String email = "dealer@example.com";
        when(adminService.updateDealer(eq(dealerUpdateDTO), eq(email))).thenReturn(dealerUpdateDTO);

        // Act
        ResponseEntity<DealerUpdateDTO> response = adminController.updateDealer(dealerUpdateDTO, email);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dealerUpdateDTO, response.getBody());
    }

    @Test
    public void testGetFarmersRatings() {
        // Arrange
        List<FarmerRatingDTO> farmersRatings = new ArrayList<>(); // Create a list of farmer ratings
        when(adminService.getFarmersRatings()).thenReturn(farmersRatings);

        // Act
        ResponseEntity<List<FarmerRatingDTO>> response = adminController.getFarmersRatings();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmersRatings, response.getBody());
    }

    @Test
    public void testDeleteFarmerById() {
        // Arrange
        String farmerId = "farmer123";
        Farmer farmer = new Farmer(); // Create a Farmer object
        when(adminService.deleteFarmerById(eq(farmerId))).thenReturn(farmer);

        // Act
        ResponseEntity<Farmer> response = adminController.deleteFarmerById(farmerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmer, response.getBody());
    }

    @Test
    public void testDeleteDealerById() {
        // Arrange
        String dealerId = "dealer123";
        Dealer dealer = new Dealer(); // Create a Dealer object
        when(adminService.deleteDealerById(eq(dealerId))).thenReturn(dealer);

        // Act
        ResponseEntity<Dealer> response = adminController.deleteDealerById(dealerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dealer, response.getBody());
    }

    @Test
    public void testGetAllTransaction() {
        // Arrange
        List<Transaction> transactions = new ArrayList<>(); // Create a list of transactions
        when(adminService.getAllTransaction()).thenReturn(transactions);

        // Act
        ResponseEntity<List<Transaction>> response = adminController.getAllTransaction();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
    }


}
