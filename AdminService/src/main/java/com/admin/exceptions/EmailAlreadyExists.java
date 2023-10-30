package com.admin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class EmailAlreadyExists extends RuntimeException {
	public EmailAlreadyExists(String msg) {
		super(msg);
	}
}
