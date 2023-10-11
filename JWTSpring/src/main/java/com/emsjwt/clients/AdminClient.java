package com.emsjwt.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emsjwt.dtos.DealerUpdateDTO;
import com.emsjwt.dtos.FarmerRatingDTO;
import com.emsjwt.dtos.FarmerUpdateDTO;
import com.emsjwt.model.Admin;
import com.emsjwt.model.Dealer;
import com.emsjwt.model.Farmer;



@FeignClient(url = "http://localhost:5003/admin",name = "secureAdminClient")
public interface AdminClient {
	
	@PostMapping("/add")
    public Admin addAdmin(@RequestBody Admin admin);

    @PutMapping("/update/{id}")
    public Admin updateAdmin(@RequestBody Admin admin, @PathVariable String id);
    
    @GetMapping("/findByEmail/{email}")
    public Admin findByEmail(@PathVariable String email);

    @GetMapping("/activeFarmers")
    public List<Farmer> getActiveFarmers();

    @GetMapping("/inactiveFarmers")
    public List<Farmer> getInactiveFarmers();

    @GetMapping("/activeDealers")
    public List<Dealer> getActiveDealers();

    @GetMapping("/inactiveDealers")
    public List<Dealer> getInactiveDealers();

    @PutMapping("/updateFarmer/{email}")
    public FarmerUpdateDTO updateFarmer(@RequestBody FarmerUpdateDTO farmerUpdateDTO, @PathVariable String email);

    @PutMapping("/updateDealer/{email}")
    public DealerUpdateDTO updateDealer(@RequestBody DealerUpdateDTO dealerUpdateDTO, @PathVariable String email) ;

    @GetMapping("/farmersRatings")
    public List<FarmerRatingDTO> getFarmersRatings();

}
