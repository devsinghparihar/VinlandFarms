package com.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealerUpdateDTO {
	
	private String name;
	private String password;
	private String gender;
	private int age;
	private String accountNumber;
	private long accountBalance;

}
