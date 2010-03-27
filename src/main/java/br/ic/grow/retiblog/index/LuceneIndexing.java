package br.ic.grow.retiblog.index;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.snowball.SnowballAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

@SuppressWarnings("deprecation")
public class LuceneIndexing extends Indexing{
	
	
	
	public LuceneIndexing() {
		super();
	}

	public LuceneIndexing(String indexDir, String keyword, String unIndexed,
			String unStored, String text) {
		super(indexDir, keyword, unIndexed, unStored, text);
	}




	protected Directory dir;
	
	protected boolean isCompound() {
		return true;
	}
	
	void index() throws IOException {
		
		dir = FSDirectory.getDirectory(indexDir, true);
		
        IndexWriter writer = new IndexWriter(dir, new SnowballAnalyzer("English", StopAnalyzer.ENGLISH_STOP_WORDS), true);
        writer.setUseCompoundFile(isCompound());
        
        Document doc = new Document();
		doc.add(new Field("id", keyword, Field.Store.YES, Field.Index.NOT_ANALYZED)); //keyword
		doc.add(new Field("city", unStored, Field.Store.YES, Field.Index.NO));//unIndexed
		doc.add(new Field("content", unStored, Field.Store.NO, Field.Index.ANALYZED));//unStored
		doc.add(new Field("city", unStored, Field.Store.YES, Field.Index.ANALYZED));//unStored
		writer.addDocument(doc);
        
		writer.optimize();
		writer.close();
    }

}
