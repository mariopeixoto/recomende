package br.ic.grow.retriblog.persistence;

import java.util.List;

import br.ic.grow.retriblog.data.Item;
import br.recomende.model.repository.GenericRepository;

public interface ItemRepository extends GenericRepository<Item, Integer> {
	
	boolean checkPermalink(String permalink);
	
	List<String> getAnalyzedTextFromCategory(String category);
	
	List<String> getCompleteTextFromCategory(String category);
	
}
