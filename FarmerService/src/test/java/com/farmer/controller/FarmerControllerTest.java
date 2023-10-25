package com.farmer.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.farmer.dtos.CropDetailDTO;
import com.farmer.dtos.UpdateDetailDTO;
import com.farmer.model.Crop;
import com.farmer.model.Farmer;
import com.farmer.model.Transaction;
import com.farmer.service.FarmerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.mock.web.MockHttpServletRequest;

public class FarmerControllerTest {

    @Mock
    private FarmerServiceImpl farmerService;

    @InjectMocks
    private FarmerController farmerController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testRegisterFarmer() throws Exception {
        Farmer farmer = new Farmer();
        when(farmerService.addFarmer(any(Farmer.class))).thenReturn(farmer);

        ResponseEntity<Farmer> response = farmerController.registerFarmer(farmer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmer, response.getBody());
        verify(farmerService).addFarmer(any(Farmer.class));
    }

    @Test
    void testAddCrop() throws Exception {
        Crop crop = new Crop();
        when(farmerService.addCrop(any(Crop.class), anyString())).thenReturn(new Farmer());

        ResponseEntity<Farmer> response = farmerController.addCrop(crop, "test@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(farmerService).addCrop(any(Crop.class), anyString());
    }

    @Test
    void testUpdateFarmerByEmail() throws Exception {
        UpdateDetailDTO updateDetailDTO = new UpdateDetailDTO();
        when(farmerService.updateFarmer(any(UpdateDetailDTO.class), anyString())).thenReturn(updateDetailDTO);

        ResponseEntity<UpdateDetailDTO> response = farmerController.updateFarmer(updateDetailDTO, "test@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updateDetailDTO, response.getBody());
        verify(farmerService).updateFarmer(any(UpdateDetailDTO.class), anyString());
    }

    @Test
    void testUpdateFarmer() throws Exception {
        Farmer farmer = new Farmer();
        when(farmerService.updateFarmer(any(Farmer.class))).thenReturn(farmer);

        ResponseEntity<Farmer> response = farmerController.updateFarmer(farmer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmer, response.getBody());
        verify(farmerService).updateFarmer(any(Farmer.class));
    }

    @Test
    void testGetAllFarmers() throws Exception {
        List<Farmer> farmers = Collections.singletonList(new Farmer());
        when(farmerService.getAll()).thenReturn(farmers);

        ResponseEntity<List<Farmer>> response = farmerController.getAllFarmers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmers, response.getBody());
        verify(farmerService).getAll();
    }

    @Test
    void testFindFarmerById() throws Exception {
        Farmer farmer = new Farmer();
        when(farmerService.findFarmerById(anyString())).thenReturn(farmer);

        ResponseEntity<Farmer> response = farmerController.findFarmerById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmer, response.getBody());
        verify(farmerService).findFarmerById(anyString());
    }

    @Test
    void testFindFarmerByEmail() throws Exception {
        Farmer farmer = new Farmer();
        when(farmerService.findFarmerByEmail(anyString())).thenReturn(farmer);

        ResponseEntity<Farmer> response = farmerController.findFarmerByEmail("test@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmer, response.getBody());
        verify(farmerService).findFarmerByEmail(anyString());
    }

    @Test
    void testGetAllCrops() throws Exception {
        List<CropDetailDTO> cropDetails = Collections.singletonList(new CropDetailDTO());
        when(farmerService.getAllCrops()).thenReturn(cropDetails);

        ResponseEntity<List<CropDetailDTO>> response = farmerController.getAllCrops();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cropDetails, response.getBody());
        verify(farmerService).getAllCrops();
    }

    @Test
    void testGetCropsByType() throws Exception {
        List<CropDetailDTO> cropDetails = Collections.singletonList(new CropDetailDTO());
        when(farmerService.getCropsByCropType(anyString())).thenReturn(cropDetails);

        ResponseEntity<List<CropDetailDTO>> response = farmerController.getCropsByType("Wheat");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cropDetails, response.getBody());
        verify(farmerService).getCropsByCropType(anyString());
    }

    @Test
    void testGetFarmerHistory() throws Exception {
        List<Transaction> transactions = Collections.singletonList(new Transaction());
        when(farmerService.findTransactionsByFarmerId(anyString())).thenReturn(transactions);

        ResponseEntity<List<Transaction>> response = farmerController.getFarmerHistory("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactions, response.getBody());
        verify(farmerService).findTransactionsByFarmerId(anyString());
    }

    @Test
    void testDeleteFarmerById() throws Exception {
        Farmer farmer = new Farmer();
        when(farmerService.deleteFarmerById(anyString())).thenReturn(farmer);

        ResponseEntity<Farmer> response = farmerController.deleteFarmerById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(farmer, response.getBody());
        verify(farmerService).deleteFarmerById(anyString());
    }
}
