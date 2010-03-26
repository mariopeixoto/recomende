package br.recomende.model.searching.engine;

import org.springframework.beans.factory.annotation.Autowired;

import br.recomende.model.document.DocumentList;
import br.recomende.model.profile.TagSet;
import br.recomende.model.recommender.TagSetMiner;
import br.recomende.model.recommender.api.MineException;
import br.recomende.model.recommender.api.annotation.BeginMethod;
import br.recomende.model.recommender.api.annotation.Searcher;

@Searcher
public class ProfileAndTextBasedDocumentSearcher {

	private ProfileBasedDocumentSearcher profileBasedDocumentSearcher;
	private TagSetMiner tagSetMiner;
	
	@Autowired
	public ProfileAndTextBasedDocumentSearcher(ProfileBasedDocumentSearcher profileBasedDocumentSearcher,
			TagSetMiner tagSuggestion) {
		this.profileBasedDocumentSearcher = profileBasedDocumentSearcher;
		this.tagSetMiner = tagSuggestion;
	}
	
	@BeginMethod
	public DocumentList search(TagSet tagSet, String text) throws MineException {
		TagSet tags = tagSet.merge(this.tagSetMiner.mine(text));
		return this.profileBasedDocumentSearcher.search(tags);
	}
	
}
