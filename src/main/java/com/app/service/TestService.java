package com.app.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.app.domain.Test;
@Service
public interface TestService extends CrudRepository<Test, Integer>{

}
