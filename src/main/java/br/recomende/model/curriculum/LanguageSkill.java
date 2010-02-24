package br.recomende.model.curriculum;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum LanguageSkill {

	GOOD("bem",1),
	REASONABLE("razoavelmente",2),
	POOR("pouco",3);
	
	private String skill;
	private int ordinal;
	
	private static final Map<String,LanguageSkill> map = new HashMap<String, LanguageSkill>();
	
	static {
		for (LanguageSkill e : EnumSet.allOf(LanguageSkill.class)) {
			map.put(e.getSkill(), e);
		}
	}
	
	private LanguageSkill(String skill, int ordinal) {
		this.skill = skill;
		this.ordinal = ordinal;
	}

	public String getSkill() {
		return skill;
	}

	public int getOrdinal() {
		return ordinal;
	}
	
	public static LanguageSkill getType(String skill) {
		return map.get(skill);
	}

}
