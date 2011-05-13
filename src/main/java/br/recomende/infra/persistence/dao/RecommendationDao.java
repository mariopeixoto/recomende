package br.recomende.infra.persistence.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.entity.Recommendation;
import br.recomende.model.repository.RecommendationRepository;

@Component
@Scope(SpringScope.PROTOTYPE)
public class RecommendationDao extends RepositoryWrapper<Recommendation, Integer>
		implements RecommendationRepository{

}
