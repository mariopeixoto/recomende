package br.ic.grow.retriblog.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpUtils {

	String getPage(String url){

		// Create an instance of HttpClient.
		HttpClient client = new DefaultHttpClient();

		// Create a method instance.
		HttpGet method = new HttpGet(url);

		// Provide custom retry handler is necessary
		//method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
		//		new DefaultHttpMethodRetryHandler(3, false));

		try {

			HttpResponse response = client.execute(method);
			// Execute the method.
			//int statusCode = client.executeMethod(method);
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + statusLine);
			}

			// Read the response body.
			//String responseBody = method.getResponseBodyAsString();
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary data
			return convertStreamToString(stream);

		} catch (Exception e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		}
		return "erro";  
	}

	public String convertStreamToString(InputStream is) throws IOException {
		if (is != null) {
			StringBuilder sb = new StringBuilder();
			String line;
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} finally {
				is.close();
			}
			return sb.toString();
		} else {        
			return "";
		}
	}


	void savePage(String url, String oldDtd, String newDtd){

		// Create an instance of HttpClient.
		HttpClient client = new DefaultHttpClient();

		// Create a method instance.
		HttpGet method = new HttpGet(url);

		// Provide custom retry handler is necessary
		//method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
		//		new DefaultHttpMethodRetryHandler(3, false));


		FileHandling fh = new FileHandling();

		try {
			// Execute the method.
			HttpResponse response = client.execute(method);

			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + statusLine);
			}

			// Read the response body.
			//String responseBody = method.getResponseBodyAsString();
			HttpEntity entity = response.getEntity();
			InputStream stream = entity.getContent();
			fh.saveFileInputstream("D:\\LiGeiRo\\workspace\\tag.xml", oldDtd, newDtd, stream, false);
			//responseBodyIn.close(); 

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary data

		} catch (Exception e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/*String convertStreamToString(InputStream is) {
		
		String returned;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        returned = StringEscapeUtils.unescapeHtml(sb.toString());
        return returned;
    }*/

}
