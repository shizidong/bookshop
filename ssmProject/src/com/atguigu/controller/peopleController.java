package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class peopleController {

	@RequestMapping("demo")
	public ModelAndView demo(ModelAndView model){
		model.setViewName("main");
		model.addObject("name", "猿来入此");
		return model;
	}
	
	@RequestMapping("login1")
		public ModelAndView login(ModelAndView model){
			model.setViewName("login");
			return model;
		}
	}

