package util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class WebRequest {

	public static String callHttpsSelfSigned(final String url) throws ConnectionException {
		Validate.notNull(url, "URL cannot be null");
		HttpGet request = new HttpGet(url);
		String response = "";
		SSLContextBuilder builder = new SSLContextBuilder();		
		try {
		    builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		    SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
		            builder.build());
		    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(
	    		sslSocketFactory).build();
			HttpResponse httpResponse = httpClient.execute(request);
			response = EntityUtils.toString(httpResponse.getEntity());
		} catch (Exception e) {
			throw new ConnectionException(e.getMessage());
		} 
		return response;
	}
}
