package com.cg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Country findbycode(@PathVariable String code) {
		return service.byCode(code);
	}
	
	@GetMapping(value="/continent-{continent}")
	public List<Country> findbycontinent(@PathVariable String continent){
		return service.byContinent(continent);
	}
	
	@PostMapping(value="/new",consumes= {"application/json"})
	public String save(@RequestBody Country country) {
		service.create(country);
		return "country added!";
	}
	
	@PutMapping(value="/update",consumes= {"application/json"})
	public String update(@RequestBody Country country) {
		service.update(country);
		return "country updated";
	}
	
}
