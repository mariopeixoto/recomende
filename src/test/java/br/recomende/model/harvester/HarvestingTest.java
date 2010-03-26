package br.recomende.model.harvester;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import br.recomende.infra.http.GetFacade;
import br.recomende.infra.util.DateConversionHelper;
import br.recomende.model.document.DublinCoreDocument;
import br.recomende.model.harvester.dc.OAIDublinCoreHarvester;
import br.recomende.model.harvester.dc.parser.OAIListRecordsParser;

public class HarvestingTest {
	
	private Mockery mockery;
	private GetFacade getFacade;
	
	@Before
	public void config() {
		this.mockery = new Mockery();
		this.getFacade = this.mockery.mock(GetFacade.class);
	}

	@Test
	public void harvest() throws ClientProtocolException, IOException, SAXException, URISyntaxException {
		mockery.checking(new Expectations() {
			{
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("verb", "ListRecords");
				map.put("metadataPrefix", "oai_dc");
				one(getFacade).get("testEndPoint", map);
				will(returnValue(HarvestingTest.class.getResourceAsStream("/oai/listRecords.xml")));
			}
		});
		
		HarvesterDefinition harvesterDefinition = new HarvesterDefinition();
		harvesterDefinition.setEndPoint("testEndPoint");
		OAIDublinCoreHarvester oaiDublinCoreHarvester = new OAIDublinCoreHarvester(this.getFacade,new OAIListRecordsParser(), new DateConversionHelper());
		oaiDublinCoreHarvester.setUp(harvesterDefinition);
		List<DublinCoreDocument> documents = oaiDublinCoreHarvester.harvest();
		Assert.assertNotNull(documents);
	}
	
}