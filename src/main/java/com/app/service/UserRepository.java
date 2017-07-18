package com.app.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.app.security.MyUser;

@Repository
public interface UserRepository extends CrudRepository<MyUser,String>,UserRepositoryCustom{

}
