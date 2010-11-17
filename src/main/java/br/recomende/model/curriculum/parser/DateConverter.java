package br.recomende.model.curriculum.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateConverter implements Converter {
	
	private Logger log = LoggerFactory.getLogger(DateConverter.class);

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
