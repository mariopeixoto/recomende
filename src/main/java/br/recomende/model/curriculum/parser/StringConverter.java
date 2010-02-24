package br.recomende.model.curriculum.parser;

import org.apache.commons.beanutils.Converter;

public class StringConverter implements Converter {
	
	@SuppressWarnings("unchecked")
	@Override
	public Object convert(Class clazz, Object string) {
		if (clazz.equals(String.class) && string instanceof String) {
			return string.toString().toLowerCase();
		}
		return null;
	}

}
