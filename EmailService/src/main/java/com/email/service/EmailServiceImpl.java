package com.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.email.model.EmailModel;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	JavaMailSender sender;
	



	@Override
	public void sendMail(EmailModel email) {
		// TODO Auto-generated method stub
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());

        sender.send(message);
		
	}
	

}
