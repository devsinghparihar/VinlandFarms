package com.farmer.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CropDetailDTO {
	
	@NotBlank
	private String farmerId;
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String accountNumber;
	@NotBlank
	private String cropType;
	@Positive
	private int cropPrice;
	@Positive
	private int cropQuantity;
	private String location;
}
