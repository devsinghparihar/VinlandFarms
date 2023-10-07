package com.dealer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class DealerException extends Exception {

	public DealerException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
}
