package br.recomende.model.searching.engine.api;

import java.util.List;

import br.recomende.model.document.Document;
import br.recomende.model.profile.Profile;

public interface SearchEngine {
	
	List<Document> search(Profile profile);

}
