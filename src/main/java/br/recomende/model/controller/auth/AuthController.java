package br.recomende.model.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/signin")
public class AuthController {
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView signin(@RequestParam(required=false) Boolean error) {
		ModelAndView modelAndView = new ModelAndView("sign/signin");
		modelAndView.addObject("error", error);
		return modelAndView;
	}
	
}
