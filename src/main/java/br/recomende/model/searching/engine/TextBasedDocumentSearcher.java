package br.recomende.model.searching.engine;

import org.springframework.beans.factory.annotation.Autowired;

import br.recomende.model.document.DocumentList;
import br.recomende.model.profile.TagSet;
import br.recomende.model.recommender.TagSetMiner;
import br.recomende.model.recommender.api.MineException;
import br.recomende.model.recommender.api.annotation.BeginMethod;
import br.recomende.model.recommender.api.annotation.Searcher;

@Searcher
public class TextBasedDocumentSearcher {

	private ProfileBasedDocumentSearcher profileBasedDocumentSearcher;
	private TagSetMiner tagSetMiner;
	
	@Autowired
	public TextBasedDocumentSearcher(ProfileBasedDocumentSearcher profileBasedDocumentSearcher,
			TagSetMiner tagSetMiner) {
		this.profileBasedDocumentSearcher = profileBasedDocumentSearcher;
		this.tagSetMiner = tagSetMiner;
	}
	
	@BeginMethod
	public DocumentList search(String text, Class<?> documentClass) throws MineException {
		TagSet tagSet = this.tagSetMiner.mine(text);
		return this.profileBasedDocumentSearcher.search(tagSet, documentClass);
	}
	
}
