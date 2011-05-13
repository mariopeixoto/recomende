package br.recomende.model.recommender.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.recomende.infra.user.User;
import br.recomende.infra.util.SpringScope;
import br.recomende.model.entity.Recommendation;
import br.recomende.model.recommender.api.IRecommender;
import br.recomende.model.recommender.api.Recommendable;
import br.recomende.model.recommender.api.RecommendableList;
import br.recomende.model.recommender.api.SearchException;
import br.recomende.model.recommender.api.annotation.Recommender;
import br.recomende.model.recommender.factory.RecommendableListFactory;
import br.recomende.model.recommender.util.RecommendUtil;
import br.recomende.model.repository.UserRepository;

@Service
@Scope(SpringScope.APPLICATION)
public class RecommenderImpl implements IRecommender {
	
	private static final Logger log = LoggerFactory.getLogger(IRecommender.class);
	
	private RecommendUtil recommendUtil;
	
	private UserRepository userRepository;
	
	@Autowired
	public RecommenderImpl(RecommendUtil recommendUtil,
			UserRepository userRepository) {
		this.recommendUtil = recommendUtil;
		this.userRepository = userRepository;
	}
	
	public RecommendableList recommend(User user, Integer quantity, Object... params) throws SearchException {
		try {
			
			RecommendableList objects = this.recommend(this.merge(user, params));
			
			//Removing already recommended
//			List<Recommendation> recommendations = user.getRecommendations();
//			for (Recommendation recommendation : recommendations) {
//				int docIndex = objects.contains(recommendation.getDocumentId(), recommendation.getDocumentClass());
//				if (docIndex != -1) {
//					objects.remove(docIndex);
//				}
//			}
			
			//Calculating score based on preferences
//			Map<PreferenceType, Integer> preferences = user.getPreferencesType();
//			for (Recommendable recommendable : objects) {
//				Integer value = preferences.get(recommendable.getObjectType());
//				recommendable.multiplyScore(value.doubleValue());
//			}
//			Collections.sort(objects, new RecommendableComparator());
			
			//Croping for quantity requested
			if (objects.size() > quantity) {
				objects = RecommendableListFactory.copyList(objects.subList(0, quantity));
			}
			
			//Generating recommendations
//			for (Recommendable object : objects) {
//				Recommendation recommendation = new Recommendation();
//				recommendation.setUser(user);
//				recommendation.setDocumentId(object.getId());
//				recommendation.setDocumentClass(object.getClass());
//				user.getRecommendations().add(recommendation);
//			}
//			this.userRepository.put(user);
			return objects;
		} catch (Exception e) {
			log.error("Error recommending", e);
			throw new SearchException(e);
		}
	}
	

	@Override
	public RecommendableList recommend(Object... params) throws SearchException {
		try {
			return this.recommendUtil.invokeComponentMethod(Recommender.class, RecommendableList.class, params);
		} catch (Exception e) {
			throw new SearchException(e);
		}
	}
	
	private Object[] merge(User user, Object[] params) {
		Object[] methodParams = new Object[params.length+1];
		int i = 0; 
		methodParams[i++] = user;
		for (Object param : params) {
			methodParams[i++] = param;
		}
		return methodParams;
	}
}
