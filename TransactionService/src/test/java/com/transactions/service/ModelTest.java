package com.transactions.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.transactions.dtos.TransactionDTO;
import com.transactions.exceptions.TransactionException;
import com.transactions.model.EmailModel;
import com.transactions.model.Transaction;

public class ModelTest {

    @Test
    public void testTransactionModel() {
        // Create a Transaction object
        Transaction transaction = new Transaction();
        transaction.setTransactionId("trans123");
        transaction.setFarmerId("farmer123");
        transaction.setDealerId("dealer123");
        transaction.setDealerEmail("dealer@example.com");
        transaction.setFarmerEmail("farmer@example.com");
        transaction.setCropType("Wheat");
        transaction.setQuantity(100);
        transaction.setPricePerKg(5);
        transaction.setTotalPrice(500.0);

        // Test getters
        assertEquals("trans123", transaction.getTransactionId());
        assertEquals("farmer123", transaction.getFarmerId());
        assertEquals("dealer123", transaction.getDealerId());
        assertEquals("dealer@example.com", transaction.getDealerEmail());
        assertEquals("farmer@example.com", transaction.getFarmerEmail());
        assertEquals("Wheat", transaction.getCropType());
        assertEquals(100, transaction.getQuantity());
        assertEquals(5, transaction.getPricePerKg());
        assertEquals(500.0, transaction.getTotalPrice());
    }

    @Test
    public void testEmailModel() {
        // Create an EmailModel object
        EmailModel emailModel = new EmailModel("recipient@example.com", "Test Subject", "Test Content");

        // Test getters
        assertEquals("recipient@example.com", emailModel.getTo());
        assertEquals("Test Subject", emailModel.getSubject());
        assertEquals("Test Content", emailModel.getText());
    }

    @Test
    public void testTransactionDTO() {
        // Create a TransactionDTO object
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setFarmerId("farmer123");
        transactionDTO.setDealerId("dealer123");
        transactionDTO.setDealerEmail("dealer@example.com");
        transactionDTO.setFarmerEmail("farmer@example.com");
        transactionDTO.setCropType("Wheat");
        transactionDTO.setQuantity(100);
        transactionDTO.setPricePerKg(5);
        transactionDTO.setTotalPrice(500.0);

        // Test getters
        assertEquals("farmer123", transactionDTO.getFarmerId());
        assertEquals("dealer123", transactionDTO.getDealerId());
        assertEquals("dealer@example.com", transactionDTO.getDealerEmail());
        assertEquals("farmer@example.com", transactionDTO.getFarmerEmail());
        assertEquals("Wheat", transactionDTO.getCropType());
        assertEquals(100, transactionDTO.getQuantity());
        assertEquals(5, transactionDTO.getPricePerKg());
        assertEquals(500.0, transactionDTO.getTotalPrice());
    }
    @Test
    public void testTransactionException() {
        // Define the error message
        String errorMessage = "Transaction not found";

        // Test that TransactionException is thrown with the specified error message
        TransactionException exception = assertThrows(TransactionException.class, () -> {
            throw new TransactionException(errorMessage);
        });

        // Verify that the exception message matches the expected error message
        String actualErrorMessage = exception.getMessage();
        assert(actualErrorMessage.equals(errorMessage));
    }
}
