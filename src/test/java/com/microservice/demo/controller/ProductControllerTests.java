package com.microservice.demo.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.demo.dto.ProductRequestDTO;
import com.microservice.demo.dto.ProductResponseDTO;
import com.microservice.demo.service.ProductServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ProductServiceImpl productService;
	
	
	
	@Test
	public void getAllProductsTest() throws Exception {
//		input : get mapping , OP: ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK); 
		
		// Mock 
		ProductResponseDTO responseDTO = ProductResponseDTO.builder().id(1L).name("dress").description("formal dress for women").price(100).build();
		when(productService.findAllProducts()).thenReturn(Collections.singletonList(responseDTO));
		
		
		
		// Actual call
		mockMvc.perform(
				get("/product")
				)
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(responseDTO))));
		
		// Validate
		verify(productService, times(1)).findAllProducts();
	}
	
	@Test
	public void createProductTest() throws Exception {
//		input : get mapping + productrequestdto , OP: Productresponsedto, HttpStatus.created); 
		
		// input
		ProductRequestDTO requestDTO = ProductRequestDTO.builder()
				.name("dress")
				.description("formal dress for women")
				.price(100)
				.build();
				
		// Mock 
		ProductResponseDTO responseDTO = ProductResponseDTO.builder()
				.id(1L)
				.name(requestDTO.getName())
				.description(requestDTO.getDescription())
				.price(requestDTO.getPrice())
				.build();
		when(productService.saveProduct(ArgumentMatchers.any(ProductRequestDTO.class))).thenReturn(responseDTO);
		
		
		
		// Actual call
		mockMvc.perform(
				post("/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestDTO))
				)
				.andExpect(status().isCreated())
				.andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
		
		// Validate
		verify(productService, times(1)).saveProduct(requestDTO);
	}

}
