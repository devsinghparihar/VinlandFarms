package com.dealer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dealer.model.Dealer;
import java.util.List;


@Repository
public interface DealerRepository extends MongoRepository<Dealer, String>{
		Dealer findByEmail(String email);
}
