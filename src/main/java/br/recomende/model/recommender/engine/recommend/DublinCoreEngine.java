package br.recomende.model.recommender.engine.recommend;

import java.io.IOException;

import org.apache.lucene.search.Query;
import org.springframework.beans.factory.annotation.Autowired;

import br.recomende.infra.user.User;
import br.recomende.model.entity.DublinCoreDocument;
import br.recomende.model.recommender.api.MineException;
import br.recomende.model.recommender.api.RecommendableList;
import br.recomende.model.recommender.api.Tag;
import br.recomende.model.recommender.api.TagMap;
import br.recomende.model.recommender.api.annotation.BeginMethod;
import br.recomende.model.recommender.api.annotation.Recommender;
import br.recomende.model.recommender.util.QueryGenerator;
import br.recomende.model.repository.RecommendableRepository;

@Recommender
public class DublinCoreEngine {
	
	@Autowired
	private QueryGenerator queryGenerator;
	
	@Autowired
	private RecommendableRepository recommendableRepository;
	
	@BeginMethod
	public RecommendableList recommend(User user) throws IOException, MineException {
		TagMap<Tag> userProfile = user.getUserProfile().clone();

		Query query = this.queryGenerator.generate(userProfile, DublinCoreDocument.class);
		return this.recommendableRepository.search(query, DublinCoreDocument.class);
	}

}
