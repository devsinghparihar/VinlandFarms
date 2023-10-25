package com.dealer.exceptionHandler;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dealer.exceptions.DealerException;
import com.dealer.exceptions.EmailAlreadyExists;
import com.dealer.exceptions.InvalidTransaction;
import com.dealer.exceptions.ResourceNotFoundException;



@RestControllerAdvice
public class DealerExceptionHandler {

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
}
