package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {
	
	 @RequestMapping("/home")
	    public String index() {
	        return "Greetings from Spring Boot!";
	    }
}