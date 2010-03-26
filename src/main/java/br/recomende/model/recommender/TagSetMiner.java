package br.recomende.model.recommender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.profile.TagSet;
import br.recomende.model.recommender.api.IMiner;
import br.recomende.model.recommender.api.MineException;
import br.recomende.model.recommender.api.annotation.Miner;

@Service
@Scope(SpringScope.APPLICATION)
public class TagSetMiner implements IMiner<TagSet> {

	private RecommendUtil recommendUtil;

	@Autowired
	public TagSetMiner(RecommendUtil recommendUtil) {
		this.recommendUtil = recommendUtil;
	}
	
	public TagSet mine(Object... params) throws MineException {
		try {
			return this.recommendUtil.invokeComponentMethod(Miner.class, TagSet.class, params);
		} catch (Exception e) {
			throw new MineException(e);
		}
	}
	
}
