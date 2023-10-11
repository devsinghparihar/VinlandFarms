package com.emsjwt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FarmerUpdateDTO {

	private String name;
	private String password;
	private String gender;
	private String location;
	private int age;
	private String accountNumber;
	
}