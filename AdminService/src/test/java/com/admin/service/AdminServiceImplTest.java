package com.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.admin.clients.DealerClient;
import com.admin.clients.FarmerClient;
import com.admin.clients.TransactionClient;
import com.admin.dtos.DealerUpdateDTO;
import com.admin.dtos.FarmerRatingDTO;
import com.admin.dtos.FarmerUpdateDTO;
import com.admin.exceptions.EmailAlreadyExists;
import com.admin.exceptions.ResourceNotFoundException;
import com.admin.model.Admin;
import com.admin.model.Crop;
import com.admin.model.Dealer;
import com.admin.model.Farmer;
import com.admin.model.Transaction;
import com.admin.repository.AdminRepository;



@SpringBootTest
public class AdminServiceImplTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private FarmerClient farmerClient;

    @Mock
    private DealerClient dealerClient;

    @Mock
    private TransactionClient transactionClient;

    @InjectMocks
    private AdminServiceImpl adminService;

    @BeforeEach
    public void setUp() {
    	 MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddAdmin() {
        // Arrange
        Admin admin = new Admin();
        admin.setName("John Doe");
        admin.setEmail("johndoe@example.com");
        admin.setPassword("password");
        admin.setGender("Male");

        when(adminRepository.findByEmail(admin.getEmail())).thenReturn(null);
        when(adminRepository.save(admin)).thenReturn(admin);

        // Act
        Admin result = adminService.addAdmin(admin);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("johndoe@example.com", result.getEmail());
        assertEquals("Male", result.getGender());
        verify(adminRepository, times(1)).findByEmail("johndoe@example.com");
        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    public void testAddAdminWithDuplicateEmail() {
        // Arrange
        Admin admin = new Admin();
        admin.setName("John Doe");
        admin.setEmail("johndoe@example.com");
        admin.setPassword("password");
        admin.setGender("Male");

        when(adminRepository.findByEmail(admin.getEmail())).thenReturn(Optional.of(admin));

        // Act and Assert
        assertThrows(EmailAlreadyExists.class, () -> adminService.addAdmin(admin));
        verify(adminRepository, times(1)).findByEmail("johndoe@example.com");
    }

    @Test
    public void testGetActiveFarmers() {
        // Arrange
        Farmer activeFarmer1 = new Farmer();
        activeFarmer1.setCrops(new ArrayList<>());

        Farmer activeFarmer2 = new Farmer();
        List<Crop> crops = new ArrayList<>();
        crops.add(new Crop());
        activeFarmer2.setCrops(crops);

        List<Farmer> allFarmers = new ArrayList<>();
        allFarmers.add(activeFarmer1);
        allFarmers.add(activeFarmer2);

        when(farmerClient.getAllFarmers()).thenReturn(allFarmers);

        // Act
        List<Farmer> result = adminService.getActiveFarmers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size()); // Only one farmer with active crops
        verify(farmerClient, times(1)).getAllFarmers();
    }

    @Test
    public void testGetInactiveFarmers() {
        // Arrange
        Farmer inactiveFarmer1 = new Farmer();
        inactiveFarmer1.setCrops(new ArrayList<>());

        Farmer inactiveFarmer2 = new Farmer();
        List<Crop> crops = new ArrayList<>();
        crops.add(new Crop());
        inactiveFarmer2.setCrops(crops);

        List<Farmer> allFarmers = new ArrayList<>();
        allFarmers.add(inactiveFarmer1);
        allFarmers.add(inactiveFarmer2);

        when(farmerClient.getAllFarmers()).thenReturn(allFarmers);

        // Act
        List<Farmer> result = adminService.getInactiveFarmers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size()); // Only one farmer with no crops
        verify(farmerClient, times(1)).getAllFarmers();
    }

    @Test
    public void testGetActiveDealers() {
        // Arrange
        Farmer activeFarmer = new Farmer();
        List<Crop> crops = new ArrayList<>();
        crops.add(new Crop());
        activeFarmer.setCrops(crops);

        Farmer inactiveFarmer = new Farmer();
        inactiveFarmer.setCrops(new ArrayList<>());

        Dealer activeDealer = new Dealer();
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setDealerId("dealer123");
        transactions.add(transaction);
        activeDealer.setRequirements(new ArrayList<>());
        activeDealer.setAccountBalance(1000L);

        Dealer inactiveDealer = new Dealer();
        inactiveDealer.setRequirements(new ArrayList<>());
        inactiveDealer.setAccountBalance(0L);

        List<Farmer> allFarmers = new ArrayList<>();
        allFarmers.add(activeFarmer);
        allFarmers.add(inactiveFarmer);

        List<Dealer> allDealers = new ArrayList<>();
        allDealers.add(activeDealer);
        allDealers.add(inactiveDealer);

        when(farmerClient.getAllFarmers()).thenReturn(allFarmers);
        when(dealerClient.getAllDealers()).thenReturn(allDealers);
        when(transactionClient.getAllTransactions()).thenReturn(transactions);

        // Act
        List<Dealer> result = adminService.getActiveDealers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size()); // Only one dealer with active transactions
        verify(farmerClient, times(1)).getAllFarmers();
        verify(dealerClient, times(1)).getAllDealers();
        verify(transactionClient, times(1)).getAllTransactions();
    }
    @Test
    public void testUpdateAdmin() {
        // Arrange
        Admin admin = new Admin();
        admin.setAdminId("admin123");
        admin.setName("John Doe");
        admin.setEmail("johndoe@example.com");
        admin.setPassword("password");
        admin.setGender("Male");

        when(adminRepository.findById(admin.getAdminId())).thenReturn(java.util.Optional.of(admin));
        when(adminRepository.save(admin)).thenReturn(admin);

        // Act
        Admin result = adminService.updateAdmin(admin, admin.getAdminId());

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("johndoe@example.com", result.getEmail());
        assertEquals("Male", result.getGender());
        verify(adminRepository, times(2)).findById("admin123");
        verify(adminRepository, times(1)).save(admin);
    }

    // Test for updateAdmin with a non-existent ID
    @Test
    public void testUpdateAdminWithNonExistentId() {
        // Arrange
        Admin admin = new Admin();
        admin.setAdminId("nonexistent123");

        when(adminRepository.findById(admin.getAdminId())).thenReturn(java.util.Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> adminService.updateAdmin(admin, admin.getAdminId()));
        verify(adminRepository, times(1)).findById("nonexistent123");
    }

    // Test for getInactiveDealers
    @Test
    public void testGetInactiveDealers() {
        // Arrange
        Dealer activeDealer = new Dealer();
        activeDealer.setDealerId("active123");
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setDealerId(activeDealer.getDealerId());
        transactions.add(transaction);

        Dealer inactiveDealer = new Dealer();
        inactiveDealer.setDealerId("inactive123");

        List<Dealer> allDealers = new ArrayList<>();
        allDealers.add(activeDealer);
        allDealers.add(inactiveDealer);

        when(dealerClient.getAllDealers()).thenReturn(allDealers);
        when(transactionClient.getDealerHistory(activeDealer.getDealerId())).thenReturn(transactions);

        // Act
        List<Dealer> result = adminService.getInactiveDealers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size()); // Only one dealer with no transactions
        verify(dealerClient, times(1)).getAllDealers();
        verify(transactionClient, times(1)).getDealerHistory("active123");
        verify(transactionClient, times(1)).getDealerHistory("inactive123");
    }

    // Test for updateFarmer
    @Test
    public void testUpdateFarmer() {
        // Arrange
        FarmerUpdateDTO farmerUpdateDTO = new FarmerUpdateDTO();
        farmerUpdateDTO.setName("John Doe");
        farmerUpdateDTO.setGender("Male");
        farmerUpdateDTO.setLocation("Farm");
        farmerUpdateDTO.setAge(30);

        Farmer farmer = new Farmer();
        farmer.setFarmerId("farmer123");
        farmer.setName("Old Farmer Name");
        farmer.setEmail("johndoe@example.com");
        farmer.setPassword("password");
        farmer.setGender("Male");
        farmer.setLocation("Old Location");
        farmer.setAge(25);

        when(farmerClient.findFarmerByEmail(farmer.getEmail())).thenReturn(farmer);
        when(farmerClient.updateFarmer(farmerUpdateDTO, farmer.getEmail())).thenReturn(farmerUpdateDTO);

        // Act
        FarmerUpdateDTO result = adminService.updateFarmer(farmerUpdateDTO, farmer.getEmail());

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("Male", result.getGender());
        assertEquals("Farm", result.getLocation());
        assertEquals(30, result.getAge());
        verify(farmerClient, times(1)).findFarmerByEmail("johndoe@example.com");
        verify(farmerClient, times(1)).updateFarmer(farmerUpdateDTO, "johndoe@example.com");
    }
    

    // Test for updateDealer
    @Test
    public void testUpdateDealer() {
        // Arrange
        DealerUpdateDTO dealerUpdateDTO = new DealerUpdateDTO();
        dealerUpdateDTO.setName("Jane Doe");
        dealerUpdateDTO.setGender("Female");
        dealerUpdateDTO.setAge(35);
        String email = "janedoe@example.com";

        when(dealerClient.updateDealer(dealerUpdateDTO, email)).thenReturn(dealerUpdateDTO);

        // Act
        DealerUpdateDTO result = adminService.updateDealer(dealerUpdateDTO, email);

        // Assert
        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        assertEquals("Female", result.getGender());
        assertEquals(35, result.getAge());
        verify(dealerClient, times(1)).updateDealer(dealerUpdateDTO, email);
    }

    // Test for getFarmersRatings
    @Test
    public void testGetFarmersRatings() {
        // Arrange
        Farmer farmer1 = new Farmer();
        farmer1.setName("Farmer1");
        farmer1.setEmail("farmer1@example.com");
        farmer1.setLocation("Location1");
        farmer1.setRating(4);

        Farmer farmer2 = new Farmer();
        farmer2.setName("Farmer2");
        farmer2.setEmail("farmer2@example.com");
        farmer2.setLocation("Location2");
        farmer2.setRating(3);

        List<Farmer> farmers = new ArrayList<>();
        farmers.add(farmer1);
        farmers.add(farmer2);

        when(farmerClient.getAllFarmers()).thenReturn(farmers);

        // Act
        List<FarmerRatingDTO> result = adminService.getFarmersRatings();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        FarmerRatingDTO rating1 = result.get(0);
        assertEquals("Farmer1", rating1.getName());
        assertEquals("farmer1@example.com", rating1.getEmail());
        assertEquals("Location1", rating1.getLocation());
        assertEquals(4, rating1.getRating());

        FarmerRatingDTO rating2 = result.get(1);
        assertEquals("Farmer2", rating2.getName());
        assertEquals("farmer2@example.com", rating2.getEmail());
        assertEquals("Location2", rating2.getLocation());
        assertEquals(3, rating2.getRating());

        verify(farmerClient, times(1)).getAllFarmers();
    }
    @Test
    public void testDeleteFarmerById() {
        // Arrange
        String farmerId = "farmer123";

        Farmer farmer = new Farmer();
        farmer.setFarmerId(farmerId);

        when(farmerClient.findFarmerById(farmerId)).thenReturn(farmer);
        when(farmerClient.deleteFarmerById(farmerId)).thenReturn(farmer);

        // Act
        Farmer result = adminService.deleteFarmerById(farmerId);

        // Assert
        assertNotNull(result);
        assertEquals(farmerId, result.getFarmerId());
        verify(farmerClient, times(1)).findFarmerById(farmerId);
        verify(farmerClient, times(1)).deleteFarmerById(farmerId);
    }

    @Test
    public void testDeleteFarmerByIdWithNonExistentFarmer() {
        // Arrange
        String farmerId = "farmer123";

        when(farmerClient.findFarmerById(farmerId)).thenReturn(null);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> adminService.deleteFarmerById(farmerId));
        verify(farmerClient, times(1)).findFarmerById(farmerId);
        verify(farmerClient, never()).deleteFarmerById(eq(farmerId));
    }

    // Test for deleteDealerById
    @Test
    public void testDeleteDealerById() {
        // Arrange
        String dealerId = "dealer123";

        Dealer dealer = new Dealer();
        dealer.setDealerId(dealerId);

        when(dealerClient.findFarmerById(dealerId)).thenReturn(dealer);
        when(dealerClient.deleteDealerById(dealerId)).thenReturn(dealer);

        // Act
        Dealer result = adminService.deleteDealerById(dealerId);

        // Assert
        assertNotNull(result);
        assertEquals(dealerId, result.getDealerId());
        verify(dealerClient, times(1)).findFarmerById(dealerId);
        verify(dealerClient, times(1)).deleteDealerById(dealerId);
    }

    @Test
    public void testDeleteDealerByIdWithNonExistentDealer() {
        // Arrange
        String dealerId = "dealer123";

        when(dealerClient.findFarmerById(dealerId)).thenReturn(null);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> adminService.deleteDealerById(dealerId));
        verify(dealerClient, times(1)).findFarmerById(dealerId);
        verify(dealerClient, never()).deleteDealerById(eq(dealerId));
    }
    @Test
    public void testFindAdminByEmail() {
    	String email = "test@email.com";
    	Admin admin = new Admin();
    	admin.setEmail(email);
    	when(adminRepository.findByEmail(email)).thenReturn(Optional.of(admin));
    	verify(adminRepository,times(0)).findByEmail(email);
    }
    @Test
    public void testFindAdminByEmailNotExist() {
    	
    	when(adminRepository.findByEmail("demo")).thenReturn(null);
    	assertThrows(ResourceNotFoundException.class, () -> adminRepository.findByEmail("demo"));
    }

    // Test for getAllTransaction
    @Test
    public void testGetAllTransaction() {
        // Arrange
        Transaction transaction1 = new Transaction();
        transaction1.setTransactionId("transaction1");

        Transaction transaction2 = new Transaction();
        transaction2.setTransactionId("transaction2");

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionClient.getAllTransactions()).thenReturn(transactions);

        // Act
        List<Transaction> result = adminService.getAllTransaction();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("transaction1", result.get(0).getTransactionId());
        assertEquals("transaction2", result.get(1).getTransactionId());
        verify(transactionClient, times(1)).getAllTransactions();
    }


}
