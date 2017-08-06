package com.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class })
@EnableAutoConfiguration
@SpringBootApplication
public class MyEStoreApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MyEStoreApplication.class, args);
		
	}
}
