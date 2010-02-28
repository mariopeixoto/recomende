package br.recomende.model.harvester;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import br.recomende.model.document.Document;
import br.recomende.model.document.DocumentHarvester;
import br.recomende.model.repository.DocumentRepository;
import br.recomende.model.repository.HarvesterDefinitionRepository;

public class HarvestDocumentCommand implements Runnable {
	
	private Logger log = LoggerFactory.getLogger(HarvestDocumentCommand.class);

	private final HarvesterDefinition harvesterDefinition;
	private final HarvesterDefinitionRepository harvesterDefinitionRepository;
	private final HarvesterFactory harvesterFactory;
	private final DocumentRepository documentRepository;
	private final PlatformTransactionManager transactionManager;
	
	public HarvestDocumentCommand(final HarvesterDefinition harvesterDefinition, 
			final HarvesterDefinitionRepository harvesterDefinitionRepository,
			final HarvesterFactory harvesterFactory,
			final DocumentRepository documentRepository,
			final PlatformTransactionManager transactionManager) {
		this.harvesterDefinition = harvesterDefinition;
		this.harvesterDefinitionRepository = harvesterDefinitionRepository;
		this.harvesterFactory = harvesterFactory;
		this.documentRepository = documentRepository;
		this.transactionManager = transactionManager;
	}

	@Override
	public void run() {
		log.info("Harvesting "+harvesterDefinition.getName()+", which last harvest on "+harvesterDefinition.getLastHarvest());
		DocumentHarvester harvester = harvesterFactory.instanceFor(harvesterDefinition);
		List<? extends Document> documents = harvester.harvest();
		log.info(documents.size() + " documents harvested");
		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		transactionDefinition.setReadOnly(false);
		transactionDefinition.setName(harvesterDefinition.getName());
		TransactionStatus transaction = transactionManager.getTransaction(transactionDefinition);
		try {
			for (Document document : documents) {
				//try {
					this.documentRepository.put(document);
				//} catch (Exception e) {
				//	log.debug(e.getMessage());
				//	continue;
				//}
			}
			if (documents.size() > 0) {
				log.info(documents.size() + " successfully added to repository and indexed");
			}
			harvesterDefinition.setLastHarvest(new Date());
			this.harvesterDefinitionRepository.put(harvesterDefinition);
			transactionManager.commit(transaction);
		} catch (Exception ex) {
			transactionManager.rollback(transaction);
			throw new RuntimeException(ex);
		}
	}

}
