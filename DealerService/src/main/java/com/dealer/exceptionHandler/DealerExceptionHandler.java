package com.dealer.exceptionHandler;


import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dealer.exceptions.DealerException;
import com.dealer.exceptions.EmailAlreadyExists;
import com.dealer.exceptions.InvalidTransaction;
import com.dealer.exceptions.ResourceNotFoundException;



@RestControllerAdvice
public class DealerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ DealerException.class, InvalidTransaction.class, EmailAlreadyExists.class,
            ResourceNotFoundException.class })
    public ResponseEntity<ExceptionResponse> handleCustomExceptions(Exception ex) {
        String message = ex.getMessage();
        String httpCodeMessage = "Internal Server Error"; // Default message

        if (ex instanceof DealerException) {
            httpCodeMessage = "Bad Request";
        } else if (ex instanceof InvalidTransaction) {
            httpCodeMessage = "Bad Request";
        } else if (ex instanceof EmailAlreadyExists) {
            httpCodeMessage = "Conflict";
        } 
        else if (ex instanceof ResourceNotFoundException) {
            httpCodeMessage = "Not Found";
        }
    
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(), message, httpCodeMessage);
        return new ResponseEntity<>(exceptionResponse, getHttpStatusForCode(httpCodeMessage));
    }

    private HttpStatus getHttpStatusForCode(String httpCodeMessage) {
        switch (httpCodeMessage) {
            case "Bad Request":
                return HttpStatus.BAD_REQUEST;
            case "Not Found":
                return HttpStatus.NOT_FOUND;
            case "Conflict":
                return HttpStatus.CONFLICT;
            
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
    @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		StringBuilder details = new StringBuilder();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			details.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(". ");
		}
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(), "Validation fails",
				 "Bad Request");
	
//        log.error(ex.getMessage());
 
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
