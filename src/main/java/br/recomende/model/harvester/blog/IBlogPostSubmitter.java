package br.recomende.model.harvester.blog;

public interface IBlogPostSubmitter {
	
	void submit(String title, String date, String blogPostText, String url);

}
