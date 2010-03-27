package br.ic.grow.retiblog.index;

import java.io.IOException;

public class IndexingControler {

	
	public void indexRun(Indexing indexing) throws IOException{
		
		indexing.index();
		
	}

}
