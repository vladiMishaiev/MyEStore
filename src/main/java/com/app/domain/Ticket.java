package com.app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="tickets")
public class Ticket {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "num" , unique = true, nullable = false)
	private int ticketNum;
	@Column(name = "userID")
	private String username;
	@Column(name = "date")
	private Date date;
	@Column(name = "subject")
	private String subject;
	@Column(name = "massage")
	@Lob
	private String massage;
	@Column(name = "status")
	private String status;
	
	public Ticket(String username, Date date, String subject, String massage, String status) {
		this.username = username;
		this.date = date;
		this.subject = subject;
		this.massage = massage;
		this.status = status;
	}

	public Ticket(){}

	public int getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
}
