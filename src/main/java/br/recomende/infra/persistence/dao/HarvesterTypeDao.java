package br.recomende.infra.persistence.dao;

import org.springframework.stereotype.Repository;

import br.recomende.model.harvester.HarvesterType;
import br.recomende.model.repository.HarvesterTypeRepository;

@Repository
public class HarvesterTypeDao extends RepositoryWrapper<HarvesterType, String>
		implements HarvesterTypeRepository {
	
	public HarvesterTypeDao() {
		super(HarvesterType.class);
	}

}
