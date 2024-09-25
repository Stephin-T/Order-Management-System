package com.training.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sorry")
public class Sorry {
	
	
	@GetMapping
	public String sorry() {
		
		 return "I am sorry , I coudn't make it as we thought.";
	}

}
