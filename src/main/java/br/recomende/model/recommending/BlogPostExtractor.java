package br.recomende.model.recommending;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.commons.digester.Digester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import br.recomende.infra.http.GetFacade;
import br.recomende.infra.util.SpringScope;
import br.recomende.model.document.BlogPostDocument;

@Component
@Scope(SpringScope.PROTOTYPE)
public class BlogPostExtractor {
	
	private Digester digester;
	private GetFacade getFacade;

	@Autowired
	public BlogPostExtractor(GetFacade getFacade) {
		this.digester = new Digester();
		this.getFacade = getFacade;
	}

	@SuppressWarnings("unchecked")
	public List<BlogPostDocument> extractPosts(String url) throws IOException, SAXException {
		url += "/feed/";
		List<BlogPostDocument> returnList = (List<BlogPostDocument>) this.digester.parse(this.getFacade.get(url, null));
		if (returnList == null) {
			return new ArrayList<BlogPostDocument>();
		}
		for (BlogPostDocument post : returnList) {
			String text = post.getText();
			Pattern pattern = Pattern.compile("<p>(.*?)</p>", Pattern.DOTALL);
			Matcher matcher = pattern.matcher(text);
			StringBuilder builder = new StringBuilder();
			while (matcher.find()) {
				builder.append(matcher.group().replaceAll("<.*?>", ""));
			}
			post.setText(builder.toString());
		}
		return returnList;
	}

	@PostConstruct
	public void setUp() {
		this.digester.setNamespaceAware(true);
		this.digester.addObjectCreate("rss/channel", ArrayList.class);
		this.digester.addObjectCreate("rss/channel/item",BlogPostDocument.class);
		this.digester.addBeanPropertySetter("rss/channel/item/title");
		this.digester.addBeanPropertySetter("rss/channel/item/link", "url");
		this.digester.addBeanPropertySetter("rss/channel/item/pubDate", "date");
		this.digester.addBeanPropertySetter("rss/channel/item/encoded", "text");
		this.digester.addSetNext("rss/channel/item", "add");
	}
}
