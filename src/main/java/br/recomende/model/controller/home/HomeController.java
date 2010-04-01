package br.recomende.model.controller.home;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.recomende.infra.exception.http.ForbiddenResourceException;
import br.recomende.infra.user.Role;
import br.recomende.infra.user.Roles;
import br.recomende.infra.user.User;

@Controller
public class HomeController {
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("home/home");
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<GrantedAuthority> authorities = user.getAuthorities();
		List<String> rolesLowerCased = new ArrayList<String>();
		for (GrantedAuthority role : authorities) {
			rolesLowerCased.add(role.getAuthority().toLowerCase());
		}
		modelAndView.addObject("roles", rolesLowerCased);
		return modelAndView;
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView homeCorrection() {
		return this.home();
	}
	
	@RequestMapping(value="/home/menu/{role}", method=RequestMethod.GET)
	public String menu(@PathVariable String role) {
		User user = this.getPrincipal();
		Role authority = new Role(Roles.get(role.toUpperCase()));
		if (user.getAuthorities().contains(authority)) {
			return "home/menu/" + role;
		} else {
			throw new ForbiddenResourceException();
		}
	}
	
	private User getPrincipal() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
}
