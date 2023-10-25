package com.farmer.exceptionHandler;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.farmer.exceptions.EmailAlreadyExists;
import com.farmer.exceptions.FarmerException;
import com.farmer.exceptions.FarmerNotFoundException;
import com.farmer.exceptions.NoCropsFoundException;
import com.farmer.exceptions.NoTransactionFoundException;
import com.farmer.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class FarmerExceptionHandler {

    @ExceptionHandler({ FarmerException.class, FarmerNotFoundException.class, EmailAlreadyExists.class,
            NoCropsFoundException.class, NoTransactionFoundException.class, ResourceNotFoundException.class })
    public ResponseEntity<ExceptionResponse> handleCustomExceptions(Exception ex) {
        String message = ex.getMessage();
        String httpCodeMessage = "Internal Server Error"; // Default message

        if (ex instanceof FarmerException) {
            httpCodeMessage = "Bad Request";
        } else if (ex instanceof FarmerNotFoundException) {
            httpCodeMessage = "Not Found";
        } else if (ex instanceof EmailAlreadyExists) {
            httpCodeMessage = "Conflict";
        } else if (ex instanceof NoCropsFoundException) {
            httpCodeMessage = "Not Found";
        } else if (ex instanceof NoTransactionFoundException) {
            httpCodeMessage = "Not Found";
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
