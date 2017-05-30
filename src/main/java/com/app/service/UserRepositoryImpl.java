package com.app.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.app.security.Users;
@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepositoryCustom{
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			user = (Users) session.get(Users.class,username);
			user.getAuthorities().size();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			session.close();
		}
		return user;
	}
}
