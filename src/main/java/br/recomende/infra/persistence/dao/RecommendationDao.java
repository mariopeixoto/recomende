package br.recomende.infra.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.recomende.infra.user.User;
import br.recomende.model.document.Document;
import br.recomende.model.recommender.Recommendation;
import br.recomende.model.repository.RecommendationRepository;

@Repository
public class RecommendationDao extends RepositoryWrapper<Recommendation, Integer> 
		implements RecommendationRepository {
	
	public RecommendationDao() {
		super(Recommendation.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Recommendation> getByUser(User user) {
		Session session = super.getSession();
		Criteria criteria = session.createCriteria(Recommendation.class)
								.add(Restrictions.eq("user", user));
		return (List<Recommendation>)criteria.list();
	}

	@Override
	@Transactional(readOnly = false)
	public void putByUser(User user, List<Document> documents) {
		for (Document document : documents) {
			Recommendation recommendation = new Recommendation();
			recommendation.setUser(user);
			recommendation.setDocumentId(document.getId());
			recommendation.setDocumentClass(document.getClass());
			super.put(recommendation);
		}
	}

}
