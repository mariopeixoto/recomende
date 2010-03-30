package br.recomende.model.repository;

import java.util.List;

import br.recomende.infra.user.User;
import br.recomende.model.document.Document;
import br.recomende.model.recommender.Recommendation;

public interface RecommendationRepository extends GenericRepository<Recommendation, Integer> {
	
	List<Recommendation> getByUser(User user);
	
	void putByUser(User user, List<Document> documents);

}
