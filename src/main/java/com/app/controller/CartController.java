package com.app.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.domain.Cart;
import com.app.domain.CartProduct;
import com.app.domain.Product;
import com.app.domain.Transaction;
import com.app.service.ProductRepository;
import com.app.service.TransactionRepository;

@Controller
public class CartController {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private TransactionRepository transactionRepo;
	@Autowired
	private Cart cart;
	
	@RequestMapping(value = {"/cart"}, method = RequestMethod.GET)
	public String getCartPage(Model model){
		//ArrayList<CartProduct> cartProducts = (ArrayList<CartProduct>) cart.getProducts();
		//System.out.println("size is : "+cart.getProducts().size());
		model.addAttribute("cart", cart);
		return "cart";
	}
	
	@RequestMapping(value = {"/cart/{productID}"}, method = RequestMethod.GET)
	public String addProductToCart(@RequestParam(value="quantity", required=false) Integer quantity, @PathVariable("productID") int productID, HttpServletRequest request){
		//System.out.println("quantity is : " + quantity);
		Product product = productRepo.findOne(productID);
		if (quantity!=null && quantity>0)
			cart.AddProductToCart(product,quantity);
		else
			cart.AddProductToCart(product);
		
		String referer = request.getHeader("Referer");
	    //System.out.println("Referer is : " + referer);
		return "redirect:"+ referer;
	}
	
	@RequestMapping(value = {"/cart/{productID}/remove"}, method = RequestMethod.GET)
	public String removeProductFromCart(@PathVariable("productID") int productID){
		cart.RemoveProductFromCart(productID);
		return "redirect:/cart";
	}
	
	@RequestMapping(value = {"/cart/{productID}/inc"}, method = RequestMethod.GET)
	public String incCartProduct(@PathVariable("productID") int productID){
		CartProduct cartProduct = cart.getCartProduct(productID);
		if (cartProduct==null){
			cart.RemoveProductFromCart(productID);
		}
		cartProduct.setQuantity(cartProduct.getQuantity()+1);
		cart.CalcTotalSum();
		return "redirect:/cart";
	}
	
	@RequestMapping(value = {"/cart/{productID}/dec"}, method = RequestMethod.GET)
	public String decCartProduct(@PathVariable("productID") int productID){
		CartProduct cartProduct = cart.getCartProduct(productID);
		if (cartProduct==null || cartProduct.getQuantity()-1==0){
			cart.RemoveProductFromCart(productID);
		}
		cartProduct.setQuantity(cartProduct.getQuantity()-1);
		cart.CalcTotalSum();
		return "redirect:/cart";
	}
	
	@RequestMapping(value = {"/cart/checkout"}, method = RequestMethod.GET)
	public String checkout(Principal principal){
		Transaction transaction = new Transaction(new Date(),principal.getName(), cart.getTotalSum());
		transactionRepo.save(transaction);
		cart.emptyCart();
		return "redirect:/products";
	}
	
}
