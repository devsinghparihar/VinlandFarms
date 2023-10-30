package com.dealer.model;

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
@Document(collection = "Dealers")
public class Dealer {
	@MongoId
	private String dealerId;
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;
	@NotBlank
	private String gender;
	@Positive
	private int age;
	private String role = "ROLE_DEALER";
	@NotBlank
	private String accountNumber;
	private Long accountBalance;
	private List<String> requirements = new ArrayList<>();
	
	public void addReqirements(String reqirements) {
		this.requirements.add(reqirements);}

}
