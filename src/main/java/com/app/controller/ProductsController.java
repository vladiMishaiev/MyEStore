package com.app.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.app.domain.Cart;
import com.app.domain.Product;
import com.app.service.ProductRepository;

@Controller
public class ProductsController {
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private Cart cart;
	@RequestMapping (value = {"/products"}, method = RequestMethod.GET)
	public String productsPage(Model model){
		List<Product> products = (List<Product>) productRepo.findAll();
		System.out.println(products);
		model.addAttribute("products", products);
		return "products";
	}
	
	@RequestMapping (value = {"/products/{productID}"}, method = RequestMethod.GET)
	public String product(@PathVariable("productID") int productID,Model model){
		Product product = productRepo.findOne(productID);
		model.addAttribute("product", product);
		//productRepo.findOne(Integer.parseInt(productID));
		return "productInfo";
	}
	
	@RequestMapping (value = {"/products/{productID}/remove"}, method = RequestMethod.GET)
	public String removeProduct(@PathVariable("productID") int productID){
		productRepo.delete(productID);
		cart.RemoveProductFromCart(productID);
		return "redirect:/products";
	}
	
	@RequestMapping (value = {"/addNewProduct2"}, method = RequestMethod.GET)
	public String newwProduct(){
		Product p = new Product("THAILAND","images/raillyBeach.jpg", "description","Rally Beach", 500.0);
		productRepo.save(p);
		return "redirect:/products";
	}
	
	@RequestMapping (value = {"/products/addNewProduct"}, method = RequestMethod.GET)
	public String newProduct(Model model){
		model.addAttribute("product",new Product());
		return "newProductForm";
	}
	
	@RequestMapping (value = {"/products/addNewProduct"}, method = RequestMethod.POST)
	public String submitProduct(@Valid @ModelAttribute Product product,BindingResult result, ModelMap model ,  HttpServletRequest request ,@RequestParam("file") MultipartFile file){
		model.addAttribute("product",product);
		if (result.hasErrors()) 
		    return "newProductForm";
		
    	String fileName = null;
    	if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream = 
                        new BufferedOutputStream(new FileOutputStream(new File("C:/Users/vladi/workspace/MyEStore/src/main/resources/static/images/" + fileName)));
                product.setImgUrl("images/" + fileName);
                buffStream.write(bytes);
                buffStream.close();
                System.out.println("You have successfully uploaded " + fileName);
            } catch (Exception e) {
                System.out.println("You failed to upload " + fileName + ": " + e.getMessage());
            }
        } else {
            System.out.println("Unable to upload. File is empty.");
        }
		
		
		
		productRepo.save(product);		    
		return "redirect:/products";
	}
}
