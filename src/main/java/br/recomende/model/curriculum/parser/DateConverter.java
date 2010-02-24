package br.recomende.model.curriculum.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;
import org.apache.log4j.Logger;

public class DateConverter implements Converter {
	
	private Logger log = Logger.getLogger(DateConverter.class);

	@SuppressWarnings("unchecked")
	public Object convert(Class clazz, Object date) {
		if (clazz.equals(Date.class) && date instanceof String) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy");
			Date dateParsed;
			try {
				dateParsed = simpleDateFormat.parse(date.toString());
			
				return dateParsed;
			} catch (ParseException e) {
				log.error("Cannot convert String to date with format ddMMyyyy",e);
			}
		}
		return null;
	}

}
