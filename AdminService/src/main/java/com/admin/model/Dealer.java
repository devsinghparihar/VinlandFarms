package com.admin.model;

import java.util.ArrayList;
import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dealer {
	
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


}
