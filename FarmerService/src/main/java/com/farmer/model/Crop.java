package com.farmer.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Crop {
	
	@NotBlank
	private String cropType;
	@Positive
	private int cropPrice;
	@Positive
	private int cropQuantity;
	@NotBlank
	private String location;
	

}
