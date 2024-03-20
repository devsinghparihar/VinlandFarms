package com.transactions.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transactions.clients.EmailClient;
import com.transactions.dtos.TransactionDTO;
import com.transactions.model.EmailModel;
import com.transactions.model.Transaction;
import com.transactions.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired	
	TransactionRepository tr;
	
	@Autowired
	EmailClient emailClient;

	@Override
	public Transaction initiateTransaction(TransactionDTO transaction) {
		Transaction generatedTransaction = new Transaction();
		generatedTransaction.setFarmerId(transaction.getFarmerId());

		generatedTransaction.setDealerId(transaction.getDealerId());

		generatedTransaction.setFarmerEmail(transaction.getFarmerEmail());

		generatedTransaction.setDealerEmail(transaction.getDealerEmail());

		generatedTransaction.setCropType(transaction.getCropType());

		generatedTransaction.setQuantity(transaction.getQuantity());

		generatedTransaction.setPricePerKg(transaction.getPricePerKg());

		generatedTransaction.setTotalPrice(transaction.getTotalPrice());

		generatedTransaction.setBookingTime(LocalDateTime.now());
		
		// TODO Auto-generated method stub
		EmailModel dealerEmail = new EmailModel();
		EmailModel farmerEmail = new EmailModel();
		dealerEmail.setTo(transaction.getDealerEmail());
		farmerEmail.setTo(transaction.getFarmerEmail());
		dealerEmail.setSubject("Transaction alert!!!");
		farmerEmail.setSubject("Transaction alert!!!");
		
		
		String dealerMessage = "Dear Dealer" + ",\n\n" +
                "We are pleased to inform you that a successful transaction has been completed on Vinland Farms. This email serves as confirmation of the transaction details:\n\n" +
                "Transaction time: " + LocalDateTime.now() + "\n" +
                "Transaction Amount: $" + transaction.getTotalPrice() + "\n" +
                "Crop name: " + transaction.getCropType() + "\n\n" +
                "Details:\n" +
                "- Quantity: " + transaction.getQuantity() + "\n" +
                "- Price per Unit: $" + transaction.getPricePerKg() + "\n" +
                "- Total Amount: $" + transaction.getTotalPrice() + "\n\n" +
                "We are committed to providing a secure and efficient platform for your transactions. If you have any questions or require further assistance regarding this transaction or any other matter, please do not hesitate to contact our support team. We are here to help.\n\n" +
                "Thank you for choosing Vinland Farms for your agricultural needs. We look forward to serving you again in the future.\n\n" +
                "Best regards,\n" +
                "The Vinland Farms Team";
		String farmerMessage = "Dear Farmer" + ",\n\n" +
                "We are pleased to inform you that a successful transaction has been completed on Vinland Farms. This email serves as confirmation of the transaction details:\n\n" +
                "Transaction time: " + LocalDateTime.now() + "\n" +
                "Transaction Amount: $" + transaction.getTotalPrice() + "\n" +
                "Crop name: " + transaction.getCropType() + "\n\n" +
                "Details:\n" +
                "- Quantity: " + transaction.getQuantity() + "\n" +
                "- Price per Unit: $" + transaction.getPricePerKg() + "\n" +
                "- Total Amount: $" + transaction.getTotalPrice() + "\n\n" +
                "We are committed to providing a secure and efficient platform for your transactions. If you have any questions or require further assistance regarding this transaction or any other matter, please do not hesitate to contact our support team. We are here to help.\n\n" +
                "Thank you for choosing Vinland Farms for your agricultural needs. We look forward to serving you again in the future.\n\n" +
                "Best regards,\n" +
                "The Vinland Farms Team";

		
		dealerEmail.setText(dealerMessage);
		farmerEmail.setText(farmerMessage);	
		emailClient.sendMail(dealerEmail);
		emailClient.sendMail(farmerEmail);
		return tr.save(generatedTransaction);
	}

	@Override
	public List<Transaction> allTransactions() {
		// TODO Auto-generated method stub
		return tr.findAll();
	}

	@Override
	public List<Transaction> findTransactionsByFarmerId(String id) {
		// TODO Auto-generated method stub
		return tr.findByFarmerId(id);
	}

	@Override
	public List<Transaction> findTransactionsByDealerId(String id) {
		// TODO Auto-generated method stub
		return tr.findByDealerId(id);
	}

}
