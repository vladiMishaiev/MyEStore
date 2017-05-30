package com.app.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.app.security.Users;

@Repository
public interface UserRepository extends CrudRepository<Users,String>,UserRepositoryCustom{

}
