package br.ic.grow.retriblog.technoratiApplication;
//package grow.retriblog.technoratiApplication;
//import grow.retiblog.index.LuceneIndexing;
//import grow.retriblog.data.Item;
//import grow.retriblog.preprocessing.CleanHTML;
//import grow.retriblog.preprocessing.EnglishStemming;
//import grow.retriblog.preprocessing.Preprocessing;
//
//import grow.retriblog.preprocessing.WhiteSpaceAnalyser;
//import grow.retriblog.utilities.BlogPostExtraction;
//import grow.retriblog.utilities.GoogleApi;
//import grow.retriblog.utilities.HibernateUtility;
//import grow.retriblog.utilities.HttpUtils;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//
//import org.json.JSONException;
//
//
//public class TechnoratiTest2 {
//	public static void main(String[] args) throws IOException {
//		
//		
//		HttpUtils httpUtils = new HttpUtils();	
//		ArrayList<Item> itensList = new ArrayList<Item>();
//		BlogPostExtraction blogPostExtraction = new BlogPostExtraction();
//		GoogleApi google = new GoogleApi();
//		WhiteSpaceAnalyser whiteSpaceAnalyser = new WhiteSpaceAnalyser();
//		EnglishStemming englishStemming = new EnglishStemming();
//		TechnoratiTagParser technoratiTagParser = new TechnoratiTagParser();
//		HibernateUtility hibernateUtility = new HibernateUtility();
//		LuceneIndexing luceneIndexing = new LuceneIndexing();
//		Preprocessing preprocessing = new CleanHTML();
//		String textWithHtml, language = "", excerpt, excerptWithoutWhite = "", textWithoutHtml;
//		
//		itensList = technoratiTagParser.tagSearch("education");
//		
//		for (int i = 0; i< itensList.size(); i++){
////			texto = (new String (teste.get(i).getExcerpt().getBytes(), "UTF-8"));	
//			excerpt = itensList.get(i).getExcerpt();
//			try {
//				excerptWithoutWhite = whiteSpaceAnalyser.analyzeString(excerpt);
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			
//			try {
//				language = google.languageDetect(excerptWithoutWhite);
//			} catch (JSONException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			if (language.equals("en")){
//				
//				try{
//					textWithHtml = blogPostExtraction.getBlogText(itensList.get(i).getPermalink(), excerptWithoutWhite);
//					if(!textWithHtml.equals("erro")){
//						textWithoutHtml = preprocessing.analyzeString(textWithHtml);
//						System.out.println(textWithoutHtml);
//						itensList.get(i).setCompleteText(textWithoutHtml);
//						luceneIndexing.index(System.getProperty("user.dir") +
//								System.getProperty("file.separator") + "configuration", itensList.get(i).getPermalink(), 
//								"", textWithoutHtml, "");
//						itensList.get(i).setAnalyzedText(englishStemming.analyzeString(textWithoutHtml));
//						//colocar indexa��o
//						if(!hibernateUtility.checarPermalink(itensList.get(i).getPermalink())){
//							hibernateUtility.salvarItem(itensList.get(i));
//							
//						}else {
//							break;
//						}
//					}
//					
//				}catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//				
//			}
//			else{
//				System.out.println("it is not english");
//			}
//		}
//		
//
//	}
//	 
//	
//
//}
