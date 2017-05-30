package com.app.security;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;

import com.app.service.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	//@Autowired
	//private UserDao userDao;
	@Autowired
	private UserRepository userService;
	//who is allowed to enter

    
    
    @Autowired
    private FacebookConnectionSignup facebookConnectionSignup;
	
    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    
    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;
    
    
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth
			//.inMemoryAuthentication()
				//.withUser("admin").password("admin").roles("USER");
			.userDetailsService(userService);
			//.jdbcAuthentication()
			//.dataSource(dataSource);
	}
	
	protected void configure(HttpSecurity http) throws Exception{
		http
		.csrf().disable()
			.authorizeRequests()
				//login section
				//.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/signUp").permitAll()
				.antMatchers("/signin/**").permitAll()
				.antMatchers("/submitSignUp").permitAll()
				
				//home section
				.antMatchers("/home").hasAuthority("ROLE_USER")
				.antMatchers("/userHomeUpdate").hasAnyAuthority("ROLE_USER")
				
				//user work table
				.antMatchers("/workTable").hasAnyAuthority("ROLE_USER")
				
				//resources
				.antMatchers("/resources/**").permitAll()
				//.antMatchers("/static/**").permitAll().anyRequest().permitAll()
				//.antMatchers("/css/**").permitAll().anyRequest().permitAll()
				//.antMatchers("/static/**").permitAll().anyRequest().permitAll()
				//.antMatchers("static/**").permitAll().anyRequest().permitAll()
				//.antMatchers("static/").permitAll().anyRequest().permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/home",true)
			.and()
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/login")
					.deleteCookies("JSESSIONID").invalidateHttpSession(true)
			.and()
				.httpBasic();
		
		//http.antMatcher("/signUp").csrf().disable().antMatcher("/submitSignUp").csrf().disable();
		//web.ignoring().antMatchers("/signUp")
		//.antMatchers("/submitSignUp");
	}
	
	@Bean
    // @Primary
    public ProviderSignInController providerSignInController() {
        ((InMemoryUsersConnectionRepository) usersConnectionRepository).setConnectionSignUp(facebookConnectionSignup);
        return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new FacebookSignInAdapter());
    }
}
