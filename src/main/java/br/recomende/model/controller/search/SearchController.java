package br.recomende.model.controller.search;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import br.recomende.infra.user.User;
import br.recomende.model.document.Document;
import br.recomende.model.recommender.DocumentSearcher;
import br.recomende.model.recommender.QueryGenerator;
import br.recomende.model.recommender.api.SearchException;
import br.recomende.model.repository.DocumentRepository;

@Controller
public class SearchController {

	private DocumentSearcher documentSearcher;
	private QueryGenerator queryGenerator;
	private DocumentRepository documentRepository;

	@Autowired
	public SearchController(DocumentSearcher documentSearcher, QueryGenerator queryGenerator,
			DocumentRepository documentRepository) {
		this.documentSearcher = documentSearcher;
		this.queryGenerator = queryGenerator;
		this.documentRepository = documentRepository;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam("q") String text, @RequestParam("t") Class<?> documentClazz) throws SearchException {
		Collection<Document> documents = this.documentSearcher.search(text, documentClazz);
		ModelAndView modelAndView = new ModelAndView("search/result");
		modelAndView.addObject("documents", documents);
		return modelAndView;
	}
	
	@RequestMapping(value = "/searcher", method = RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("search/searcher");
		modelAndView.addObject("types", this.queryGenerator.getIndexedClasses());
		return modelAndView;
	}
	
	@RequestMapping(value = "/recommend", method = RequestMethod.GET)
	public ModelAndView recommend() throws SearchException {
		User user = this.getPrincipal();
		Collection<Document> documents = this.documentSearcher.search(user, 5, user.getProfile(), Document.class);
		ModelAndView modelAndView = new ModelAndView("search/result");
		modelAndView.addObject("documents", documents);
		return modelAndView;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(value = "/reindex", method = RequestMethod.GET)
	public void reindex() {
		this.documentRepository.reindex();
	}
	
	private User getPrincipal() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
}
