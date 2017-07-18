package com.app.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.security.Authorities;
@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities, Integer>{

}
