package com.email.service;

import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.email.model.EmailModel;

public interface EmailService {
	
	void sendMail(EmailModel email) throws Exception;

}
