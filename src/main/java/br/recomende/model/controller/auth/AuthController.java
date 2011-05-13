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
import br.recomende.model.entity.Profile;
import br.recomende.model.recommender.api.IMiner;
import br.recomende.model.recommender.api.Tag;
import br.recomende.model.recommender.api.TagMap;
import br.recomende.model.repository.UserRepository;

@Controller
public class AuthController {
	
	private IMiner miner;
	private UserRepository userRepository;
	
	@Autowired
	public AuthController(IMiner miner, UserRepository userRepository) {
		this.miner = miner;
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
			if (curriculum != null) {
				CurriculumParser curriculumParser = new CurriculumParser();
				CurriculumVitae curriculumVitae = curriculumParser.parse(curriculum.getInputStream());
				Profile profile = (Profile) this.miner.mine(Profile.class, curriculumVitae);
				user.setUserProfile(profile);
			}
		} catch(Throwable e) {
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
