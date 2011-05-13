package br.recomende.model.entity;

import java.util.List;

import br.recomende.model.harvester.HarvesterDefinition;
import br.recomende.model.harvester.HarvesterFactory;
import br.recomende.model.recommender.api.Recommendable;

public interface DocumentHarvester {
	
	/**
	 * Method to harvest documents
	 * @return List of harvested documents
	 */
	List<? extends Recommendable> harvest();
	
	/**
	 * This method will be called right after the instantiation on {@link HarvesterFactory}
	 * @param harvesterDefinition
	 */
	void setUp(HarvesterDefinition harvesterDefinition);
	
}
