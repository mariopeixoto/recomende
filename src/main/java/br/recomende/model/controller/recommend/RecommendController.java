package br.recomende.model.controller.recommend;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.recomende.infra.user.User;
import br.recomende.model.document.Document;
import br.recomende.model.recommender.DocumentSearcher;
import br.recomende.model.recommender.api.SearchException;

@Controller
public class RecommendController {
	
	private DocumentSearcher searcher;
	
	@Autowired
	public RecommendController(DocumentSearcher searcher) {
		this.searcher = searcher;
	}

	@RequestMapping(value="/recommend")
	public ModelAndView recommend() throws SearchException {
		User user = this.getPrincipal();
		Collection<Document> documents = this.searcher.search(user.getProfile());
		ModelAndView modelAndView = new ModelAndView("recommend/recommend");
		modelAndView.addObject("documents", documents);
		return modelAndView;
	}
	
	private User getPrincipal() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
}
