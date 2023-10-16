package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.EmailModel;
import com.email.service.EmailServiceImpl;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "http://localhost:3000")
public class EmailController {

	@Autowired
	private EmailServiceImpl service;
	
	@PostMapping("/sendMail")
	public ResponseEntity<String> sendMail(@RequestBody EmailModel mail ){
		try {
			service.sendMail(mail);
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);
			// TODO: handle exception
		}
		
	}
	
	
}
