package br.recomende.model.harvester.dc.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.Digester;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.document.DublinCoreDocument;
import br.recomende.model.harvester.HarvesterDefinition;

@Component
@Scope(SpringScope.PROTOTYPE)
public class OAIListRecordsParser {

	private Digester digester;
	
	public OAIListRecordsParser() {
		this.digester = new Digester();
		this.setUp();
	}

	private void setUp() {
		this.digester.setNamespaceAware(true);
		this.digester.addObjectCreate("*/ListRecords", ArrayList.class);
		this.digester.addObjectCreate("*/ListRecords/record/metadata/dc",DublinCoreDocument.class);
		this.digester.addBeanPropertySetter("*/ListRecords/record/metadata/dc/title");
		this.digester.addBeanPropertySetter("*/ListRecords/record/metadata/dc/creator");
		this.digester.addBeanPropertySetter("*/ListRecords/record/metadata/dc/subject");
		this.digester.addBeanPropertySetter("*/ListRecords/record/metadata/dc/description");
		this.digester.addBeanPropertySetter("*/ListRecords/record/metadata/dc/publisher");
		this.digester.addBeanPropertySetter("*/ListRecords/record/metadata/dc/contributor");
		this.digester.addBeanPropertySetter("*/ListRecords/record/metadata/dc/date");
		this.digester.addBeanPropertySetter("*/ListRecords/record/metadata/dc/type");
		this.digester.addBeanPropertySetter("*/ListRecords/record/metadata/dc/format");
		this.digester.addBeanPropertySetter("*/ListRecords/record/metadata/dc/identifier");
		this.digester.addBeanPropertySetter("*/ListRecords/record/metadata/dc/source");
		this.digester.addBeanPropertySetter("*/ListRecords/record/metadata/dc/language");
		this.digester.addBeanPropertySetter("*/ListRecords/record/metadata/dc/relation");
		this.digester.addBeanPropertySetter("*/ListRecords/record/metadata/dc/coverage");
		this.digester.addBeanPropertySetter("*/ListRecords/record/metadata/dc/rights");
		this.digester.addSetNext("*/ListRecords/record/metadata/dc", "add");
	}
	
	@SuppressWarnings("unchecked")
	public List<DublinCoreDocument> parse(InputStream stream, HarvesterDefinition harvesterDefinition) throws IOException, SAXException {
		List<DublinCoreDocument> returnList = (List<DublinCoreDocument>) this.digester.parse(stream);
		if (returnList == null) {
			return new ArrayList<DublinCoreDocument>();
		}
		for (DublinCoreDocument doc : returnList) {
			doc.setHarvesterDefinition(harvesterDefinition);
		}
		return returnList;
	}
	
}
