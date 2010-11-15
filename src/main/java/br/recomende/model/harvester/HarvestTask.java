package br.recomende.model.harvester;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import br.recomende.infra.task.Task;
import br.recomende.model.repository.DocumentRepository;
import br.recomende.model.repository.HarvesterDefinitionRepository;

@Service
public class HarvestTask implements Task {
	
	private Logger log = LoggerFactory.getLogger(HarvestTask.class);
	
	private HarvesterDefinitionRepository harvesterDefinitionRepository;
	private HarvesterFactory harvesterFactory;
	private DocumentRepository documentRepository;
	private TaskExecutor taskExecutor;
	private PlatformTransactionManager transactionManager;

	@Autowired
	public HarvestTask(HarvesterDefinitionRepository harvesterDefinitionRepository, 
			HarvesterFactory harvesterFactory,
			DocumentRepository documentRepository,
			TaskExecutor taskExecutor,
			PlatformTransactionManager platformTransactionManager) {
		this.harvesterDefinitionRepository = harvesterDefinitionRepository;
		this.harvesterFactory = harvesterFactory;
		this.documentRepository = documentRepository;
		this.taskExecutor = taskExecutor;
		this.transactionManager = platformTransactionManager;
	}
	
	@Override
	@Scheduled(cron="0 0 2 * * *")
	//@Scheduled(fixedDelay=5000000)
	public void run() {
		log.info("Initializing harvest task");
		Collection<HarvesterDefinition> harvesterDefinitions = this.harvesterDefinitionRepository.list();
		for (HarvesterDefinition harvesterDefinition : harvesterDefinitions) {
			HarvestDocumentCommand command = new HarvestDocumentCommand(harvesterDefinition, 
					this.harvesterDefinitionRepository, this.harvesterFactory, this.documentRepository,
					this.transactionManager);
			this.taskExecutor.execute(command);
		}
		log.info("Harvest task finalized");
	}
	
}
