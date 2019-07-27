package com.cg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.entities.Book;
import com.cg.exceptions.ApplicationException;
import com.cg.services.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired private BookService service;
	
	//on GET request just show FORM
	@GetMapping("/delete")
	public String deleteFind() {
		return "delete-view";
	}
	
	//on POST request display details and delete button
	@PostMapping("/delete")
	public String deleteForm(Model model,@RequestParam int id) {
		try {
			Book book = service.findById(id);
			model.addAttribute("book", book);
		}catch(ApplicationException ex) {
			System.out.println("Error "+ex.getMessage());
			model.addAttribute("msg","No record found!");
		}
		return "delete-view";
	}
	
	//on POST request delete by id
	@PostMapping("/delete-by-id")
	public String deleteById(Model model,@RequestParam int id) {
		try {
			service.delete(id);
			model.addAttribute("msg","Book deleted successfuly!");
		}catch(ApplicationException ex) {
			System.out.println("Error "+ex.getMessage());
			model.addAttribute("msg","No record found!");
		}
		return "delete-view";
	}
	
	//on GET request just show FORM
	@GetMapping("/edit")
	public String editView() {
		return "edit-view";
	}
	
	//on POST request display details
	@PostMapping("/edit")
	public String editDetails(Model model,@RequestParam int id) {
		try {
			Book book = service.findById(id);
			model.addAttribute("book", book);
		}catch(ApplicationException ex) {
			System.out.println("Error "+ex.getMessage());
			model.addAttribute("msg","No record found!");
		}
		return "edit-view";
	}
	
	//on GET request just show FORM
	@GetMapping("/view")
	public String viewOne(Model model) {
		return "view-one";
	}
	
	//on POST request display all book details
	@PostMapping("/view")
	public String viewDetails(Model model, @RequestParam int id) {
		try {
		Book book = service.findById(id);
		model.addAttribute("book", book);
		}catch(ApplicationException ex) {
			System.out.println("Error "+ex.getMessage());
			model.addAttribute("msg","No record found!");
		}
		return "view-one";
	}
	
	@GetMapping("/view-all")
	public String findAll(Model model) {
		String message = null;
		try {
		List<Book> books = service.getAll();
		message = books.size()+" records found!";
		model.addAttribute("books",books);
		}catch(ApplicationException ex) {
			message = "No records found!";
		}
		model.addAttribute("msg",message);
		return "view-all";
	}
	
	@PostMapping("/edit-save")
	public String editSave(Model model, @ModelAttribute Book book,BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("msg","Invalid form input");
		}
		try {
			service.update(book);
		}catch(ApplicationException ex) {
			System.out.println(ex.getMessage());
			model.addAttribute("msg",ex.getMessage());
		}
		return "edit-view";
	}
	
	
	@GetMapping("/create")
	public String newSave(Model model) {
			model.addAttribute("book",new Book());
			return "new-view";
		}
		
	@PostMapping("/create")
	public String newSave(Model model, @ModelAttribute Book book,BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("msg","Invalid form input");
		}
		try {
			service.save(book);
			model.addAttribute("msg","Book "+book.getTitle()+" added successfuly!");
			model.addAttribute("book",new Book()); //Empty the form fields on success
		}catch(ApplicationException ex) {
			System.out.println(ex.getMessage());
			model.addAttribute("msg",ex.getMessage());
		}
		return "new-view";
	}
}
