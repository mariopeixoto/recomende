package br.recomende.model.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import br.recomende.infra.user.User;
import br.recomende.model.profile.Profile;
import br.recomende.model.repository.UserRepository;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	private UserRepository userRepository;
	
	@Autowired
	public ProfileController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView get() {
		User user = this.getPrincipal();
		Profile profile = user.getProfile();
		ModelAndView modelAndView = new ModelAndView("profile/get");
		modelAndView.addObject("elements", profile.getElements());
		return modelAndView;
	}
	
	@RequestMapping(value="/{term}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void edit(@PathVariable String term, @RequestBody final MultiValueMap<String, String> params) {
		User user = this.getPrincipal();
		Double weight = Double.parseDouble(params.getFirst("weight"));
		user.getProfile().edit(term, weight);
		this.userRepository.put(user);
		
	}
	
	@RequestMapping(value="/{term}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void remove(@PathVariable String term) {
		User user = this.getPrincipal();
		user.getProfile().remove(term);
		this.userRepository.put(user);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView add(@RequestParam String term, @RequestParam Double weight) {
		User user = this.getPrincipal();
		user.getProfile().add(term, weight);
		this.userRepository.put(user);
		ModelAndView modelAndView = new ModelAndView("profile/post");
		modelAndView.addObject("term", term);
		modelAndView.addObject("weight", weight);
		return modelAndView;
	}
	
	private User getPrincipal() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
}
