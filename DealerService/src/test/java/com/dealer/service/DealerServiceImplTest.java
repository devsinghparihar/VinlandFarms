package com.dealer.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.dealer.clients.EmailClient;
import com.dealer.clients.FarmerClient;
import com.dealer.clients.TransactionClient;
import com.dealer.dtos.CropDetailDTO;
import com.dealer.dtos.TransactionDTO;
import com.dealer.dtos.UpdateDetailDTO;
import com.dealer.exceptions.EmailAlreadyExists;
import com.dealer.exceptions.InvalidTransaction;
import com.dealer.exceptions.ResourceNotFoundException;
import com.dealer.model.Crop;
import com.dealer.model.Dealer;
import com.dealer.model.Farmer;
import com.dealer.model.Transaction;
import com.dealer.repository.DealerRepository;

class DealerServiceImplTest {

    @InjectMocks
    private DealerServiceImpl dealerService;

    @Mock
    private DealerRepository dealerRepository;

    @Mock
    private EmailClient emailClient;

    @Mock
    private FarmerClient farmerClient;

    @Mock
    private TransactionClient transactionClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDealer_Success() {
        Dealer dealer = new Dealer();
        dealer.setEmail("test@example.com");
        dealer.setPassword("password");
        dealer.setName("Test Dealer");
        dealer.setGender("Male");
        dealer.setAge(30);

        Mockito.when(dealerRepository.findByEmail(dealer.getEmail())).thenReturn(null);
        Mockito.when(dealerRepository.save(dealer)).thenReturn(dealer);

 

        Dealer addedDealer = dealerService.addDealer(dealer);

        assertNotNull(addedDealer);
        assertEquals(dealer.getEmail(), addedDealer.getEmail());
    }

    @Test
    void testAddDealer_EmailAlreadyExists() {
        Dealer dealer = new Dealer();
        dealer.setEmail("test@example.com");

        Mockito.when(dealerRepository.findByEmail(dealer.getEmail())).thenReturn(dealer);

        assertThrows(EmailAlreadyExists.class, () -> dealerService.addDealer(dealer));
    }

    @Test
    void testGetAll_Success() {
        List<Dealer> dealerList = new ArrayList<>();
        dealerList.add(new Dealer());
        dealerList.add(new Dealer());

        Mockito.when(dealerRepository.findAll()).thenReturn(dealerList);

        List<Dealer> dealers = dealerService.getAll();

        assertNotNull(dealers);
        assertEquals(2, dealers.size());
    }

    @Test
    void testGetAll_NoDealers() {
        Mockito.when(dealerRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(ResourceNotFoundException.class, () -> dealerService.getAll());
    }
    @Test
    void testAddRequirement_DealerNotFound() {
        String requirement = "Requirement";
        String email = "test@example.com";

        Mockito.when(dealerRepository.findByEmail(email)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> dealerService.addRequirement(requirement, email));
    }

    @Test
    void testUpdateDealer_Success() {
        UpdateDetailDTO updateDetailDTO = new UpdateDetailDTO("New Name", "Male", 25);
        String email = "test@example.com";
        Dealer dealer = new Dealer();
        dealer.setEmail(email);

        Mockito.when(dealerRepository.findByEmail(email)).thenReturn(dealer);
        Mockito.when(dealerRepository.save(dealer)).thenReturn(dealer);

        UpdateDetailDTO updatedDTO = dealerService.updateDealer(updateDetailDTO, email);

        assertNotNull(updatedDTO);
        assertEquals(updateDetailDTO.getName(), updatedDTO.getName());
    }

    @Test
    void testUpdateDealer_DealerNotFound() {
        UpdateDetailDTO updateDetailDTO = new UpdateDetailDTO("New Name", "Male", 25);
        String email = "test@example.com";

        Mockito.when(dealerRepository.findByEmail(email)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> dealerService.updateDealer(updateDetailDTO, email));
    }

    @Test
    void testFindDealerById_Success() {
        String dealerId = "12345";
        Dealer dealer = new Dealer();
        dealer.setDealerId(dealerId);

        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(Optional.of(dealer));

        Dealer foundDealer = dealerService.findDealerById(dealerId);

        assertNotNull(foundDealer);
        assertEquals(dealerId, foundDealer.getDealerId());
    }

    @Test
    void testFindDealerById_DealerNotFound() {
        String dealerId = "12345";

        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> dealerService.findDealerById(dealerId));
    }

