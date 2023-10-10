package com.dealer.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CropDetailDTO {
	
	private String farmerId;
	private String name;
	private String email;
	private String accountNumber;
	private String cropType;
	private int cropPrice;
	private int cropQuantity;
	private String location;
}
