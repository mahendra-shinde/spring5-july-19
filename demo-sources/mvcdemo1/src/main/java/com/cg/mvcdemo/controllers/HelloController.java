package com.cg.mvcdemo.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello.obj")
public class HelloController {
	
	@GetMapping
	public ModelAndView process(){
			ModelAndView mv = new ModelAndView("hello");//VIEW
		mv.addObject("user", "Chris Evans");//MODEL
		mv.addObject("today", new Date());//MODEL
		return mv;
		}
	}
	
