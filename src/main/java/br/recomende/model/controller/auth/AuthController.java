package br.recomende.model.controller.auth;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.recomende.infra.user.Role;
import br.recomende.infra.user.Roles;
import br.recomende.infra.user.User;
import br.recomende.infra.util.SHA1;
import br.recomende.model.curriculum.CurriculumVitae;
import br.recomende.model.curriculum.parser.CurriculumParser;
import br.recomende.model.profile.TagSet;
import br.recomende.model.recommender.TagSetMiner;
import br.recomende.model.repository.UserRepository;

@Controller
public class AuthController {
	
	private TagSetMiner tagSuggestion;
	private UserRepository userRepository;
	
	@Autowired
	public AuthController(TagSetMiner tagSuggestion, UserRepository userRepository) {
		this.tagSuggestion = tagSuggestion;
		this.userRepository = userRepository;
	}
	
	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public ModelAndView signin(@RequestParam(required=false) Boolean error) {
		ModelAndView modelAndView = new ModelAndView("auth/signin");
		modelAndView.addObject("error", error);
		return modelAndView;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String formRegister(Model model) {
		model.addAttribute(new User());
		return "auth/register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@ModelAttribute User user, @RequestParam("curriculum") MultipartFile curriculum) {
		try {
			CurriculumParser curriculumParser = new CurriculumParser();
			CurriculumVitae curriculumVitae = curriculumParser.parse(curriculum.getInputStream());
			TagSet profile = this.tagSuggestion.mine(curriculumVitae);
			user.setProfile(profile);
		} catch(Exception e) {
			//FIXME Corrigir tratamento de exception
			throw new RuntimeException(e);
		}
		user.setRoles(new HashSet<Role>());
		user.getRoles().add(new Role(Roles.USER));
		String hashedPassword = SHA1.crypt(user.getPassword());
		user.setPassword(hashedPassword);
		this.userRepository.put(user);
		return "redirect:/home";
	}
	
}
