package com.thyemleaf.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {	
	@RequestMapping(value="/about", method = RequestMethod.GET)
	public String about(Model model) {
	    System.out.println("It is running");
	    model.addAttribute("name", "Harsh");
	    model.addAttribute("CurrentDate", new Date().toLocaleString());
	    
	    return "about";
	}
	
	@GetMapping("/iterate")
	public String iterateHandler(Model m) {
		List<String> names = List.of("Harsh","Akash","Pratik","Sanket");
		m.addAttribute("names",names);
		return "iterate"; 
		
	}
	
	@GetMapping("/service")
	public String service(Model m) {
		
		return "service";
	}
	
	@GetMapping("/aboutnew")
	public String aboutnew() {
		return "aboutnew";
	}



}
