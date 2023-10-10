package com.dealer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealer.clients.EmailClient;
import com.dealer.clients.FarmerClient;
import com.dealer.clients.TransactionClient;
import com.dealer.dtos.EmailModel;
import com.dealer.dtos.TransactionDTO;
import com.dealer.dtos.UpdateDetailDTO;
import com.dealer.exceptions.InvalidTransaction;
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
	public Dealer addDealer(Dealer d) {
		
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
		// TODO Auto-generated method stub
		return dr.findAll();
	}

	@Override
	public Dealer addRequirement(String requirement, String email) {
		// TODO Auto-generated method stub
		Dealer dealer = dr.findByEmail(email);
		dealer.addReqirements(requirement);
		return dr.save(dealer);
	}

	@Override
	public UpdateDetailDTO updateDealer(UpdateDetailDTO f, String email) {
		// TODO Auto-generated method stub
		Dealer farmer = dr.findByEmail(email);
		farmer.setName(f.getName());
		farmer.setAge(f.getAge());
		farmer.setPassword(f.getPassword());
		farmer.setAccountNumber(f.getAccountNumber());
		farmer.setGender(f.getGender());
		farmer.setAccountBalance(f.getAccountBalance());
		dr.save(farmer);
		return f;
	}

	@Override
	public Dealer findDealerById(String id) {
		// TODO Auto-generated method stub
		return dr.findById(id).get();
	}

	@Override
	public Dealer findDealerByEmail(String email) {
		// TODO Auto-generated method stub
		return dr.findByEmail(email);
	}
	public static double calculateTotal(int price,int quantity) {
		return price*quantity;
	}

	@Override
	public Transaction buyCrop(String farmerId, String dealerId, String cropType, int quantity) throws InvalidTransaction {
		// TODO Auto-generated method stub
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
		return transactionClient.getDealerHistory(id);
	}

	@Override
	public boolean runScan(String dealerId) {
		Dealer dealer = dr.findById(dealerId).get();
		List<String> required = dealer.getRequirements();
		for(String cropType: required) {
			
		}
		// TODO Auto-generated method stub
		return false;
	}

	

}
