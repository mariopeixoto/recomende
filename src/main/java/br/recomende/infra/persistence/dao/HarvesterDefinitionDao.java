package br.recomende.infra.persistence.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.harvester.HarvesterDefinition;
import br.recomende.model.repository.HarvesterDefinitionRepository;

@Component
@Scope(SpringScope.PROTOTYPE)
public class HarvesterDefinitionDao extends RepositoryWrapper<HarvesterDefinition, Integer> 
						implements HarvesterDefinitionRepository {

	public HarvesterDefinitionDao() {
		super(HarvesterDefinition.class);
	}
	
}
