package br.recomende.infra.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(SpringScope.PROTOTYPE)
public class DateConversionHelper {
	
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public String toXML(Date date) {
		return simpleDateFormat.format(date);
	}
	
}
