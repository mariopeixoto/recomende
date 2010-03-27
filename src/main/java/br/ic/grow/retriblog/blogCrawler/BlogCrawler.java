package br.ic.grow.retriblog.blogCrawler;



import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import br.ic.grow.retiblog.index.Indexing;
import br.ic.grow.retiblog.index.IndexingControler;
import br.ic.grow.retiblog.tagParser.TagParser;
import br.ic.grow.retiblog.tagParser.TagParserControler;
import br.ic.grow.retriblog.application.Application;
import br.ic.grow.retriblog.application.ApplicationControler;
import br.ic.grow.retriblog.data.Item;
import br.ic.grow.retriblog.persistence.Persistence;
import br.ic.grow.retriblog.persistence.PersistenceControler;
import br.ic.grow.retriblog.preprocessing.CleanHTML;
import br.ic.grow.retriblog.preprocessing.Preprocessing;
import br.ic.grow.retriblog.preprocessing.PreprocessingControler;
import br.ic.grow.retriblog.preprocessing.WhiteSpaceAnalyser;
import br.ic.grow.retriblog.textExtraction.TextExtraction;
import br.ic.grow.retriblog.textExtraction.TextExtractionControler;
import br.ic.grow.retriblog.utilities.BlogPostExtraction;
import br.ic.grow.retriblog.utilities.GoogleApi;
import br.ic.grow.retriblog.utilities.Utilities;
import br.ic.grow.retriblog.utilities.UtilitiesControler;

public class BlogCrawler {
	
	private ArrayList<Item> itensList;
	private Utilities utilities;
	private Preprocessing whiteSpaceAnalyser;
	private Preprocessing cleanHtml;
	private String textWithHtml, blogLanguage = "", excerpt, excerptWithoutWhite = "", textWithoutHtml;
	private int numberOfTags;
	private ArrayList<String> tags;
	private Indexing index;
	private Persistence persistence;
	private TextExtraction textExtraction;
	private Application main;
	private PreprocessingControler preprocessingControler;
	private IndexingControler indexingControler;
	private PersistenceControler persistenceControler;
	private ApplicationControler applicationControler;
	private TagParserControler tagParserControler;
	
	
	public BlogCrawler(Application main) {
		
		itensList = new ArrayList<Item>();
		utilities = new UtilitiesControler();
		whiteSpaceAnalyser = new WhiteSpaceAnalyser();
		cleanHtml = new CleanHTML();
		
		this.main = main;//ioc
		numberOfTags = main.getNumberOfTags();
		tags = main.getTags();
		index = main.getIndexing();
		persistence = main.getPersistence();
		textExtraction = main.getTextExtraction();
		
		preprocessingControler = new PreprocessingControler();
		indexingControler = new IndexingControler();
		persistenceControler = new PersistenceControler();
		applicationControler = new ApplicationControler();
		tagParserControler = new TagParserControler();

	}

	public void crawlerTag (TagParser tagParser) {
		
		for (int aux=0; aux < numberOfTags; aux++){
			tagParser.setTag(tags.get(aux));
			itensList = tagParserControler.tagSearchRun(tagParser);
			
			for (int i = 0; i< itensList.size(); i++){
//				texto = (new String (teste.get(i).getExcerpt().getBytes(), "UTF-8"));	
				excerpt = itensList.get(i).getExcerpt();
				try {
					whiteSpaceAnalyser.setText(excerpt);
					excerptWithoutWhite = preprocessingControler.analyzeStringRun(whiteSpaceAnalyser);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				try {
					blogLanguage = utilities.languageDetect(excerptWithoutWhite);
				} catch (Exception e) {
					e.printStackTrace();
				}
				main.setBlogLanguage(blogLanguage);
				if (applicationControler.checksLanguageRun(main)){//ioc
					
					try{
						textWithHtml = utilities.getBlogText(itensList.get(i).getPermalink(), excerptWithoutWhite, textExtraction);
						if(!textWithHtml.equals("erro")){
//							itensList.get(i).setHtmlText(textWithHtml);
							
							cleanHtml.setText(textWithHtml);
							textWithoutHtml = preprocessingControler.analyzeStringRun(cleanHtml);
							System.out.println(textWithoutHtml);
							itensList.get(i).setCompleteText(textWithoutHtml);
							index.setIndexDir(System.getProperty("user.dir") +
									System.getProperty("file.separator") + "configuration");
							index.setKeyword(itensList.get(i).getPermalink());
							index.setText(textWithoutHtml);
							indexingControler.indexRun(index);
							main.setText(textWithoutHtml);
							itensList.get(i).setAnalyzedText(applicationControler.preprocesTextRun(main));//ioc
							//colocar indexa��o
							persistence.setPermalink(itensList.get(i).getPermalink());
							
							if(!persistenceControler.checarPermalinkRun(persistence)){
								itensList.get(i).setCategory(tags.get(aux));
								persistence.setItem(itensList.get(i));
								persistenceControler.salvarItemRun(persistence);
								
							}else {
								break;
							}
						}
						
					}catch (Exception e) {
						e.printStackTrace();
					}
					
					
				}
				else{
					System.out.println("it is not english");
				}
			}
		}

		

	}
	 
	

}
