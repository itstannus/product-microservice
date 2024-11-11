package com.microservice.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.demo.dto.ProductRequestDTO;
import com.microservice.demo.dto.ProductResponseDTO;
import com.microservice.demo.entity.Product;
import com.microservice.demo.repository.ProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	
	public List<ProductResponseDTO> findAllProducts(){
		List<Product> products =  productRepository.findAll();
		
		//TODO : use mapstruct from product to productresponsedto
		//TODO : send exception if list is empty
		List<ProductResponseDTO> productDtos = new ArrayList<>();
		for(Product product : products) {
			ProductResponseDTO productDTO = new ProductResponseDTO();
			productDTO.setName(product.getName());
			productDTO.setDescription(product.getDescription());
			productDTO.setPrice(product.getPrice());
			productDtos.add(productDTO);
		}
		
		return productDtos;
	}

	@Override
	public ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO) {
		//TODO mapstruct to convert from productrequestdto to product
		Product product=Product.builder()
				.name(productRequestDTO.getName())
				.description(productRequestDTO.getDescription())
				.price(productRequestDTO.getPrice())
				.build();
		
		Product savedProduct = productRepository.save(product);
		
		//TODO mapstruct to convert from product to productresponsedto
		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		productResponseDTO.setId(savedProduct.getId());
		productResponseDTO.setName(savedProduct.getName());
		productResponseDTO.setDescription(savedProduct.getDescription());
		productResponseDTO.setPrice(savedProduct.getPrice());
		
		
		return productResponseDTO;
	}
	

}
