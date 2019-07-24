package com.cg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String search(Model model, @RequestParam("q") String query) {
		System.out.println("Searching for "+query);
		model.addAttribute("msg","Product "+query
					+" is currently unavailable, please try later!");
		return "home";
	}
}
