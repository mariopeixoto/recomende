package br.ic.grow.retriblog.persistence;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.ic.grow.retriblog.data.Item;
import br.ic.grow.retriblog.utilities.HibernateUtility;


public class MySqlPersistence extends Persistence{


	public MySqlPersistence() {
		super();
	}

	public MySqlPersistence(String title, String excerpt, String created,
			String postupdate, String permalink, String completeText,
			String analyzedText, String category, Item item) {
		super(title, excerpt, created, postupdate, permalink, completeText,
				analyzedText, category, item);
	}

	void salvarItem(){

		Session session = HibernateUtility.getSession();
		Transaction transaction = session.beginTransaction();

		Item item = new Item();

		item.setTitle(title);
		item.setExcerpt(excerpt);
		item.setCreated(created);
		item.setPostupdate(postupdate);
		item.setPermalink(permalink);
		item.setCompleteText(completeText);
		item.setAnalyzedText(analyzedText);
		item.setCategory(category);
		session.save(item); // Realiza persist�ncia

		transaction.commit(); // Finaliza transa��o
		session.close(); // Fecha sess�o

	}

	void salvarItemItem(){

		Session session = HibernateUtility.getSession();
		Transaction transaction = session.beginTransaction();

		session.save(item); // Realiza persist�ncia

		transaction.commit(); // Finaliza transa��o
		session.close(); // Fecha sess�o

	}

	@SuppressWarnings("unchecked")
	boolean checarPermalink(){//true = exist
		Session session = HibernateUtility.getSession();

		try{
			Criteria crit = session.createCriteria(Item.class)
			.add( Restrictions.like("permalink", permalink));
			List<Item> itens = crit.list();	
			if(itens.size() == 0){
				return false;
			}else{
				return true;
			}
		}catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	List<String> getAnalyzedTextFromCategory() {
		Session session = HibernateUtility.getSession();

		try{
			Criteria crit = session.createCriteria(Item.class)
			.add( Restrictions.like("category", category));
			List<Item> itens = crit.list();	
			List<String> analyzedText = new ArrayList<String>();
			for (Item i : itens) {
				analyzedText.add(i.getAnalyzedText());
			}
			return analyzedText;
		}catch (Exception e) {
			return null;
		}

	}


	@SuppressWarnings("unchecked")
	List<String> getCompleteTextFromCategory() {
		Session session = HibernateUtility.getSession();

		try{
			Criteria crit = session.createCriteria(Item.class)
			.add( Restrictions.like("category", category));
			List<Item> itens = crit.list();	
			List<String> completeText = new ArrayList<String>();
			for (Item i : itens) {
				completeText.add(i.getCompleteText());
			}
			return completeText;
		}catch (Exception e) {
			return null;
		}
	}

}
