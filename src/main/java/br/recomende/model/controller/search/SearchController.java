package br.recomende.model.controller.search;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import br.recomende.infra.user.User;
import br.recomende.model.recommender.api.IRecommender;
import br.recomende.model.recommender.api.Recommendable;
import br.recomende.model.recommender.api.SearchException;
import br.recomende.model.repository.RecommendableRepository;
import br.recomende.model.repository.UserRepository;

@Controller
public class SearchController {

	private IRecommender recommender;
	private RecommendableRepository recommendableRepository;
	private UserRepository userRepository;
	
	@Autowired
	public SearchController(IRecommender recommender,
			RecommendableRepository recommendableRepository, UserRepository userRepository) {
		this.recommender = recommender;
		this.recommendableRepository = recommendableRepository;
		this.userRepository = userRepository;
	}
	
	@RequestMapping(value = "/recommend", method = RequestMethod.GET)
	public ModelAndView recommend() throws SearchException {
		User user = this.getPrincipal();
		Collection<Recommendable> documents = this.recommender.recommend(user, 5);
		ModelAndView modelAndView = new ModelAndView("search/result");
		modelAndView.addObject("documents", documents);
		return modelAndView;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/reindex", method = RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public void reindex() throws FileNotFoundException, IOException, SAXException {
		this.recommendableRepository.reindex();
	}
	
	private User getPrincipal() {
		Session session = (Session) this.userRepository.getDelegate();
		return (User) session.merge(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	}
	
}
