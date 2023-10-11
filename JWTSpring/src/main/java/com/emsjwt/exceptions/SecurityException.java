package com.emsjwt.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SecurityException extends Exception {
	String msgString;
}
