package br.ic.grow.retiblog.index;


public class IndexingFactory {

	
	public static Indexing getLuceneIndexing(){
		return new LuceneIndexing();
	}
	
	public static Indexing getLuceneIndexing(String indexDir, String keyword, String unIndexed,
			String unStored, String text){
		return new LuceneIndexing(indexDir, keyword, unIndexed,
				unStored, text);
	}
	
}
