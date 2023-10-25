package com.dealer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@NoArgsConstructor
public class DealerException extends Exception {

	public DealerException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
}
