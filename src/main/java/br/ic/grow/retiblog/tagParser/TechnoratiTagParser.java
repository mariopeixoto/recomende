package br.ic.grow.retiblog.tagParser;

import java.util.ArrayList;

import br.ic.grow.retriblog.data.Item;
import br.ic.grow.retriblog.utilities.HttpUtils;
import br.ic.grow.retriblog.utilities.Utilities;
import br.ic.grow.retriblog.utilities.UtilitiesControler;


public class TechnoratiTagParser extends TagParser{
	

	public TechnoratiTagParser() {
		super();
	}

	public TechnoratiTagParser(String tag) {
		super(tag);
	}

	ArrayList<Item> tagSearch() {
		Utilities hu = new 	UtilitiesControler();
		ArrayList<Item> technoratiItemList = new ArrayList<Item>();
		String page = hu.getPage("http://technorati.com/tag/" + this.getTag());
		int firstIndexPermalink, lastIndexPermalink, firstIndexNamePost, lastIndexNamePost, firstIndexExcerpt, lastIndexPermaExcerpt = 0;
		String permalink, namePost, excerpt;
		int links = 0;
		
		while (links < 20){
			Item item = new Item();
			firstIndexPermalink = page.indexOf("<h3><a class=\"offsite\"", lastIndexPermaExcerpt) + 29;
			lastIndexPermalink = page.indexOf("\">", firstIndexPermalink);
			
			permalink = page.substring(firstIndexPermalink, lastIndexPermalink);
			
			firstIndexNamePost = lastIndexPermalink + 2;
			lastIndexNamePost = page.indexOf("</a>", firstIndexNamePost);
			
			namePost = page.substring(firstIndexNamePost, lastIndexNamePost);
			
			firstIndexExcerpt = page.indexOf("<br />", lastIndexNamePost + 16) + 6;
			lastIndexPermaExcerpt = page.indexOf("<div>", firstIndexExcerpt);
			
			excerpt = page.substring(firstIndexExcerpt, lastIndexPermaExcerpt);
			
			item.setTitle(namePost);
			item.setPermalink(permalink);
			item.setExcerpt(excerpt);
			technoratiItemList.add(item);
			links++;
		}
	
		return technoratiItemList;
	}
}
