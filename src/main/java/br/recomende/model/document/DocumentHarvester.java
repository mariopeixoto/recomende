package br.recomende.model.document;

import java.util.List;

import br.recomende.model.harvester.HarvesterDefinition;
import br.recomende.model.harvester.HarvesterFactory;

public interface DocumentHarvester {
	
	/**
	 * Method to harvest documents
	 * @return List of harvested documents
	 */
	List<? extends Document> harvest();
	
	/**
	 * This method will be called right after the instantiation on {@link HarvesterFactory}
	 * @param harvesterDefinition
	 */
	void setUp(HarvesterDefinition harvesterDefinition);
	
}
