package br.recomende.model.harvester.dc;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.recomende.infra.http.GetFacade;
import br.recomende.infra.util.DateConversionHelper;
import br.recomende.infra.util.SpringScope;
import br.recomende.model.entity.DocumentHarvester;
import br.recomende.model.entity.DublinCoreDocument;
import br.recomende.model.harvester.HarvesterDefinition;
import br.recomende.model.harvester.dc.parser.OAIListRecordsParser;

@Component
@Scope(SpringScope.PROTOTYPE)
public class OAIDublinCoreHarvester implements DocumentHarvester {
	
	private GetFacade getFacade;
	private OAIListRecordsParser parser;
	private HarvesterDefinition harvesterDefinition;
	private DateConversionHelper dateConversionHelper;
	
	@Autowired
	public OAIDublinCoreHarvester(GetFacade getFacade, OAIListRecordsParser parser,
			DateConversionHelper dateConversionHelper) {
		this.getFacade = getFacade;
		this.parser = parser;
		this.dateConversionHelper = dateConversionHelper;
	}
	
	public void setUp(HarvesterDefinition harvesterDefinition) {
		this.harvesterDefinition = harvesterDefinition;
	}
	
	public List<DublinCoreDocument> harvest() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("verb", "ListRecords");
		parameters.put("metadataPrefix", "oai_dc");
		if (this.harvesterDefinition.getLastHarvest() != null) {
			parameters.put("from", this.dateConversionHelper.toXML(this.harvesterDefinition.getLastHarvest()));
			parameters.put("until", this.dateConversionHelper.toXML(new Date()));
		}
		
		InputStream response = getFacade.get(this.harvesterDefinition.getEndPoint(), parameters);
		
		try {
			return this.parser.parse(response, harvesterDefinition);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
