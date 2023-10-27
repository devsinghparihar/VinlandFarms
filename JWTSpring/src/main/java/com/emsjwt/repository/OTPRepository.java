package com.emsjwt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.emsjwt.model.Login;
import com.emsjwt.model.SendOTP;

@Repository
public interface OTPRepository extends MongoRepository<SendOTP, String>{

}
