package com.farmer.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
	
	private String farmerId;
	private String dealerId;
	private String dealerEmail;
	private String farmerEmail;
	private String cropType;
	private int quantity;
	private int pricePerKg;
	private double totalPrice;
	

}
