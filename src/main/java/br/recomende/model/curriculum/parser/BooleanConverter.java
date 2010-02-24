package br.recomende.model.curriculum.parser;

import org.apache.commons.beanutils.Converter;

public class BooleanConverter implements Converter {

	@SuppressWarnings("unchecked")
	public Object convert(Class clazz, Object param) {
		if (clazz.equals(Boolean.class) && param instanceof String) {
			if (param.toString().equalsIgnoreCase("sim")) {
				return true;
			} else {
				return false;
			}
		}
		return null;
	}

}
