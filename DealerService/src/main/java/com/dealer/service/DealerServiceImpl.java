package com.dealer.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealer.clients.EmailClient;
import com.dealer.clients.FarmerClient;
import com.dealer.clients.TransactionClient;
import com.dealer.dtos.CropDetailDTO;
import com.dealer.dtos.EmailModel;
import com.dealer.dtos.TransactionDTO;
import com.dealer.dtos.UpdateDetailDTO;
import com.dealer.exceptions.DealerException;
import com.dealer.exceptions.EmailAlreadyExists;
import com.dealer.exceptions.InvalidTransaction;
import com.dealer.exceptions.ResourceNotFoundException;
import com.dealer.model.Crop;
import com.dealer.model.Dealer;
import com.dealer.model.Farmer;
import com.dealer.model.Transaction;
import com.dealer.repository.DealerRepository;

@Service
public class DealerServiceImpl implements DealerService {
	
	@Autowired
	DealerRepository dr;
	
	@Autowired
	EmailClient emailClient;
	
	@Autowired
	FarmerClient farmerClient;
	
	@Autowired
	TransactionClient transactionClient;

	@Override
	public Dealer addDealer(Dealer d)  {
		if(dr.findByEmail(d.getEmail()) !=null ) {
			throw new EmailAlreadyExists("User with email"+d.getEmail()+" already exists");
		}
		EmailModel email = new EmailModel();
		email.setTo(d.getEmail());
		email.setSubject("Welcome to Vinland Farms");
		email.setText("Dear Dealer,\r\n"
				+ "\r\n"
				+ "Welcome to Vinland Farms! We are excited to welcome you to our platform.\r\n"
				+ "\r\n"
				+ "Vinland Farms is your gateway to connecting with local farmers and accessing a wide variety of crops for purchase. As a registered dealer, you can explore and buy crops directly from our farming community.\r\n"
				+ "\r\n"
				+ "If you have any questions, need assistance, or would like to learn more about how Vinland Farms works, please feel free to reach out to us. We're here to assist you every step of the way.\r\n"
				+ "\r\n"
				+ "Thank you for choosing Vinland Farms. We look forward to a fruitful partnership.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "The Vinland Farms Team\r\n"
				+ "");
		emailClient.sendMail(email);
		// TODO Auto-generated method stub
		return dr.save(d);
	}

	@Override
	public List<Dealer> getAll() {
		
		if(dr.findAll().isEmpty()) {
			throw new ResourceNotFoundException("No dealer has registered yet");
		}
		// TODO Auto-generated method stub
		return dr.findAll();
	}

	@Override
	public Dealer addRequirement(String requirement, String email) {
		// TODO Auto-generated method stub
		if(dr.findByEmail(email) == null) {
			throw new ResourceNotFoundException("Dealer with "+email+" not found");
		}
		Dealer dealer = dr.findByEmail(email);
		dealer.addReqirements(requirement);
		return dr.save(dealer);
	}

	@Override
	public UpdateDetailDTO updateDealer(UpdateDetailDTO f, String email) {
		// TODO Auto-generated method stub
		if(dr.findByEmail(email) == null) {
			throw new ResourceNotFoundException("Dealer with "+email+" not found");
		}
		Dealer farmer = dr.findByEmail(email);
		farmer.setName(f.getName());
		farmer.setAge(f.getAge());
//		farmer.setPassword(f.getPassword());
//		farmer.setAccountNumber(f.getAccountNumber());
		farmer.setGender(f.getGender());
//		farmer.setAccountBalance(f.getAccountBalance());
		dr.save(farmer);
		return f;
	}

	@Override
	public Dealer findDealerById(String id) {
		// TODO Auto-generated method stub
		if(dr.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("Dealer with "+id+" not found");
		}
		return dr.findById(id).get();
	}

	@Override
	public Dealer findDealerByEmail(String email) {
		// TODO Auto-generated method stub
		if(dr.findByEmail(email) == null) {
			throw new ResourceNotFoundException("Dealer with "+email+" not found");
		}
		return dr.findByEmail(email);
	}
	public static double calculateTotal(int price,int quantity) {
		return price*quantity;
	}

