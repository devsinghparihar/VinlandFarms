package com.transactions.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.transactions.clients.EmailClient;
import com.transactions.dtos.TransactionDTO;
import com.transactions.model.EmailModel;
import com.transactions.model.Transaction;
import com.transactions.repository.TransactionRepository;

@SpringBootTest
public class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;
	
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private EmailClient emailClient;
    
    @BeforeEach
    public void setUp() {
    	 MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInitiateTransaction() {
        // Arrange
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setFarmerId("sampleFarmerId");
        transactionDTO.setDealerId("sampleDealerId");
        transactionDTO.setDealerEmail("dealer@example.com");
        transactionDTO.setFarmerEmail("farmer@example.com");
        transactionDTO.setCropType("Wheat");
        transactionDTO.setQuantity(100);
        transactionDTO.setPricePerKg(5);
        transactionDTO.setTotalPrice(500);

        // Create a sample transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionId("sampleTransactionId");
        transaction.setFarmerId("sampleFarmerId");
        transaction.setDealerId("sampleDealerId");
        transaction.setDealerEmail("dealer@example.com");
        transaction.setFarmerEmail("farmer@example.com");
        transaction.setCropType("Wheat");
        transaction.setQuantity(100);
        transaction.setPricePerKg(5);
        transaction.setTotalPrice(500);
        transaction.setBookingTime(LocalDateTime.now());

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        // Act
        Transaction result = transactionService.initiateTransaction(transactionDTO);

        // Assert
        verify(emailClient, times(2)).sendMail(any(EmailModel.class));
        assertEquals(transaction, result);
    }


    @Test
    public void testAllTransactions() {
        // Arrange
        List<Transaction> transactions = new ArrayList<>();

        // Create sample transactions and set their properties
        Transaction transaction1 = new Transaction();
        transaction1.setFarmerId("farmerId1");
        transaction1.setDealerId("dealerId1");
        transaction1.setDealerEmail("dealer1@example.com");
        transaction1.setFarmerEmail("farmer1@example.com");
        transaction1.setCropType("CropType1");
        transaction1.setQuantity(10);
        transaction1.setPricePerKg(5);
        transaction1.setTotalPrice(50.0);
        transaction1.setBookingTime(LocalDateTime.now());

        Transaction transaction2 = new Transaction();
        transaction2.setFarmerId("farmerId2");
        transaction2.setDealerId("dealerId2");
        transaction2.setDealerEmail("dealer2@example.com");
        transaction2.setFarmerEmail("farmer2@example.com");
        transaction2.setCropType("CropType2");
        transaction2.setQuantity(20);
        transaction2.setPricePerKg(8);
        transaction2.setTotalPrice(160.0);
        transaction2.setBookingTime(LocalDateTime.now());

        // Add the sample transactions to the list
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionRepository.findAll()).thenReturn(transactions);

        // Act
        List<Transaction> result = transactionService.allTransactions();

        // Assert
        assertEquals(transactions, result);
    }


    @Test
    public void testFindTransactionsByFarmerId() {
        // Arrange
        String farmerId = "sampleFarmerId";
        List<Transaction> transactions = new ArrayList<>();

        // Create sample transactions and set their properties
        Transaction transaction1 = new Transaction();
        transaction1.setFarmerId(farmerId);
        transaction1.setDealerId("dealerId1");
        transaction1.setDealerEmail("dealer1@example.com");
        transaction1.setFarmerEmail("farmer1@example.com");
        transaction1.setCropType("Crop1");
        transaction1.setQuantity(10);
        transaction1.setPricePerKg(5);
        transaction1.setTotalPrice(50.0);

        Transaction transaction2 = new Transaction();
        transaction2.setFarmerId(farmerId);
        transaction2.setDealerId("dealerId2");
        transaction2.setDealerEmail("dealer2@example.com");
        transaction2.setFarmerEmail("farmer2@example.com");
        transaction2.setCropType("Crop2");
        transaction2.setQuantity(15);
        transaction2.setPricePerKg(7);
        transaction2.setTotalPrice(105.0);

        // Add the sample transactions to the list
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionRepository.findByFarmerId(farmerId)).thenReturn(transactions);

        // Act
        List<Transaction> result = transactionService.findTransactionsByFarmerId(farmerId);

        // Assert
        assertEquals(transactions, result);
    }

    @Test
    public void testFindTransactionsByDealerId() {
        // Arrange
        String dealerId = "sampleDealerId";
        List<Transaction> transactions = new ArrayList<>();

        // Create sample transactions and set their properties
        Transaction transaction1 = new Transaction();
        transaction1.setFarmerId("farmerId1");
        transaction1.setDealerId(dealerId);
        transaction1.setDealerEmail("dealer1@example.com");
        transaction1.setFarmerEmail("farmer1@example.com");
        transaction1.setCropType("Crop1");
        transaction1.setQuantity(10);
        transaction1.setPricePerKg(5);
        transaction1.setTotalPrice(50.0);

        Transaction transaction2 = new Transaction();
        transaction2.setFarmerId("farmerId2");
        transaction2.setDealerId(dealerId);
        transaction2.setDealerEmail("dealer2@example.com");
        transaction2.setFarmerEmail("farmer2@example.com");
        transaction2.setCropType("Crop2");
        transaction2.setQuantity(15);
        transaction2.setPricePerKg(7);
        transaction2.setTotalPrice(105.0);

        // Add the sample transactions to the list
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionRepository.findByDealerId(dealerId)).thenReturn(transactions);

        // Act
        List<Transaction> result = transactionService.findTransactionsByDealerId(dealerId);

        // Assert
        assertEquals(transactions, result);
    }

}
