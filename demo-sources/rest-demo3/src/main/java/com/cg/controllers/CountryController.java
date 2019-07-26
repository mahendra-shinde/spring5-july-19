package com.cg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Country;
import com.cg.services.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryController {

	@Autowired CountryService service;
	
	@GetMapping(value="/code/{code}")	
	public ResponseEntity<Country> findbycode(@PathVariable String code) {
		Country country = service.byCode(code);
		if(country==null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(country, HttpStatus.FOUND);
	}
	
	@GetMapping(value="/continent-{continent}")
	public ResponseEntity<List<Country>> findbycontinent(@PathVariable String continent){
		List<Country> countries = service.byContinent(continent);
		if(countries ==null || countries.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(countries,HttpStatus.OK);
	}
	
	@PostMapping(value="/new",consumes= {"application/json"})
	public ResponseEntity<String> save(@RequestBody Country country) {
		try {
		service.create(country);
		}catch(RuntimeException ex) {
			return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Record created",HttpStatus.OK);
	}
	
	//TODO: Implement ResponseEntity to return SUCCESS when record is updated
	//		Else, return an error
	@PutMapping(value="/update",consumes= {"application/json"})
	public String update(@RequestBody Country country) {
		service.update(country);
		return "country updated";
	}
	
}
