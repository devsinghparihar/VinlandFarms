package com.farmer.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="Farmers")

public class Farmer {
	
	@MongoId
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
	
	public void addCropToInventory(Crop crop) {
		this.crops.add(crop);
		
	}

}
