package br.ic.grow.retriblog.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.ic.grow.retriblog.data.Item;
import br.recomende.infra.persistence.dao.RepositoryWrapper;

@Repository
public class ItemDao extends RepositoryWrapper<Item, Integer> implements ItemRepository {

	public ItemDao() {
		super(Item.class);
	}

	@SuppressWarnings("unchecked")
	public boolean checkPermalink(String permalink) {
		Criteria crit = getSession().createCriteria(Item.class)
						.add( Restrictions.like("permalink", permalink));
		List<Item> itens = (List<Item>) crit.list();	
		if(itens.size() == 0) {
			return false;
		} else{
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getAnalyzedTextFromCategory(String category) {
		Criteria crit = getSession().createCriteria(Item.class)
						.add( Restrictions.like("category", category));
		List<Item> itens = (List<Item>) crit.list();	
		List<String> analyzedText = new ArrayList<String>();
		for (Item i : itens) {
			analyzedText.add(i.getAnalyzedText());
		}
		return analyzedText;
	}

	@SuppressWarnings("unchecked")
	public List<String> getCompleteTextFromCategory(String category) {
		Criteria crit = getSession().createCriteria(Item.class)
						.add( Restrictions.like("category", category));
		List<Item> itens = (List<Item>) crit.list();	
		List<String> completeText = new ArrayList<String>();
		for (Item i : itens) {
			completeText.add(i.getCompleteText());
		}
		return completeText;
	}
	
}
