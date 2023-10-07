package com.farmer.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDetailDTO {

	private String name;
	private String password;
	private String gender;
	private String location;
	private int age;
	private String accountNumber;
	
}