    @Test
    void testFindDealerByEmail_Success() {
        String email = "test@example.com";
        Dealer dealer = new Dealer();
        dealer.setEmail(email);

        Mockito.when(dealerRepository.findByEmail(email)).thenReturn(dealer);

        Dealer foundDealer = dealerService.findDealerByEmail(email);

        assertNotNull(foundDealer);
        assertEquals(email, foundDealer.getEmail());
    }

    @Test
    void testFindDealerByEmail_DealerNotFound() {
        String email = "test@example.com";

        Mockito.when(dealerRepository.findByEmail(email)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> dealerService.findDealerByEmail(email));
    }
    @Test
    void testBuyCrop_Success() {
        String farmerId = "farmer123";
        String dealerId = "dealer456";
        String cropType = "Wheat";
        int quantity = 10;

        // Prepare mock data
        Farmer farmer = new Farmer();
        farmer.setFarmerId(farmerId);
        farmer.setAccountBalance((long) 0);

        Dealer dealer = new Dealer();
        dealer.setDealerId(dealerId);
        dealer.setAccountBalance(1000L);

        Crop crop = new Crop();
        crop.setCropType(cropType);
        crop.setCropPrice(10);
        crop.setCropQuantity(20);

        List<Crop> crops = new ArrayList<>();
        crops.add(crop);
        farmer.setCrops(crops);

        TransactionDTO transactionDTO = new TransactionDTO(farmerId, dealerId, dealer.getEmail(), farmer.getEmail(),
                cropType, quantity, crop.getCropPrice(), 100.0);

        // Mock the dependencies
        Mockito.when(farmerClient.findFarmerById(farmerId)).thenReturn(farmer);
        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(java.util.Optional.of(dealer));
        Mockito.when(transactionClient.createTransaction(transactionDTO)).thenReturn(new Transaction());

        Transaction transaction = dealerService.buyCrop(farmerId, dealerId, cropType, quantity);

        assertNotNull(transaction);
       
    }

    @Test
    void testBuyCrop_FarmerNotFound() {
        String farmerId = "farmer123";
        String dealerId = "dealer456";
        String cropType = "Wheat";
        int quantity = 10;

        // Mock the dependencies
        Mockito.when(farmerClient.findFarmerById(farmerId)).thenReturn(null);

        assertThrows(InvalidTransaction.class, () -> dealerService.buyCrop(farmerId, dealerId, cropType, quantity));
    }

    @Test
    void testBuyCrop_DealerNotFound() {
        String farmerId = "farmer123";
        String dealerId = "dealer456";
        String cropType = "Wheat";
        int quantity = 10;

        // Prepare mock data
        Farmer farmer = new Farmer();
        farmer.setFarmerId(farmerId);

        // Mock the dependencies
        Mockito.when(farmerClient.findFarmerById(farmerId)).thenReturn(farmer);
        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(java.util.Optional.empty());

        assertThrows(NoSuchElementException.class, () -> dealerService.buyCrop(farmerId, dealerId, cropType, quantity));
    }
    @Test
    void testFindTransactionsByDealerId() {
        String dealerId = "dealer123";
        Dealer dealer = new Dealer();
        dealer.setDealerId(dealerId);

        Transaction transaction1 = new Transaction();
        transaction1.setTransactionId("1");
        transaction1.setDealerId(dealerId);

        Transaction transaction2 = new Transaction();
        transaction2.setTransactionId("2");
        transaction2.setDealerId(dealerId);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(Optional.of(dealer));
        Mockito.when(transactionClient.getDealerHistory(dealerId)).thenReturn(transactions);

        List<Transaction> result = dealerService.findTransactionsByDealerId(dealerId);

        assertEquals(2, result.size());
        assertEquals("1", result.get(0).getTransactionId());
        assertEquals("2", result.get(1).getTransactionId());
    }

