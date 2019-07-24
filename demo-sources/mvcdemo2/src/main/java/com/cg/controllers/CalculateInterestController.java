package com.cg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("calc-interest")
public class CalculateInterestController {

	@PostMapping
	public String calc(Model model, 
				@RequestParam("p") double principal, 
				@RequestParam("r") double rate, 
				@RequestParam("d") int duration) {
		double interest = principal * (rate/100/12) * duration;
		model.addAttribute("msg","Interest to be paid: "+interest);
		return "home";
	}
}
