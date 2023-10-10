package com.dealer.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidTransaction extends RuntimeException {

	public InvalidTransaction(String msg) {
		super(msg);
	}
	
}
