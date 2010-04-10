package br.recomende.model.recommending;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.digester.Digester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import br.recomende.infra.http.GetFacade;
import br.recomende.infra.util.SpringScope;

@Component
@Scope(SpringScope.PROTOTYPE)
public class BlogCommentExtractor {
	
	private Digester digester;
	private GetFacade getFacade;
	
	@Autowired
	public BlogCommentExtractor(GetFacade getFacade) {
		this.digester = new Digester();
		this.getFacade = getFacade;
	}
	
	@SuppressWarnings("unchecked")
	public List<BlogComment> extractComments(String url) throws IOException, SAXException {
		url += "/comments/feed/"; 
		List<BlogComment> returnList = (List<BlogComment>) this.digester.parse(this.getFacade.get(url, null));
		if (returnList == null) {
			return new ArrayList<BlogComment>();
		}
		return returnList;
	}
	
	@PostConstruct
	public void setUp() {
		this.digester.setNamespaceAware(true);
		this.digester.addObjectCreate("rss/channel", ArrayList.class);
		this.digester.addObjectCreate("rss/channel/item",BlogComment.class);
		this.digester.addBeanPropertySetter("rss/channel/item/creator");
		this.digester.addBeanPropertySetter("rss/channel/item/link", "blogPostPermalink");
		this.digester.addSetNext("rss/channel/item", "add");
	}
	
}
