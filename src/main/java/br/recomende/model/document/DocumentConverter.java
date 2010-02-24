package br.recomende.model.document;

/**
 * Interface implemented by converters of supported documents for convert it into text that will be indexed by lucene.
 * @author Mário Peixoto
 *
 */
public interface DocumentConverter {
	
	/**
	 * Converts a supported document into text
	 * @param document
	 * @return Document converted into text
	 */
	String convert(Document document);
	
	Class<? extends Document> getDocumentClass();
	
}