	@Override
	public Transaction buyCrop(String farmerId, String dealerId, String cropType, int quantity) throws InvalidTransaction {
		// TODO Auto-generated method stub
		if(farmerClient.findFarmerById(farmerId) ==null || dr.findById(dealerId)==null ||  quantity <= 0 ){
			throw new InvalidTransaction("Invalid request");
		}
		Farmer farmer = farmerClient.findFarmerById(farmerId);
		Dealer dealer = dr.findById(dealerId).get();
		Double totalPrice = 0.0;
		int pricePerKg = 0;
		
		for(Crop crop: farmer.getCrops()) {
				if(crop.getCropType().equalsIgnoreCase(cropType)) {
					if(dealer.getAccountBalance()<calculateTotal(crop.getCropPrice(), quantity)) {
						throw new InvalidTransaction("Insufficient funds");
					}
					else if(crop.getCropQuantity()<quantity) {
						throw new InvalidTransaction("Invalid quantity");
						
					}
					else {
						dealer.setAccountBalance((long) (dealer.getAccountBalance()-calculateTotal(crop.getCropPrice(), quantity)));
						if(quantity == crop.getCropQuantity()) {
							totalPrice+=calculateTotal(crop.getCropPrice(), quantity);
							pricePerKg += crop.getCropPrice();
							List<Crop> updatedCrops = farmer.getCrops();
							updatedCrops.remove(crop);
							farmer.setCrops(updatedCrops);
							farmerClient.updateFarmer(farmer);
							dr.save(dealer);
							break;
							
							
						}
						else {
							totalPrice+=calculateTotal(crop.getCropPrice(), quantity);
							Crop updatedCrop = crop;
							pricePerKg += crop.getCropPrice();
							updatedCrop.setCropQuantity(crop.getCropQuantity()-quantity);
							List<Crop> updatedCrops = farmer.getCrops();
							updatedCrops.remove(crop);
							updatedCrops.add(updatedCrop);
							farmer.setCrops(updatedCrops);
							farmerClient.updateFarmer(farmer);
							dr.save(dealer);
							break;
						}
					}
				}
		}
		TransactionDTO trans = new TransactionDTO
		(farmerId, dealerId, dealer.getEmail(), farmer.getEmail(), cropType,
		quantity,pricePerKg,
		totalPrice);
		return transactionClient.createTransaction(trans);
		
		
	}

	@Override
	public List<Transaction> findTransactionsByDealerId(String id) {
		// TODO Auto-generated method stub
		if(dr.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("Dealer with "+id+" not found");
		}
		return transactionClient.getDealerHistory(id);
	}

	@Override
	public boolean runScan(String dealerId) {
		if(dr.findById(dealerId).isEmpty()) {
			throw new ResourceNotFoundException("Dealer with "+dealerId+" not found");
		}
		Dealer dealer = dr.findById(dealerId).get();
		List<String> required = dealer.getRequirements();
		List<String> availableCrops = new ArrayList<>();
		for(String cropType: required) {
			List<CropDetailDTO> crop=  farmerClient.getCropsByType(cropType);
			
			for(CropDetailDTO cropsInDto: crop) {
				availableCrops.add(cropsInDto.getCropType());
			}
			if(availableCrops.contains(cropType)) {
				return true;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> findRequirements(String dealerId) {
		if(dr.findById(dealerId).isEmpty()) {
			throw new ResourceNotFoundException("Dealer with "+dealerId+" not found");
		}
		Dealer dealer = dr.findById(dealerId).get();
		List<String> required = dealer.getRequirements();
		List<String> availableCrops = new ArrayList<>();
		for(String cropType: required) {
			List<CropDetailDTO> crop=  farmerClient.getCropsByType(cropType);
			
			for(CropDetailDTO cropsInDto: crop) {
				availableCrops.add(cropsInDto.getCropType());
			}
			
		}
		// TODO Auto-generated method stub
		Set<String> tempSet = new HashSet<>();
		List<String> avail = new ArrayList<>();
		tempSet.addAll(availableCrops);
		avail.addAll(tempSet);
		
		
		return avail;
	}

	@Override
	public Dealer deleteDealerById(String id) {
		if(dr.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("Dealer with "+id+" not found");
		}
		// TODO Auto-generated method stub
		Dealer dealer = dr.findById(id).get();
		dr.deleteById(id);
		return dealer;
	}

	@Override
	public String addMoneyToWallet(String id, long money) {
		if(dr.findById(id).isEmpty()) {
			throw new ResourceNotFoundException("Dealer with "+id+" not found");
		}
		else if(money <= 0) {
			throw new InvalidTransaction("You cannot add negative amount");
		}
		// TODO Auto-generated method stub
		Dealer d = dr.findById(id).get();
		d.setAccountBalance(d.getAccountBalance()+money);
		dr.save(d);
		return "Balance updated, current balance: "+d.getAccountBalance();
	}
	
	

}
