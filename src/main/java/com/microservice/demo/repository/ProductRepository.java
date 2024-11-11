package com.microservice.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
