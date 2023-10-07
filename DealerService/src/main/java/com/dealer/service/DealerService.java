package com.dealer.service;

import java.util.List;

import com.dealer.dtos.UpdateDetailDTO;
import com.dealer.model.Dealer;



public interface DealerService {
	
	Dealer addDealer(Dealer d);
	List<Dealer> getAll();
	Dealer addRequirement(String requirement, String email);
	UpdateDetailDTO updateDealer(UpdateDetailDTO f,String email);

}
