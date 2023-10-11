package com.emsjwt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FarmerRatingDTO {
	
	private String name;
	private String email;
	private int rating;
	private String location;


}
