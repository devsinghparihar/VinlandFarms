	package com.farmer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)	
public class FarmerNotFoundException extends RuntimeException {
	public FarmerNotFoundException(String msg) {
		super(msg);
	}
}
