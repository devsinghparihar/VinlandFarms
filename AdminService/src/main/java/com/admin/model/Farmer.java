package com.admin.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Farmer {
	
	private String farmerId;
	private String name;
	private String email;
	private String password;
	private String gender;
	private String location;
	private int age;
	private String role = "ROLE_FARMER";
	private int rating;
	private String accountNumber;
	private Long accountBalance;
	private List<Crop> crops = new ArrayList<>();
	
	

}
