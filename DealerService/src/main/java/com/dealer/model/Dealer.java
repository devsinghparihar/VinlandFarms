package com.dealer.model;

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
@Document(collection = "Dealers")
public class Dealer {
	@MongoId
	private String dealerId;
	private String name;
	private String email;
	private String password;
	private String gender;
	private int age;
	private String role = "ROLE_DEALER";
	private String accountNumber;
	private Long accountBalance;
	private List<String> requirements = new ArrayList<>();
	
	public void addReqirements(String reqirements) {
		this.requirements.add(reqirements);}

}
