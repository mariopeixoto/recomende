package br.ic.grow.retriblog.persistence;

import java.util.List;

public class PersistenceControler {
	
	public void salvarItemRun(Persistence persistence){
		persistence.salvarItem();
	}
	 
	public void salvarItemItemRun(Persistence persistence){
		persistence.salvarItemItem();
	}
	 
	public boolean checarPermalinkRun(Persistence persistence){
		return persistence.checarPermalink();
		
	}
	 
	public List<String> getCompleteTextFromCategoryRun(Persistence persistence){
		return persistence.getCompleteTextFromCategory();
	}
	 
	public List<String> getAnalyzedTextFromCategoryRun(Persistence persistence){
		return persistence.getAnalyzedTextFromCategory();
	}

}
