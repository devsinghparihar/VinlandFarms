package com.farmer.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.farmer.clients.EmailClient;
import com.farmer.clients.TransactionClient;
import com.farmer.dtos.CropDetailDTO;
import com.farmer.dtos.EmailModel;
import com.farmer.dtos.TransactionDTO;
import com.farmer.dtos.UpdateDetailDTO;
import com.farmer.model.Crop;
import com.farmer.model.Farmer;
import com.farmer.model.Transaction;
import com.farmer.repository.FarmerRepository;
@ExtendWith(MockitoExtension.class)
public class FarmerServiceImplTest {

	
	
	@Mock
    private FarmerRepository fr;
	@InjectMocks
	private FarmerServiceImpl farmerService; 
    private EmailClient emailClient = new EmailClient() {
		
		@Override
		public String sendMail(EmailModel mail) {
			// TODO Auto-generated method stub
			return "Success";
		}
	};
    private TransactionClient transactionClient = new TransactionClient() {
		
		@Override
		public List<Transaction> getFarmerHistory(String id) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Transaction createTransaction(TransactionDTO dto) {
			// TODO Auto-generated method stub
			return null;
		}
	};

    

    @Test
    void testAddFarmer() {
        // Arrange
        Farmer farmer = new Farmer();
        EmailModel email = new EmailModel();
        when(emailClient.sendMail(email)).thenReturn("Success");
        when(fr.save(farmer)).thenReturn(farmer);

        // Act
        Farmer result = farmerService.addFarmer(farmer);

        // Assert
        assertNotNull(result);
        assertEquals(farmer, result);
        verify(emailClient).sendMail(email);
        verify(fr).save(farmer);
    }

    @Test
    void testGetAll() {
        // Arrange
        List<Farmer> farmers = new ArrayList<>();
        when(fr.findAll()).thenReturn(farmers);

        // Act
        List<Farmer> result = farmerService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(farmers, result);
        verify(fr).findAll();
    }

    @Test
    void testAddCrop() {
        // Arrange
        Crop crop = new Crop();
        Farmer farmer = new Farmer();
        when(fr.findByEmail(anyString())).thenReturn(farmer);
        when(fr.save(farmer)).thenReturn(farmer);

        // Act
        Farmer result = farmerService.addCrop(crop, "test@example.com");

        // Assert
        assertNotNull(result);
        assertEquals(farmer, result);
        assertTrue(farmer.getCrops().contains(crop));
        verify(fr).findByEmail("test@example.com");
        verify(fr).save(farmer);
    }

    @Test
    void testUpdateFarmer() {
        // Arrange
        UpdateDetailDTO updateDetail = new UpdateDetailDTO("John Doe", "Male", "New Location",30);
        Farmer farmer = new Farmer();
        when(fr.findByEmail(anyString())).thenReturn(farmer);

        // Act
        UpdateDetailDTO result = farmerService.updateFarmer(updateDetail, "test@example.com");

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", farmer.getName());
        assertEquals(30, farmer.getAge());
        assertEquals("Male", farmer.getGender());
        assertEquals("New Location", farmer.getLocation());
        verify(fr).findByEmail("test@example.com");
        verify(fr).save(farmer);
    }

    @Test
    void testGetAllCrops() {
        // Arrange
        Farmer farmer = new Farmer();
        Crop crop = new Crop();
        crop.setCropType("Wheat");
        crop.setCropPrice(10);
        crop.setCropQuantity(50);
        farmer.addCropToInventory(crop);
        when(fr.findAll()).thenReturn(List.of(farmer));

        // Act
        List<CropDetailDTO> result = farmerService.getAllCrops();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Wheat", result.get(0).getCropType());
        assertEquals(10, result.get(0).getCropPrice());
        assertEquals(50, result.get(0).getCropQuantity());
    }

    @Test
    void testGetCropsByCropType() {
        // Arrange
    	
        Farmer farmer1 = new Farmer();
        Crop crop1 = new Crop();
        crop1.setCropType("Wheat");
        CropDetailDTO cropDetail1 = new CropDetailDTO("farmer1", "John", "john@example.com","12345", "Wheat", 10, 50, "Location1");
        farmer1.addCropToInventory(crop1);

        Farmer farmer2 = new Farmer();
        Crop crop2 = new Crop();
        crop2.setCropType("Rice");
        CropDetailDTO cropDetail2 = new CropDetailDTO("farmer1", "John", "john@example.com","12345", "Rice", 10, 50, "Location1");
        farmer2.addCropToInventory(crop2);

        when(fr.findAll()).thenReturn(List.of(farmer1, farmer2));

        // Act
        List<CropDetailDTO> result = farmerService.getCropsByCropType("Rice");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(cropDetail2, result.get(0));
    }

    @Test
    void testFindFarmerById() {
        // Arrange
        Farmer farmer = new Farmer();
        farmer.setFarmerId("1");
        when(fr.findById("1")).thenReturn(java.util.Optional.of(farmer));

        // Act
        Farmer result = farmerService.findFarmerById("1");

        // Assert
        assertNotNull(result);
        assertEquals("1", result.getFarmerId());
        verify(fr).findById("1");
    }

    @Test
    void testFindFarmerByEmail() {
        // Arrange
        Farmer farmer = new Farmer();
        when(fr.findByEmail("test@example.com")).thenReturn(farmer);

        // Act
        Farmer result = farmerService.findFarmerByEmail("test@example.com");

        // Assert
        assertNotNull(result);
        assertEquals(farmer, result);
        verify(fr).findByEmail("test@example.com");
    }

//    @Test
//    void testUpdateFarmer() {
//        // Arrange
//        Farmer farmer = new Farmer();
//        when(fr.save(farmer)).thenReturn(farmer);
//
//        // Act
//        Farmer result = farmerService.updateFarmer(farmer);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(farmer, result);
//        verify(fr).save(farmer);
//    }

    @Test
    void testFindTransactionsByFarmerId() {
        // Arrange
        List<Transaction> transactions = new ArrayList<>();
        when(transactionClient.getFarmerHistory("1")).thenReturn(transactions);

        // Act
        List<Transaction> result = farmerService.findTransactionsByFarmerId("1");

        // Assert
        assertNotNull(result);
        assertEquals(transactions, result);
        verify(transactionClient).getFarmerHistory("1");
    }

    @Test
    void testDeleteFarmerById() {
        // Arrange
        Farmer farmer = new Farmer();
        farmer.setFarmerId("1");
        when(fr.findById("1")).thenReturn(java.util.Optional.of(farmer));

        // Act
        Farmer result = farmerService.deleteFarmerById("1");

        // Assert
        assertNotNull(result);
        assertEquals("1", result.getFarmerId());
        verify(fr).findById("1");
        verify(fr).deleteById("1");
    }

    // Add more test cases as needed
}
