package br.recomende.model.searching.engine;

import org.springframework.beans.factory.annotation.Autowired;

import br.recomende.model.document.DocumentList;
import br.recomende.model.profile.TagSet;
import br.recomende.model.recommender.DocumentSearcher;
import br.recomende.model.recommender.TagSetMiner;
import br.recomende.model.recommender.api.MineException;
import br.recomende.model.recommender.api.SearchException;
import br.recomende.model.recommender.api.annotation.BeginMethod;
import br.recomende.model.recommender.api.annotation.Searcher;

@Searcher
public class TextBasedDocumentSearcher {

	private DocumentSearcher documentSearcher;
	private TagSetMiner tagSetMiner;
	
	@Autowired
	public TextBasedDocumentSearcher(DocumentSearcher documentSearcher,
			TagSetMiner tagSetMiner) {
		this.documentSearcher = documentSearcher;
		this.tagSetMiner = tagSetMiner;
	}
	
	@BeginMethod
	public DocumentList search(String text, Class<?> documentClass) throws MineException, SearchException {
		TagSet tagSet = this.tagSetMiner.mine(text);
		return this.documentSearcher.search(tagSet, documentClass);
	}
	
}
