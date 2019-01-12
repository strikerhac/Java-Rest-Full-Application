package edu.seecs.dropwizard;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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
	public void doGet(String username, String password, int id) throws ClientProtocolException, IOException {

		/* create the HTTP client and GET request */
		HttpGet httpGet = new HttpGet("http://localhost:18080/person/" + id);

		String credentials = encode(username + "|" + password);
		httpGet.setHeader("Authorization", credentials);

		/* execute request */
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity httpEntity = httpResponse.getEntity();

		/* process response */
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			String responseText = EntityUtils.toString(httpEntity);
			System.out.println();
			System.out.println(responseText);
		}
		else if (httpResponse.getStatusLine().getStatusCode() == 404) {
			EntityUtils.consume(httpEntity);
			System.out.println();
			System.out.println("person not found");
		}
		else if (httpResponse.getStatusLine().getStatusCode() == 401) {
			EntityUtils.consume(httpEntity);
			System.out.println();
			System.out.println("Incorrect username or password");
		}
		else {
			EntityUtils.consume(httpEntity);
			System.out.println();
			System.out.println("Invalid HTTP response: " + httpResponse.getStatusLine().getStatusCode());
		}
	}

	@SuppressWarnings("unused")
	public void doDelete(String username, String password, int id) throws ClientProtocolException, IOException {

		/* create the HTTP client and GET request */
		HttpDelete httpDel = new HttpDelete("http://localhost:18080/person/" + id);

		String credentials = encode(username + "|" + password);
		httpDel.setHeader("Authorization", credentials);

		/* execute request */
		HttpResponse httpResponse = httpClient.execute(httpDel);
		HttpEntity httpEntity = httpResponse.getEntity();

		/* process response */
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			String responseText = EntityUtils.toString(httpEntity);
			System.out.println(responseText);
			System.out.println("Student record delete successfully!");
		}
		else if (httpResponse.getStatusLine().getStatusCode() == 401) {
			EntityUtils.consume(httpEntity);
			System.out.println();
			System.out.println("Incorrect username or password");
		}
		else {
			EntityUtils.consume(httpEntity);
			System.out.println();
			System.out.println("person not found");
		}

	}

	public void doPost(Person p) throws IOException, UnsupportedEncodingException, ClientProtocolException {
		StringEntity param = new StringEntity(otoj(p));
		/* create the HTTP client and POST request */
		HttpPost httpPost = new HttpPost("http://localhost:18080/person");

		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setEntity(param);

		/* execute request */
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();

		/* process response */
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			String responseText = EntityUtils.toString(httpEntity);
			System.out.println(responseText);
			System.out.println("Student record added successfully!");
		} else {
			EntityUtils.consume(httpEntity);
			System.out.println();
			System.out.println("person with id: "+p.getId()+" is already present in the database");
			System.out.println("id must be unique");
		}

	}

	private String encode(String creds) {
		return Base64.getEncoder().encodeToString(creds.getBytes());
	}

	public String otoj(Person person) throws JsonProcessingException {
		return mapper.writeValueAsString(person);
	}
}
