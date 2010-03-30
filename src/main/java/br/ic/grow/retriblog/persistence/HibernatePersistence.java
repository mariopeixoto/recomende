package br.ic.grow.retriblog.persistence;

import java.util.List;

import br.ic.grow.retriblog.data.Item;
import br.recomende.model.harvester.blog.IBlogPostSubmitter;

public class HibernatePersistence extends Persistence {
	
	private ItemRepository itemRepository;
	
	private IBlogPostSubmitter blogPostSubmitter;
	
	public HibernatePersistence(ItemRepository itemRepository, IBlogPostSubmitter blogPostSubmitter) {
		super();
		this.itemRepository = itemRepository;
		this.blogPostSubmitter = blogPostSubmitter;
	}

	public HibernatePersistence(String title, String excerpt, String created,
			String postupdate, String permalink, String completeText,
			String analyzedText, String category, Item item, ItemRepository itemRepository, IBlogPostSubmitter blogPostSubmitter) {
		super(title, excerpt, created, postupdate, permalink, completeText,
				analyzedText, category, item);
		this.itemRepository = itemRepository;
		this.blogPostSubmitter = blogPostSubmitter;
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
		
		this.blogPostSubmitter.submit(title, created, excerpt, permalink);
	}

	@Override
	void salvarItemItem() {
		this.itemRepository.put(this.item);
		this.blogPostSubmitter.submit(this.item.getTitle(), this.item.getCreated(), this.item.getExcerpt(), this.item.getPermalink());
	}

}
