package com.emsjwt.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Admin {
	@MongoId
	private String adminId;
	private String name;
	private String email;
	private String password;
	private String gender;
	private String role = "ROLE_ADMIN";
	
	
}
