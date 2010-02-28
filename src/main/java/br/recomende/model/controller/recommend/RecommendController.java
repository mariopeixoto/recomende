package br.recomende.model.controller.recommend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.recomende.infra.user.User;
import br.recomende.model.document.Document;
import br.recomende.model.searching.engine.api.SearchEngine;

@Controller
public class RecommendController {
	
	private SearchEngine searchEngine;
	
	@Autowired
	public RecommendController(SearchEngine searchEngine) {
		this.searchEngine = searchEngine;
	}

	@RequestMapping(value="/recommend")
	public ModelAndView recommend() {
		User user = this.getPrincipal();
		List<Document> documents = this.searchEngine.search(user.getProfile());
		ModelAndView modelAndView = new ModelAndView("recommend/recommend");
		modelAndView.addObject("documents", documents);
		return modelAndView;
	}
	
	private User getPrincipal() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
}
