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

}
