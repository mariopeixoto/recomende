package br.recomende.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/signin")
public class TestController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String test() {
		return "sign/signin";
	}
	
}
