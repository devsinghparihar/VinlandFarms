package com.dealer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Crop {
	
	private String cropType;
	private int cropPrice;
	private int cropQuantity;
	private String location;
	

}
