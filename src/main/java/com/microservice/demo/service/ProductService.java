package com.microservice.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.demo.dto.ProductRequestDTO;
import com.microservice.demo.dto.ProductResponseDTO;


public interface ProductService {
	
	public List<ProductResponseDTO> findAllProducts();

	public ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO);

}
