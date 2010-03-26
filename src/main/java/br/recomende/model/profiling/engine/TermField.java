package br.recomende.model.profiling.engine;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TermField {
	
	TITLE("title"),
	KEYWORDS("keywords"),
	TEXT("text");
	
	private String name;
	private static final Map<String,TermField> map = new HashMap<String, TermField>();
	
	static {
		for (TermField e : EnumSet.allOf(TermField.class)) {
			map.put(e.getName(), e);
		}
	}
	
	private TermField(String name) {
		this.name = name;
	}
	
	public static TermField getType(String name) {
		return map.get(name);
	}
	
	public String getName() {
		return this.name;
	}

}
