package br.ic.grow.retriblog.utilities;


import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;

import br.ic.grow.retriblog.textExtraction.TextExtraction;

public class UtilitiesControler implements Utilities{
	
	BlogPostExtraction blogPostExtraction;
	FileHandling fileHandling;
	GoogleApi googleApi;
	HttpUtils httpUtils;
	XMLDom xMLDom;
	
	public UtilitiesControler() {
		
		blogPostExtraction = new BlogPostExtraction();
		fileHandling = new FileHandling();
		googleApi = new GoogleApi();
		httpUtils = new HttpUtils();
		xMLDom = new XMLDom();
	}
	
	public String getPost(String url, String first, String last, TextExtraction textExtraction){
		return blogPostExtraction.getPost(url, first, last, textExtraction);
	}
	public String getBlogText(String uri, String excerpt, TextExtraction textExtraction){
		return blogPostExtraction.getBlogText(uri, excerpt, textExtraction);
	}
	public boolean saveFileString(String fileName, String message, boolean add){
		return fileHandling.saveFileString(fileName, message, add);
	}
	public boolean saveFileStringUtf(String fileName, String message, boolean add){
		return fileHandling.saveFileStringUtf(fileName, message, add);
	}
	public boolean saveFileInputstream(String fileName, String oldDtd, String newDtd, InputStream message, boolean add){
		return fileHandling.saveFileInputstream(fileName, oldDtd, newDtd, message, add);
	}
	public boolean changeDtdFile(String oldDtd, String newDtd, String fileName){
		try {
			return fileHandling.changeDtdFile(oldDtd, newDtd, fileName);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean changeDtdFileInputstream(String oldDtd, String newDtd, String fileName){
		try {
			return fileHandling.changeDtdFileInputstream(oldDtd, newDtd, fileName);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	public String languageDetect (String text){
		try {
			return googleApi.languageDetect(text);
		} catch (JSONException e) {
			e.printStackTrace();
			return "erro";
		} catch (IOException e) {
			e.printStackTrace();
			return "erro";
		}
	}
	public String cleanSpace(String text){
		return googleApi.cleanSpace(text);
	}
	public String getPage(String url){
		return httpUtils.getPage(url);
	}
	public void savePage(String url, String oldDtd, String newDtd){
		httpUtils.savePage(url, oldDtd, newDtd);
	}
	public String convertStreamToString(InputStream is){
		try {
			return httpUtils.convertStreamToString(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public Object loadXML( String src, boolean validate){
		return xMLDom.loadXML(src, validate);
	}

}
