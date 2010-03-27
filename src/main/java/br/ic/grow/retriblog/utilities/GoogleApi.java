package br.ic.grow.retriblog.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;


public class GoogleApi {
	
	String languageDetect (String text) throws JSONException, IOException {
		
		text = this.cleanSpace(text);
		URL url = new URL("http://ajax.googleapis.com/ajax/services/language/detect?v=1.0&q="+text);
		URLConnection connection = url.openConnection();

		String line;
		StringBuilder builder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while((line = reader.readLine()) != null) {
		 builder.append(line);
		}

		JSONObject json = new JSONObject(builder.toString());
		String jsonString = json.toString();
		int indexOfLanguage = jsonString.indexOf("language") + 11;
		String language = jsonString.substring(indexOfLanguage, indexOfLanguage+2);
		return language;
	}
	
	String cleanSpace(String text){
		
		String noSpaceString;
		noSpaceString = text.replaceAll(" ", "%20");
		
		return noSpaceString;
		
	}
	
	
	public static void main(String[] args) throws JSONException, IOException {
		GoogleApi ga = new GoogleApi();
		String teste =  ga.languageDetect("o flamengo � bom");
		System.out.println(teste);
	}

}
