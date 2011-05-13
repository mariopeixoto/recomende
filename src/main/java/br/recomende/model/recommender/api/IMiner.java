package br.recomende.model.recommender.api;



public interface IMiner {
	
	@SuppressWarnings("rawtypes")
	TagMap mine(Class<? extends TagMap> tagSetImplClass, Object... params) throws MineException;

}
