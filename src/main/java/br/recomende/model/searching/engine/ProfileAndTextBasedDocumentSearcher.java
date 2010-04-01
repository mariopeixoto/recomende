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
public class ProfileAndTextBasedDocumentSearcher {

	private TagSetMiner tagSetMiner;
	private DocumentSearcher documentSearcher;
	
	@Autowired
	public ProfileAndTextBasedDocumentSearcher(DocumentSearcher documentSearcher,
			TagSetMiner tagSuggestion) {
		this.tagSetMiner = tagSuggestion;
		this.documentSearcher = documentSearcher;
	}
	
	@BeginMethod
	public DocumentList search(TagSet tagSet, String text, Class<?> documentClass) throws SearchException, MineException {
		TagSet tags = tagSet.merge(this.tagSetMiner.mine(text));
		return this.documentSearcher.search(tags, documentClass);
	}
	
}
