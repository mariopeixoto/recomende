package br.recomende.model.document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.recomende.infra.util.SpringScope;

@Component
@Scope(SpringScope.APPLICATION)
public class DocumentConverterFactory {
	
	private Map<Class<?>,DocumentConverter> factoryMap;
	
	@Autowired
	public DocumentConverterFactory(List<DocumentConverter> converters) {
		this.factoryMap = new HashMap<Class<?>, DocumentConverter>();
		for (DocumentConverter converter : converters) {
			this.factoryMap.put(converter.getDocumentClass(), converter);
		}
	}

	public DocumentConverter instanceFor(Document document) {
		DocumentConverter converter = this.factoryMap.get(document.getClass());
		if (converter != null) {
			return converter;
		} else {
			throw new BeanInstantiationException(document.getClass(), "Failed to instantiate bean, no converter founded on spring web context for bean class [" + document.getClass() + "]");
		}
	}
	
}
