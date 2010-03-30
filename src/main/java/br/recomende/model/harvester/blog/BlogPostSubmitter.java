package br.recomende.model.harvester.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.document.BlogPostDocument;
import br.recomende.model.repository.DocumentRepository;

@Service
@Scope(SpringScope.PROTOTYPE)
public class BlogPostSubmitter implements IBlogPostSubmitter {
	
	private DocumentRepository documentRepository;
	
	@Autowired
	public BlogPostSubmitter(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}
	
	public void submit(String title, String date, String blogPostText, String url) {
		BlogPostDocument blogPostDocument = new BlogPostDocument();
		blogPostDocument.setText(blogPostText);
		blogPostDocument.setUrl(url);
		blogPostDocument.setTitle(title);
		blogPostDocument.setDate(date);
		this.documentRepository.put(blogPostDocument);
	}

}
