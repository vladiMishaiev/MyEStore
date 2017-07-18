package com.app.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.app.service.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	//@Autowired
	//private DataSource dataSource;
	//@Autowired
	//private UserDao userDao;
	@Autowired
	private UserRepository userService;
	//who is allowed to enter

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
				//login and registration
				//.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/signUp").permitAll()
				.antMatchers("/submitSignUp").permitAll()
				
				//Social registration 
				.antMatchers("/connect/**").permitAll()
				.antMatchers("/process/**").permitAll()
				.antMatchers("/fragments/**").permitAll()
				
				//Admin
				.antMatchers("/products/addNewProduct").hasAuthority("ROLE_ADMIN")
				.antMatchers("/products/{productID}/remove").hasAuthority("ROLE_ADMIN")
				.antMatchers("/tickets").hasAuthority("ROLE_ADMIN")
				
				//resources
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/js/**").permitAll()
				
				.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/products",true)
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
	
	
}
