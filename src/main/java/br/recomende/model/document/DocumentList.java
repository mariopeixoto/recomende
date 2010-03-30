package br.recomende.model.document;

import java.util.ArrayList;
import java.util.Collection;

public class DocumentList extends ArrayList<Document> {

	private static final long serialVersionUID = 8235698547155469691L;

	public DocumentList() {
		super();
	}

	public DocumentList(Collection<? extends Document> c) {
		super(c);
	}

	public DocumentList(int initialCapacity) {
		super(initialCapacity);
	}
	
	public int contains(Integer id, Class<?> documentClass) {
		for (Document document : this) {
			if (document.getId().equals(id) && document.getClass().equals(documentClass)) {
				return this.indexOf(document);
			}
		}
		return -1;
	}

}
