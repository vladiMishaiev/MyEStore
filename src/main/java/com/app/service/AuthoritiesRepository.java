package com.app.service;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import com.app.security.Authorities;
@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities, Integer>{

}
