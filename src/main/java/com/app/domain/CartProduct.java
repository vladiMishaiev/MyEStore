package com.app.domain;

public class CartProduct {
	private Product product;
	private int quantity;
	private double sum;
	
	

	public CartProduct(Product product, int quantity) {
		super();
		this.product = product;
		this.setQuantity(quantity);
	}
	
	public CartProduct(){}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		setSum(quantity*product.getPrice());
	}
	
	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
	
}
