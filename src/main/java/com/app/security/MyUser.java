package com.app.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users")
public class MyUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "username" , unique = true, nullable = true)
	@NotNull
	@Size(min=2, max=30)
	private String username;
	@Column(name="displayName")
	private String displayName;
	@Column(name = "password" , nullable = false)
	@NotNull
	@Size(min=2, max=12)
	private String password;
	//@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name = "authority")
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "user")
	private Collection<Authorities> authorities = new ArrayList<Authorities>();
	@Column(name="enabled" , nullable = false)
	private int enabled = 1;
	@Column(name="mail")
	@NotNull
	@Size(min=2, max=50)
    @Email
	private String email;
	@Column(name="phone")
	private String phone;
	@Column(name="img")
	private String imageUrl;
	@Column(name="provider")
	private String provider;

	public MyUser (){
		
	}
	
	public Authorities getAuthority(){
		
		return null;
	}
	
	public MyUser(String username, String password,String mail,String phone) {
		this.username = username;
		this.password = password;
		this.email=mail;
		this.phone=phone;
		//this.addAuthority(role);
	}

	public void addAuthority (String authority){
		this.authorities.add(new Authorities(authority,this));
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public String getPassword() {
		return this.password;
	}
	public String getUsername() {
		return this.username;
	}
	public boolean isAccountNonExpired() {
		return true;
	}
	public boolean isAccountNonLocked() {
		return true;
	}
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	public boolean isEnabled() {
		return true;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password + ", authorities=" + authorities + ", enabled="
				+ enabled + ", mail=" + email + ", phone=" + phone + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
}
