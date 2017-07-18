package com.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "dealNum" , unique = true, nullable = false)
	private int dealNum;
	@Column(name = "country")
	private String country;
	@Column(name = "imgUrl")
	private String imgUrl;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	@Lob
	private String description;
	@Column(name = "price")
	private Double price;
	
	public Product() {}
	public Product(String country, String imgUrl, String description,String title, Double price) {
		this.country = country;
		this.imgUrl = imgUrl;
		this.description = description;
		this.price = price;
		this.title=title;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDealNum() {
		return dealNum;
	}
	public void setDealNum(int dealNum) {
		this.dealNum = dealNum;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [dealNum=" + dealNum + ", country=" + country + ", imgUrl=" + imgUrl + ", title=" + title
				+ ", description=" + description + ", price=" + price + "]";
	}
	
}
