package br.recomende.model.controller.recommend;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ic.grow.retiblog.tagParser.FragmentalTagParser;
import br.ic.grow.retriblog.application.LigeiroApplication;
import br.ic.grow.retriblog.blogCrawler.BlogCrawler;
import br.recomende.infra.user.User;
import br.recomende.model.document.BlogPostDocument;
import br.recomende.model.document.Document;
import br.recomende.model.document.DublinCoreDocument;
import br.recomende.model.recommender.DocumentSearcher;
import br.recomende.model.recommender.api.SearchException;

@Controller
public class RecommendController {
	
	private DocumentSearcher searcher;
	private BlogCrawler blogCrawler;
	
	@Autowired
	public RecommendController(DocumentSearcher searcher, BlogCrawler blogCrawler) {
		this.searcher = searcher;
		this.blogCrawler = blogCrawler;
	}

	@RequestMapping(value="/recommend")
	public ModelAndView recommend() throws SearchException {
		return this.recommendPaper(5);
	}
	
	@RequestMapping(value="/recommend/paper/{quantity}")
	public ModelAndView recommendPaper(@PathVariable Integer quantity) throws SearchException {
		User user = this.getPrincipal();
		Collection<Document> documents = this.searcher.search(user, quantity, user.getProfile(), DublinCoreDocument.class);
		ModelAndView modelAndView = new ModelAndView("recommend/recommendPaper");
		modelAndView.addObject("documents", documents);
		return modelAndView;
	}
	
	@RequestMapping(value="/recommend/blog/{quantity}")
	public ModelAndView recommendBlog(@PathVariable Integer quantity) throws SearchException {
		User user = this.getPrincipal();
		Collection<Document> documents = this.searcher.search(user, quantity, user.getProfile(), BlogPostDocument.class);
		ModelAndView modelAndView = new ModelAndView("recommend/recommendBlog");
		modelAndView.addObject("documents", documents);
		return modelAndView;
	}
	
	@RequestMapping(value="/recommend/blog/discussion/{quantity}")
	public ModelAndView recommendBlogDiscussion(@RequestParam String text, @PathVariable Integer quantity) throws SearchException {
		User user = this.getPrincipal();
		Collection<Document> documents = this.searcher.search(user, quantity, text, BlogPostDocument.class);
		ModelAndView modelAndView = new ModelAndView("recommend/recommendBlog");
		modelAndView.addObject("documents", documents);
		return modelAndView;
	}
	
	@RequestMapping(value="/recommend/paper/discussion/{quantity}")
	public ModelAndView recommendPaperDiscussion(@RequestParam String text, @PathVariable Integer quantity) throws SearchException {
		User user = this.getPrincipal();
		Collection<Document> documents = this.searcher.search(user, quantity, text, DublinCoreDocument.class);
		ModelAndView modelAndView = new ModelAndView("recommend/recommendPaper");
		modelAndView.addObject("documents", documents);
		return modelAndView;
	}
	
	@RequestMapping(value="/crawl")
	@Scheduled(cron="0 0 2 * * *")
	public void startCrawler() {
		this.blogCrawler.crawlerTag(new FragmentalTagParser());
	}
	
	private User getPrincipal() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
}
