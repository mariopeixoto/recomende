package br.recomende.model.recommender.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.recommender.api.IMiner;
import br.recomende.model.recommender.api.MineException;
import br.recomende.model.recommender.api.TagMap;
import br.recomende.model.recommender.api.annotation.Miner;
import br.recomende.model.recommender.util.RecommendUtil;

@Service
@Scope(SpringScope.APPLICATION)
public class MinerImpl implements IMiner {
	
	private static final Logger log = LoggerFactory.getLogger(IMiner.class);

	private RecommendUtil recommendUtil;

	@Autowired
	public MinerImpl(RecommendUtil recommendUtil) {
		this.recommendUtil = recommendUtil;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TagMap mine(Class<? extends TagMap> tagSetImplClass, Object... params) throws MineException {
		try {
			return this.recommendUtil.invokeComponentMethod(Miner.class, tagSetImplClass, params);
		} catch (Exception e) {
			log.error("Error mining", e);
			throw new MineException(e);
		}
	}
	
}
