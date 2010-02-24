package br.recomende.model.curriculum.parser;

import org.apache.commons.beanutils.Converter;

import br.recomende.model.curriculum.Languages;

public class LanguagesConverter implements Converter {

	@SuppressWarnings("unchecked")
	@Override
	public Object convert(Class clazz, Object language) {
		if (clazz.equals(Languages.class) && language instanceof String) {
			return Languages.getType(language.toString().toLowerCase());
		}
		return null;
	}

}
