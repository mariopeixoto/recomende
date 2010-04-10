package br.recomende.infra.persistence.dao;

import org.springframework.stereotype.Repository;

import br.recomende.model.recommending.BlogToRecommend;
import br.recomende.model.repository.BlogToRecommendRepository;

@Repository
public class BlogToRecommendDao extends RepositoryWrapper<BlogToRecommend, String> 
	implements BlogToRecommendRepository {

	public BlogToRecommendDao() {
		super(BlogToRecommend.class);
	}

}
