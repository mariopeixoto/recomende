package br.ic.grow.retriblog.textExtraction;


import java.util.ArrayList;

public class SummaryStrategy extends TextExtraction {

	
	public SummaryStrategy() {}
	
	public SummaryStrategy(ArrayList<Object> parametros) {
		super(parametros);
	}


	String getText() {
		
		String text, summary, last;
		
		text = (String) parametros.get(0);
		summary = (String) parametros.get(1);
		last = (String) parametros.get(2);
		
		int buttonBlogIndex;
		int topBlogIndex;
		String blogHtml = "";
		int indexAux = 0;
		int textDown = summary.length();
		int textUp = 0;
		int textSize = summary.length();
		String firstUp = summary;//first para ser usado no up 
//        String teste = this.cleanHTML(StringEscapeUtils.unescapeHtml(input));
        do{
//            buttonBlogIndex = StringEscapeUtils.unescapeHtml(input).indexOf(first, indexAux);
        	blogHtml = "erro";
            buttonBlogIndex = text.indexOf(summary, indexAux);
    		
    		if(buttonBlogIndex != -1){//caso n seja blog
    			topBlogIndex = text.indexOf(last, buttonBlogIndex);
    			while (text.charAt(buttonBlogIndex)!= '<'){
    				buttonBlogIndex--;
    			}
    			blogHtml = text.substring(buttonBlogIndex, topBlogIndex);
    			indexAux = topBlogIndex;
    		}
    		else{
    			textDown = textDown - 10;
    			if (textDown>0){
    				summary = summary.substring(0, textDown);
    			}
    			else {
    				break;
    			}
    		}
        }while(!blogHtml.contains("</p>")&& textDown > 0 || blogHtml.contains("<meta"));

        if (blogHtml.equals("erro")){
        	indexAux = 0;
        	String aux = firstUp.substring(textUp, textSize);
        	do{
        		
            buttonBlogIndex = text.indexOf(aux, indexAux);
      	
      		if(buttonBlogIndex != -1){//caso n seja blog
      			topBlogIndex = text.indexOf(last, buttonBlogIndex);
      			while (text.charAt(buttonBlogIndex)!= '<'){
      				buttonBlogIndex--;
      			}
      			blogHtml = text.substring(buttonBlogIndex, topBlogIndex);
      			indexAux = topBlogIndex;
      		}
      		else{
      			textUp = textUp + 10;
      			if(textUp < textSize){
      				aux = firstUp.substring(textUp, textSize);
      			}
      			else {
      				break;
      			}
      		}
          }while(!blogHtml.contains("</p>")&& textUp < textSize || blogHtml.contains("<meta"));
        }
        return blogHtml;//tratar erro
        
    }

	
	public static void main(String[] args) {
//		HttpUtils h = new HttpUtils();
//		String t = h.getPage("http://ajax.googleapis.com/ajax/services/language/detect?v=1.0&q=No%20flamengo%20alegria");
//		System.out.println(t);
//		BlogPostExtraction t = new BlogPostExtraction();
//		String test;
//		test = h.getPage("http://loftyseattle.com/a-couple-of-various-movie-reviews-to-check-out/");
//		test = t.getBlogText("http://ismailimail.wordpress.com/2009/11/13/aga-khan-health-service-hold-training-course-in-mombasa/", "Mr. Ramadhan Bungale, Acting Regional Coordinator of Constituent Development Funds for Coast province distributed the", new SummaryStrategy());
//		System.out.println(h.cleanHTML(test));
//		System.out.println(test);
//		h.savePage("http://api.technorati.com/tag?key=27a4ad9b8d5df406a0f93d7a1b1d6652&tag=education", "http://api.technorati.com/dtd/tapi-002.xml", "D:\\LiGeiRo\\workspace\\tapi-002.xml");
		
	}
	
}
