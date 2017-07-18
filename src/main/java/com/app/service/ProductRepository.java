package com.app.service;

import org.springframework.data.repository.CrudRepository;

import com.app.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}
