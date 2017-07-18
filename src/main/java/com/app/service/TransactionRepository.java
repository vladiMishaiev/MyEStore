package com.app.service;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.domain.Transaction;
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{
	public ArrayList<Transaction> findByUserName(String userName);
}
