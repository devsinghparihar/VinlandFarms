package com.transactions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class TransactionException extends Exception {
	
	public TransactionException(String msg) {
		super(msg);
	}

}
