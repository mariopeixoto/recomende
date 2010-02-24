package br.recomende.model.harvester;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.recomende.infra.task.Task;
import br.recomende.model.document.Document;
import br.recomende.model.document.DocumentHarvester;
import br.recomende.model.repository.DocumentRepository;
import br.recomende.model.repository.HarvesterDefinitionRepository;

@Service
public class HarvestTask implements Task {
	
	private Logger log = LoggerFactory.getLogger(HarvestTask.class);
	
	private HarvesterDefinitionRepository harvesterDefinitionRepository;
	private HarvesterFactory harvesterFactory;
	private DocumentRepository documentRepository;
	
	@Autowired
	public HarvestTask(HarvesterDefinitionRepository harvesterDefinitionRepository, 
			HarvesterFactory harvesterFactory,
			DocumentRepository documentRepository) {
		this.harvesterDefinitionRepository = harvesterDefinitionRepository;
		this.harvesterFactory = harvesterFactory;
		this.documentRepository = documentRepository;
	}
	
	@Override
	@Scheduled(cron="0 0 23 * * *")
	//@Scheduled(fixedDelay=5000)
	public void run() {
		log.info("Initializing harvesting task");
		Collection<HarvesterDefinition> harvesterDefinitions = this.harvesterDefinitionRepository.list();
		for (HarvesterDefinition harvesterDefinition : harvesterDefinitions) {
			log.info("Harvesting "+harvesterDefinition.getHarvesterClass().getSimpleName()+", which last harvest on "+harvesterDefinition.getLastHarvest());
			DocumentHarvester harvester = harvesterFactory.instanceFor(harvesterDefinition);
			List<? extends Document> documents = harvester.harvest();
			log.info(documents.size() + " documents harvested");
			for (Document document : documents) {
				this.documentRepository.put(document);
			}
			if (documents.size() > 0) {
				log.info(documents.size() + " successfully added to repository and indexed");
			}
			harvesterDefinition.setLastHarvest(new Date());
			this.harvesterDefinitionRepository.put(harvesterDefinition);
		}
	}

}
