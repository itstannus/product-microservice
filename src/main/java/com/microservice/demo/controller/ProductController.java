package com.microservice.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.demo.dto.ProductRequestDTO;
import com.microservice.demo.dto.ProductResponseDTO;
import com.microservice.demo.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
		return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK); 
	}
	
	@PostMapping
	public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO){
		return new ResponseEntity<>(productService.saveProduct(productRequestDTO), HttpStatus.CREATED);
	}
	

}
