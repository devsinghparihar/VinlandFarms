package com.transactions.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	
	@MongoId
	private String transactionId;
	private String farmerId;
	private String dealerId;
	private String dealerEmail;
	private String farmerEmail;
	private String cropType;
	private int quantity;
	private int pricePerKg;
	private double totalPrice;
	private LocalDateTime bookingTime;
	
	

}
