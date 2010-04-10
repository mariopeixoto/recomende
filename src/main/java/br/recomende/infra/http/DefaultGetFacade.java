package br.recomende.infra.http;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.recomende.infra.util.SpringScope;

@Component
@Scope(SpringScope.PROTOTYPE)
public class DefaultGetFacade implements GetFacade {
	
	private static final Logger log = LoggerFactory.getLogger(DefaultGetFacade.class);
	
	public InputStream get(String endPoint, Map<String,Object> parameters) {
		HttpClient httpclient = new DefaultHttpClient();
		
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		
		String uri = endPoint;
		if (parameters != null) {
			for (String key : parameters.keySet()) {
				qparams.add(new BasicNameValuePair(key, parameters.get(key).toString()));
			}
			uri += "?"+URLEncodedUtils.format(qparams, "UTF-8");
		} 
		log.debug("Sending a GET requisition to "+uri);
		HttpGet httpGet = new HttpGet(uri);
		
		StatusLine status;
		InputStream stream;
		try {
			HttpResponse response = httpclient.execute(httpGet);
			status = response.getStatusLine();
			HttpEntity entity = response.getEntity();
			stream = entity.getContent();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if (status.getStatusCode() == HttpStatus.SC_OK ) {
			return stream;
		} else {
			throw new RuntimeException(new HttpException(String.valueOf(status.getStatusCode())));
		}
	}
	
}
