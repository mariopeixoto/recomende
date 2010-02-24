package br.recomende.model.curriculum.parser;

import org.apache.commons.beanutils.Converter;

import br.recomende.model.curriculum.LanguageSkill;

public class LanguageSkillConverter implements Converter{

	@SuppressWarnings("unchecked")
	@Override
	public Object convert(Class clazz, Object readSkill) {
		if (clazz.equals(LanguageSkill.class) && readSkill instanceof String) {
			return LanguageSkill.getType(readSkill.toString().toLowerCase());
		}
		return null;
	}

}
