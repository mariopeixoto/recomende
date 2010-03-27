package br.ic.grow.retriblog.utilities;

import java.util.ArrayList;

import br.ic.grow.retriblog.textExtraction.TextExtraction;
import br.ic.grow.retriblog.textExtraction.TextExtractionControler;


public class BlogPostExtraction {
	
	String getPost(String url, String first, String last, TextExtraction textExtraction){
		  Utilities hu = new UtilitiesControler();
	      String responseBody = hu.getPage(url);
	      
	      TextExtractionControler textExtractionControler = new TextExtractionControler();
	      
	      ArrayList<Object> parametros = new ArrayList<Object>();
	      
	      parametros.add(responseBody);
	      parametros.add(first);
	      parametros.add(last);
	      
	      textExtraction.setParametros(parametros);
	      
	      String responsePost = textExtractionControler.getTextRun(textExtraction);
	      
	      return responsePost;

	}
	
	String getBlogText(String uri, String excerpt, TextExtraction textExtraction) {
		
	
//		String completeHtml;
		String blogHtml = "";
		String textBlogHtml = "";
		String htmlAux;
		int buttonLineIndex;
		int topLineIndex;
		int iterator=0; 
		
		blogHtml = this.getPost(uri, excerpt, "</div>", textExtraction);

		while (iterator< blogHtml.length() && !blogHtml.equals("erro") && blogHtml.contains("</p>")){// se nao estiver entre o <p> n eh relevante
			
			if(iterator==0){
				buttonLineIndex = -3;
			}
			else{
				buttonLineIndex = blogHtml.indexOf("<p>", iterator);
			}
			topLineIndex = blogHtml.indexOf("</p>", buttonLineIndex);
			
			if(buttonLineIndex == -1){
				break;
			}
			htmlAux = blogHtml.substring(buttonLineIndex + 3, topLineIndex);

			textBlogHtml += htmlAux + "\n";
			
			iterator = topLineIndex;
		}
		if (iterator==0){
			if(blogHtml.contains("<br />")){
				textBlogHtml = blogHtml;
			}
			else{
				return "erro";	
			}
		}
		return textBlogHtml;

	}

}
