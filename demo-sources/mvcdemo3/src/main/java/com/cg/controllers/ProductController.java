package com.cg.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.entities.Product;

@Controller
@RequestMapping("/add-product")
public class ProductController {

	@GetMapping
	public String showForm(Model model) {
		Product p = new Product();
		p.setPrice(100D);
		// Creating and storing new empty product object
		// THIS object would be PASSED to FORM page as "modelAttribute"
		model.addAttribute("product",p);
		return "form";
	}
	
	@PostMapping
	public String submit(Model model,
			//ModelAttribute : Collect multiple parameters and group them
			//  				into an Object (Object class must be POJO)
				@Valid @ModelAttribute("product") Product product,
			// Collect all Conversion & Validation errors
				BindingResult results) {
		String msg1="";
		System.out.println("Processing "+product.getName());
		if(!results.hasErrors()) {
			msg1="No Errors found!";
			model.addAttribute("msg",msg1);
			model.addAttribute("productname",product.getName());
			return "success";
		}
		else {
			msg1=results.getErrorCount()+ " errors occurred!";
			model.addAttribute("msg",msg1);
			model.addAttribute(results.getAllErrors());
			return "form";
		}
	}
}
