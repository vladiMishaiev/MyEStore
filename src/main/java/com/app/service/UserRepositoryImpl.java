package com.app.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.app.security.MyUser;
@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepositoryCustom{
	@Autowired
    private SessionFactory sessionFactory;
	@Autowired
	private SupportUserService userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findOne(username);
		/*MyUser user = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			user = (MyUser) session.get(MyUser.class,username);
			user.getAuthorities().size();
			System.err.println("before comit");
			session.getTransaction().commit();
			System.err.println("Got user :  " + user);
		} catch (Exception e) {
			e.getMessage();
		} finally {
			session.close();
		}
		return user;*/
	}
}
