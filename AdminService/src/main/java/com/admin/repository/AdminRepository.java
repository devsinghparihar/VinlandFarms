 	package com.admin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.admin.model.Admin;
import java.util.List;
import java.util.Optional;


public interface AdminRepository extends MongoRepository<Admin, String> {

	Optional<Admin> findByEmail(String email);
}
