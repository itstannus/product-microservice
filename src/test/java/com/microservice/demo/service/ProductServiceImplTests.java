package com.microservice.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservice.demo.dto.ProductRequestDTO;
import com.microservice.demo.dto.ProductResponseDTO;
import com.microservice.demo.entity.Product;
import com.microservice.demo.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTests {

	@InjectMocks
	private ProductServiceImpl productService;

	@Mock
	private ProductRepository productRepository;

	@Test
	@DisplayName("find all products")
	public void findAllProductsTest() {
		Product dress = Product.builder().name("dress").description("formal dress for women").price(100).build();
		Product pants = Product.builder().name("pants").description("formal pants for women").price(200).build();
		List<Product> products = List.of(dress, pants);

		when(productRepository.findAll()).thenReturn(products);

		List<ProductResponseDTO> productResponse = productService.findAllProducts();

		verify(productRepository, times(1)).findAll();

		assertNotNull(productResponse);
		assertEquals(productResponse.size(), products.size());
		assertEquals(productResponse.get(0).getName(), dress.getName());
		assertEquals(productResponse.get(0).getDescription(), dress.getDescription());
		assertEquals(productResponse.get(0).getPrice(), dress.getPrice());

	}

	@Test
	@DisplayName("create product")
	public void saveProductTest() {

		//Mock
		Product dress = Product.builder().id(1L).name("dress").description("formal dress for women").price(100).build();
		when(productRepository.save(any(Product.class))).thenReturn(dress);

		// Actual service call
		ProductRequestDTO requestDTO = ProductRequestDTO.builder()
				.name(dress.getName())
				.description(dress.getDescription())
				.price(dress.getPrice())
				.build();
		ProductResponseDTO savedProductResponse = productService.saveProduct(requestDTO);
		
		// Validate data
		verify(productRepository, times(1)).save(any(Product.class));
		
		assertNotNull(savedProductResponse);
		assertEquals(savedProductResponse.getId(), dress.getId());
		assertEquals(savedProductResponse.getName(), requestDTO.getName());
		assertEquals(savedProductResponse.getDescription(), requestDTO.getDescription());
		assertEquals(savedProductResponse.getPrice(), requestDTO.getPrice());
	}

}
