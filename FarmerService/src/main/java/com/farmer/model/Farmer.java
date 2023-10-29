package com.farmer.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;
	@NotBlank
	private String gender;
	@NotBlank
	private String location;
	@Positive
	private int age;
	private String role = "ROLE_FARMER";
	
	private int rating;
	@NotBlank
	private String accountNumber;
	private Long accountBalance;
	private List<Crop> crops = new ArrayList<>();
	
	public void addCropToInventory(Crop crop) {
		this.crops.add(crop);
		
	}

}
