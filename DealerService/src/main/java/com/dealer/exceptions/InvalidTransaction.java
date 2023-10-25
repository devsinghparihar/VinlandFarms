package com.dealer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTransaction extends RuntimeException {

	public InvalidTransaction(String msg) {
		super(msg);
	}
	
}
