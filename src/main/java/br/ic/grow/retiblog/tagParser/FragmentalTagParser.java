package br.ic.grow.retiblog.tagParser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.ic.grow.retriblog.data.Item;
import br.ic.grow.retriblog.utilities.Utilities;
import br.ic.grow.retriblog.utilities.UtilitiesControler;

public class FragmentalTagParser extends TagParser {
	
	public FragmentalTagParser() {
		super();
	}

	ArrayList<Item> tagSearch() {
		Utilities hu = new 	UtilitiesControler();
		String page = hu.getPage("http://fragmental.tw/category/" + this.tag);
		
		Pattern pattern = Pattern.compile("<h3 class=\"entry-title\">(.*?)</h3>", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(page);
		
		ArrayList<Item> items = new ArrayList<Item>();
		
		while(matcher.find()) {
			Pattern href = Pattern.compile("href=\"(.*?)\"");
			Matcher hrefMatcher = href.matcher(matcher.group());
			hrefMatcher.find();
			String permalink = hrefMatcher.group().replace("href=", "").replace("\"", "");
			String title = matcher.group().replaceAll("<.*?>", "");
			Item item = new Item();
			item.setPermalink(permalink);
			item.setTitle(title);
			items.add(item);
		}
		
		for (Item item : items) {
			String blogPost = hu.getPage(item.getPermalink());
			Pattern post = Pattern.compile("<div class=\"entry-content\">(.*?)</div>", Pattern.DOTALL);
			Matcher postMatcher = post.matcher(blogPost);
			postMatcher.find();
			Pattern date = Pattern.compile("<abbr class=\"published\" title=\"(.*?)\">(.*?)</abbr>");
			Matcher dateMatcher = date.matcher(blogPost);
			dateMatcher.find();
			String created = dateMatcher.group().replaceAll("<.*?>", "");
			String text = postMatcher.group().replaceAll("<.*?>", "");
			item.setExcerpt(text);
			item.setCreated(created);
		}
		
		return items;
	}
	
}
