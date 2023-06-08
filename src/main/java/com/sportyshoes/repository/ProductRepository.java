package com.sportyshoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sportyshoes.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	Optional<Product> findByName(String fileName);
}