    @Test
    void testFindTransactionsByDealerId_DealerNotFound() {
        String dealerId = "dealer123";
        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> dealerService.findTransactionsByDealerId(dealerId));
    }

    @Test
    void testRunScan_Success() {
        String dealerId = "dealer123";
        Dealer dealer = new Dealer();
        dealer.setDealerId(dealerId);
        List<String> required = new ArrayList<>();
        required.add("Wheat");
        dealer.setRequirements(required);

        List<String> availableCrops = new ArrayList<>();
        availableCrops.add("Wheat");

        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(Optional.of(dealer));
        Mockito.when(farmerClient.getCropsByType("Wheat")).thenReturn(new ArrayList<>());

        boolean result = dealerService.runScan(dealerId);

        assertFalse(result);
    }

    @Test
    void testRunScan_DealerNotFound() {
        String dealerId = "dealer123";
        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> dealerService.runScan(dealerId));
    }

    @Test
    void testRunScan_NoMatchingCrops() {
        String dealerId = "dealer123";
        Dealer dealer = new Dealer();
        dealer.setDealerId(dealerId);
        List<String> required = new ArrayList<>();
        required.add("Wheat");
        dealer.setRequirements(required);

        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(Optional.of(dealer));
        Mockito.when(farmerClient.getCropsByType("Wheat")).thenReturn(new ArrayList<>());

        boolean result = dealerService.runScan(dealerId);

        assertFalse(result);
    }

    @Test
    void testFindRequirements() {
        String dealerId = "dealer123";
        Dealer dealer = new Dealer();
        dealer.setDealerId(dealerId);
        List<String> required = new ArrayList<>();
        required.add("Wheat");
        required.add("Rice");
        dealer.setRequirements(required);

        List<CropDetailDTO> wheatCrops = new ArrayList<>();
        CropDetailDTO wheatCrop1 = new CropDetailDTO();
        wheatCrop1.setCropType("Wheat");
        wheatCrops.add(wheatCrop1);

        List<CropDetailDTO> riceCrops = new ArrayList<>();
        CropDetailDTO riceCrop1 = new CropDetailDTO();
        riceCrop1.setCropType("Rice");
        riceCrops.add(riceCrop1);

        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(Optional.of(dealer));
        Mockito.when(farmerClient.getCropsByType("Wheat")).thenReturn(wheatCrops);
        Mockito.when(farmerClient.getCropsByType("Rice")).thenReturn(riceCrops);

        List<String> result = dealerService.findRequirements(dealerId);

        assertEquals(2, result.size());
        assertTrue(result.contains("Wheat"));
        assertTrue(result.contains("Rice"));
    }

    @Test
    void testFindRequirements_DealerNotFound() {
        String dealerId = "dealer123";
        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> dealerService.findRequirements(dealerId));
    }

    @Test
    void testDeleteDealerById() {
        String dealerId = "dealer123";
        Dealer dealer = new Dealer();
        dealer.setDealerId(dealerId);

        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(Optional.of(dealer));

        Dealer result = dealerService.deleteDealerById(dealerId);

        assertNotNull(result);
        assertEquals(dealerId, result.getDealerId());
    }

    @Test
    void testDeleteDealerById_DealerNotFound() {
        String dealerId = "dealer123";
        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> dealerService.deleteDealerById(dealerId));
    }

    @Test
    void testAddMoneyToWallet_Success() {
        String dealerId = "dealer123";
        Dealer dealer = new Dealer();
        dealer.setDealerId(dealerId);
        dealer.setAccountBalance(1000L);

        long money = 500L;

        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(Optional.of(dealer));

        String result = dealerService.addMoneyToWallet(dealerId, money);

        assertEquals("Balance updated, current balance: 1500", result);
        assertEquals(1500L, dealer.getAccountBalance());
    }

    @Test
    void testAddMoneyToWallet_DealerNotFound() {
        String dealerId = "dealer123";
        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> dealerService.addMoneyToWallet(dealerId, 500L));
    }

    @Test
    void testAddMoneyToWallet_NegativeAmount() {
        String dealerId = "dealer123";
        Dealer dealer = new Dealer();
        dealer.setDealerId(dealerId);
        dealer.setAccountBalance(1000L);

        long money = -500L;

        Mockito.when(dealerRepository.findById(dealerId)).thenReturn(Optional.of(dealer));

        assertThrows(InvalidTransaction.class, () -> dealerService.addMoneyToWallet(dealerId, money));
        assertEquals(1000L, dealer.getAccountBalance());
    }

    @Test
    void testUpdateFarmerRating_Success() {
        String farmerId = "farmer123";
        int rating = 4;

        Mockito.when(farmerClient.updateRating(farmerId, rating)).thenReturn("Rating updated successfully");

        String result = dealerService.updateFarmerRating(farmerId, rating);

        assertEquals("Rating updated successfully", result);
    }

    @Test
    void testChangePassword_Success() {
        String email = "dealer@example.com";
        String newPassword = "newPassword";

        Dealer dealer = new Dealer();
        dealer.setEmail(email);

        Mockito.when(dealerRepository.findByEmail(email)).thenReturn(dealer);

        String result = dealerService.changePassword(email, newPassword);

        assertEquals("Password changed Successfully", result);
        assertEquals(newPassword, dealer.getPassword());
    }

    @Test
    void testChangePassword_DealerNotFound() {
        String email = "dealer@example.com";
        Mockito.when(dealerRepository.findByEmail(email)).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> dealerService.changePassword(email, "newPassword"));
    }
 
    

    // Add test cases for other methods in the service.
}
