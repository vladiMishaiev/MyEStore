package com.app.config;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;


@Configuration
public class SessionFactoryConfiguration {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean
	public SessionFactory getSessionFactory() {
	    if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
	    	//System.out.println("** ** ** error");
	        throw new NullPointerException("factory is not a hibernate factory");
	    }
	    //System.out.println("** ** ** success");
	    return entityManagerFactory.unwrap(SessionFactory.class);
	}

}
