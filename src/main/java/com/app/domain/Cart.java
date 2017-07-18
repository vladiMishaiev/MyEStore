package com.app.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
	private double totalSum;
	private List<CartProduct> products;
	
	public Cart (){
		totalSum=0;
		products = new ArrayList<>();
	}
	public void emptyCart(){
		Iterator<CartProduct> itr = products.iterator();
		while (itr.hasNext()){
			itr.next();
			itr.remove();
		}
		this.setTotalSum(0);
	}
	
	public boolean AddProductToCart (Product product, int quantity){
		CartProduct cartProduct = getCartProduct(product.getDealNum());
		//new product
		if (cartProduct==null){
			//should never get here
			cartProduct = new CartProduct(product, quantity);
			products.add(cartProduct);
		}else{
			cartProduct.setQuantity(quantity);
		}
		CalcTotalSum();
		return true;
	}
	public boolean AddProductToCart (Product product){
		CartProduct cartProduct = getCartProduct(product.getDealNum());
		//new product
		if (cartProduct==null){
			cartProduct = new CartProduct(product, 1);
			products.add(cartProduct);
		}else{
			cartProduct.setQuantity(cartProduct.getQuantity()+1);
		}
		CalcTotalSum();
		return true;
	}
	
	public boolean RemoveProductFromCart (CartProduct cartProduct){
		boolean res = products.remove(cartProduct);
		if (res)
			CalcTotalSum();
		return res;
	}
	
	public boolean RemoveProductFromCart (int productID){
		CartProduct cartProduct= getCartProduct(productID);
		boolean res = products.remove(cartProduct);
		if (res)
			CalcTotalSum();
		return res;
	}
	
	private boolean isProductInCart(Product product){
		Iterator<CartProduct> itr = products.iterator();
		while (itr.hasNext()){
			CartProduct cartProduct = itr.next();
			if (cartProduct.getProduct().getDealNum()==product.getDealNum()){
				return true;
			}
		}
		return false;
	}
	private boolean isProductInCart(int productID){
		Iterator<CartProduct> itr = products.iterator();
		while (itr.hasNext()){
			CartProduct cartProduct = itr.next();
			if (cartProduct.getProduct().getDealNum()==productID){
				return true;
			}
		}
		return false;
	}
	public CartProduct getCartProduct(int productID){
		Iterator<CartProduct> itr = products.iterator();
		while (itr.hasNext()){
			CartProduct cartProduct = itr.next();
			if (cartProduct.getProduct().getDealNum()==productID){
				return cartProduct;
			}
		}
		return null;
	}
	public void CalcTotalSum(){
		double sum =0;
		Iterator<CartProduct> itr = products.iterator();
		while (itr.hasNext()){
			CartProduct cartProduct = itr.next();
			sum+=cartProduct.getSum();
		}
		this.setTotalSum(sum);
	}
	
	public double getTotalSum() {
		return totalSum;
	}
	public void setTotalSum(double totalSum) {
		this.totalSum = totalSum;
	}
	public List<CartProduct> getProducts() {
		return products;
	}
	public void setProducts(List<CartProduct> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Cart [totalSum=" + totalSum + ", products=" + products + "]";
	}
	
	
	
}
