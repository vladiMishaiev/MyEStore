package com.app.service;

import org.springframework.data.repository.CrudRepository;

import com.app.security.Authorities;
import com.app.security.MyUser;

public interface SupportUserService extends  CrudRepository<MyUser,String>{

}
