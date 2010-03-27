package br.ic.grow.retriblog.technoratiApplication;

import br.ic.grow.retiblog.tagParser.TagParser;
import br.ic.grow.retiblog.tagParser.TechnoratiTagParser;
import br.ic.grow.retriblog.application.Application;
import br.ic.grow.retriblog.application.TechnoratiApplication;
import br.ic.grow.retriblog.blogCrawler.BlogCrawler;

public class Main {

	public static void main(String[] args) {
		Application technoratiApplication = new TechnoratiApplication();
		TagParser technoratiTagParser = new TechnoratiTagParser();
		BlogCrawler blogCrawler = new BlogCrawler(technoratiApplication);
		
		blogCrawler.crawlerTag(technoratiTagParser);
	}
	
}
