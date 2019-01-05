package edu.seecs.dropwizard;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("deprecation")
public class ClientApp {

	private HttpClient httpClient;
	private ObjectMapper mapper;

	public ClientApp() {
		this.httpClient = new DefaultHttpClient();
		this.mapper = new ObjectMapper();
	}

	@SuppressWarnings("unused")
	public void doGet(int id) throws ClientProtocolException, IOException {

		/* create the HTTP client and GET request */
		HttpGet httpGet = new HttpGet("http://localhost:18080/person/" + id);

		/* execute request */
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();

		/* process response */
		if (httpResponse.getStatusLine().getStatusCode() == 200) {

			String responseText = EntityUtils.toString(httpEntity);

			System.out.println(responseText);

		} else {
			System.err.println("Invalid HTTP response: " + httpResponse.getStatusLine().getStatusCode());
		}

	}

	@SuppressWarnings("unused")
	public void doDelete(int id) throws ClientProtocolException, IOException {

		/* create the HTTP client and GET request */
		HttpDelete httpDel = new HttpDelete("http://localhost:18080/person/" + id);

		/* execute request */
		HttpResponse httpResponse = httpClient.execute(httpDel);
		HttpEntity httpEntity = httpResponse.getEntity();

		/* process response */
		if (httpResponse.getStatusLine().getStatusCode() == 200) {

			String responseText = EntityUtils.toString(httpEntity);

			System.out.println(responseText);

		} else {
			System.err.println("Invalid HTTP response: " + httpResponse.getStatusLine().getStatusCode());
		}

	}

	@SuppressWarnings({ "unused" })
	public void doPost(Person p) throws IOException, UnsupportedEncodingException, ClientProtocolException {
		StringEntity param = new StringEntity(otoj(p));
		/* create the HTTP client and POST request */
		HttpPost httpPost = new HttpPost("http://localhost:18080/person");

		httpPost.addHeader("Content-Type", "application/json");
		httpPost.setEntity(param);

		/* execute request */
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();

		/* process response */
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			String responseText = EntityUtils.toString(httpEntity);
			System.out.println(responseText);

		} else {
			System.err.println("Invalid HTTP response: " + httpResponse.getStatusLine().getStatusCode());
		}

	}

	@SuppressWarnings({ "unused" })
	public void doPut(Person p) throws IOException, UnsupportedEncodingException, ClientProtocolException {
		StringEntity param = new StringEntity(otoj(p));
		/* create the HTTP client and POST request */
		HttpPut httpPut = new HttpPut("http://localhost:18080/person");

		httpPut.addHeader("Content-Type", "application/json");
		httpPut.setEntity(param);

		/* execute request */
		HttpResponse httpResponse = httpClient.execute(httpPut);
		HttpEntity httpEntity = httpResponse.getEntity();

		/* process response */
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			String responseText = EntityUtils.toString(httpEntity);
			System.out.println(responseText);

		} else {
			System.err.println("Invalid HTTP response: " + httpResponse.getStatusLine().getStatusCode());
		}

	}

	private String otoj(Person person) throws JsonProcessingException {
		return mapper.writeValueAsString(person);
	}
}
