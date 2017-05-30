package com.app.security;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="authorities")
public class Authorities implements GrantedAuthority{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_role_id", unique = true, nullable = false)
	private int userId;
	@Column(name = "authority", nullable = false)
	private String authority;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "username", nullable = false)
	private Users user;
	
	public Authorities(){}
	
	public Authorities (String authority,Users user){
		this.authority=authority;
		this.user=user;
	}
	
	
	public String getAuthority() {
		return this.authority;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Authorities [userId=" + userId + ", authority=" + this.getAuthority() + ", user=" + user.getUsername() + "]";
	}

}
