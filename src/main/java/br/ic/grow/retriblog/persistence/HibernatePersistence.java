package br.ic.grow.retriblog.persistence;

import java.util.List;

import br.ic.grow.retriblog.data.Item;

public class HibernatePersistence extends Persistence {
	
	private ItemRepository itemRepository;
	
	public HibernatePersistence(ItemRepository itemRepository) {
		super();
		this.itemRepository = itemRepository;
	}

	public HibernatePersistence(String title, String excerpt, String created,
			String postupdate, String permalink, String completeText,
			String analyzedText, String category, Item item, ItemRepository itemRepository) {
		super(title, excerpt, created, postupdate, permalink, completeText,
				analyzedText, category, item);
		this.itemRepository = itemRepository;
	}

	@Override
	boolean checarPermalink() {
		return this.itemRepository.checkPermalink(this.permalink);
	}

	@Override
	List<String> getAnalyzedTextFromCategory() {
		return this.itemRepository.getAnalyzedTextFromCategory(this.category);
	}

	@Override
	List<String> getCompleteTextFromCategory() {
		return this.itemRepository.getCompleteTextFromCategory(this.category);
	}

	@Override
	void salvarItem() {
		Item item = new Item();

		item.setTitle(title);
		item.setExcerpt(excerpt);
		item.setCreated(created);
		item.setPostupdate(postupdate);
		item.setPermalink(permalink);
		item.setCompleteText(completeText);
		item.setAnalyzedText(analyzedText);
		item.setCategory(category);
		
		this.itemRepository.put(item);
	}

	@Override
	void salvarItemItem() {
		this.itemRepository.put(this.item);
	}

}
