package com.emsjwt.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealerUpdateDTO {
	
	private String name;
//	private String password;
	private String gender;
	private int age;
//	private String accountNumber;
//	private long accountBalance;

}