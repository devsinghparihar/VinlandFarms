package com.dealer.model;

import java.time.LocalDateTime;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	
	
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
