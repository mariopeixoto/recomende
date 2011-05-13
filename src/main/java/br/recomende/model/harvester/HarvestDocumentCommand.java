package br.recomende.model.harvester;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import br.recomende.model.entity.DocumentHarvester;
import br.recomende.model.recommender.api.Recommendable;
import br.recomende.model.repository.HarvesterDefinitionRepository;
import br.recomende.model.repository.RecommendableRepository;

public class HarvestDocumentCommand implements Runnable {
	
	private Logger log = LoggerFactory.getLogger(HarvestDocumentCommand.class);

	private final HarvesterDefinition harvesterDefinition;
	private final HarvesterDefinitionRepository harvesterDefinitionRepository;
	private final HarvesterFactory harvesterFactory;
	private final RecommendableRepository recommendableRepository;
	private final PlatformTransactionManager transactionManager;
	
	public HarvestDocumentCommand(final HarvesterDefinition harvesterDefinition, 
			final HarvesterDefinitionRepository harvesterDefinitionRepository,
			final HarvesterFactory harvesterFactory,
			final RecommendableRepository recommendableRepository,
			final PlatformTransactionManager transactionManager) {
		this.harvesterDefinition = harvesterDefinition;
		this.harvesterDefinitionRepository = harvesterDefinitionRepository;
		this.harvesterFactory = harvesterFactory;
		this.recommendableRepository = recommendableRepository;
		this.transactionManager = transactionManager;
	}

	@Override
	public void run() {
		log.info("Harvesting "+harvesterDefinition.getName()+", which last harvest on "+harvesterDefinition.getLastHarvest());
		DocumentHarvester harvester = harvesterFactory.instanceFor(harvesterDefinition);
		List<? extends Recommendable> documents = harvester.harvest();
		log.info(documents.size() + " documents harvested");
		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		transactionDefinition.setReadOnly(false);
		transactionDefinition.setName(harvesterDefinition.getName());
		TransactionStatus transaction = transactionManager.getTransaction(transactionDefinition);
		try {
			for (Recommendable document : documents) {
				this.recommendableRepository.put(document);
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
