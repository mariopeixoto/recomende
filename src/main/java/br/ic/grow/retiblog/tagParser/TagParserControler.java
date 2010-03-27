package br.ic.grow.retiblog.tagParser;


import java.util.ArrayList;

import br.ic.grow.retriblog.data.Item;

public class TagParserControler {
	
	
	public ArrayList<Item> tagSearchRun(TagParser tagParser){
		return tagParser.tagSearch();
	}

}
