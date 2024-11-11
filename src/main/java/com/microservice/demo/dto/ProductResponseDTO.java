package com.microservice.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
	
	/* Id of product */
	private long id;
	
	/* name of product */
	private String name;
	
	/* product description */
	private String description;
	
	/* product price */
	private double price;

}
