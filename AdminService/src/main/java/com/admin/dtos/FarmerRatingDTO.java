package com.admin.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmerRatingDTO {
	
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	@Positive
	private int rating;
	@NotBlank
	private String location;


}
