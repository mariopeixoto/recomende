package br.recomende.model.harvester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.entity.DocumentHarvester;

@Component
@Scope(SpringScope.APPLICATION)
public class HarvesterFactory {

	private ApplicationContext applicationContext;
	
	@Autowired
	public HarvesterFactory(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	public DocumentHarvester instanceFor(HarvesterDefinition harvesterDefinition) {
		Class<?> harvesterClass = harvesterDefinition.getHarvesterType().getHarvesterClass();
		try {
			DocumentHarvester harvester = (DocumentHarvester) this.applicationContext.getBean(harvesterClass);
			harvester.setUp(harvesterDefinition);
			return harvester;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
