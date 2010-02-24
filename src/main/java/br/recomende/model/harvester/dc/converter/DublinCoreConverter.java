package br.recomende.model.harvester.dc.converter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.recomende.infra.util.SpringScope;
import br.recomende.model.document.Document;
import br.recomende.model.document.DocumentConverter;
import br.recomende.model.harvester.dc.DublinCoreDocument;

@Component
@Scope(SpringScope.PROTOTYPE)
public class DublinCoreConverter implements DocumentConverter {
	
	public String convert(Document rawDocument) {
		DublinCoreDocument document = (DublinCoreDocument) rawDocument;
		StringBuilder builder = new StringBuilder(document.getTitle());
		builder.append(" ");
		builder.append(document.getDescription());
		return builder.toString();
	}

	@Override
	public Class<? extends Document> getDocumentClass() {
		return DublinCoreDocument.class;
	}

}
