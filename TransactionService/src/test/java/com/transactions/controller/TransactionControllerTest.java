package com.transactions.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.transactions.dtos.TransactionDTO;
import com.transactions.model.EmailModel;
import com.transactions.model.Transaction;
import com.transactions.service.TransactionServiceImpl;

public class TransactionControllerTest {

  
    private TransactionController transactionController;

   
    private TransactionServiceImpl transactionService;

    @BeforeEach
    public void setUp() {
    	
         transactionService = Mockito.mock(TransactionServiceImpl.class);
         transactionController = new TransactionController(transactionService);
    }

    @Test
    public void testCreateTransaction() {
        TransactionDTO transactionDTO = getSampleTransactionDTO();
        Transaction generatedTransaction = getSampleTransaction();

        Mockito.when(transactionService.initiateTransaction(transactionDTO)).thenReturn(generatedTransaction);

        ResponseEntity<Transaction> response = transactionController.createTransaction(transactionDTO);

        Mockito.verify(transactionService, Mockito.times(1)).initiateTransaction(transactionDTO);
       

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == generatedTransaction;
    }

    @Test
    public void testGetFarmerHistory() {
        String farmerId = "1";
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(getSampleTransaction());

        Mockito.when(transactionService.findTransactionsByFarmerId(farmerId)).thenReturn(transactions);

        ResponseEntity<List<Transaction>> response = transactionController.getFarmerHistory(farmerId);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().equals(transactions);
    }

    @Test
    public void testGetDealerHistory() {
        String dealerId = "2";
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(getSampleTransaction());

        Mockito.when(transactionService.findTransactionsByDealerId(dealerId)).thenReturn(transactions);

        ResponseEntity<List<Transaction>> response = transactionController.getDealerHistory(dealerId);

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().equals(transactions);
    }

    @Test
    public void testGetAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(getSampleTransaction());

        Mockito.when(transactionService.allTransactions()).thenReturn(transactions);

        ResponseEntity<List<Transaction>> response = transactionController.getAllTransactions();

        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().equals(transactions);
    }

    private TransactionDTO getSampleTransactionDTO() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setFarmerId("1");
        transactionDTO.setDealerId("2");
        transactionDTO.setDealerEmail("dealer@example.com");
        transactionDTO.setFarmerEmail("farmer@example.com");
        transactionDTO.setCropType("Wheat");
        transactionDTO.setQuantity(100);
        transactionDTO.setPricePerKg(5);
        transactionDTO.setTotalPrice(500.0);
        return transactionDTO;
    }

    private Transaction getSampleTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionId("1");
        transaction.setFarmerId("1");
        transaction.setDealerId("2");
        transaction.setDealerEmail("dealer@example.com");
        transaction.setFarmerEmail("farmer@example.com");
        transaction.setCropType("Wheat");
        transaction.setQuantity(100);
        transaction.setPricePerKg(5);
        transaction.setTotalPrice(500.0);
        return transaction;
    }

    private EmailModel getSampleEmailModel() {
        EmailModel emailModel = new EmailModel();
        emailModel.setTo("recipient@example.com");
        emailModel.setSubject("Test Subject");
        emailModel.setText("Test Email Content");
        return emailModel;
    }
}
