package com.cg.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.cg.entities.Message;

@RestController
@RequestMapping("/test")
public class HomeController {

	/**
	 * Process GET REQUEST on localhost:5000/test/	
	 * @return JSON or XML object of type MESSAGE
	 */
	@GetMapping(value="/",produces= {"application/json","application/xml"})
	public Message testGet() {
		return new Message("This is GET request");
	}

	/**
	 * Process POST REQUEST on localhost:5000/test/	
	 * @return JSON or XML object of type MESSAGE
	 */
	@PostMapping(value="/",produces= {"application/json","application/xml"})
	public Message testPost() {
		return new Message("This is POST request");
	}
	
	/**
	 * Process PUT REQUEST on localhost:5000/test/	
	 * @return XML object of type MESSAGE
	 */
	@PutMapping(value="/",produces= {"application/xml"})
	public Message testPut() {
		return new Message("This is PUT request");
	}
	

	/**
	 * Process DELETE REQUEST on localhost:5000/test/	
	 * @return JSON or XML object of type MESSAGE
	 */
	@DeleteMapping(value="/",produces= {"application/json","application/xml"})
	public Message testDelete() {
		return new Message("This is DELETE request");
	}
	
	
	
}
