package edu.seecs.dropwizard;


import java.util.Base64;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;


public class test {

	public static void main(String[] args) throws JsonProcessingException {
		test t = new test();
		String encode = t.encode("rahmad|seecs@123");
		System.out.println("encode = " + encode);
		t.decode(encode);
		//DBHandler db = new DBHandler();
		//Person p = db.authorize(encode);
		//System.out.println(p);
		ClientApp app = new ClientApp();
		Person p = new Person(3,"rahmad","seecs@123","Nouman","Ahmad","Seecs","0346-4567345");
		System.out.println(p);
		System.out.println(app.otoj(p));
	}

	String encode(String creds) {
		return Base64.getEncoder().encodeToString(creds.getBytes());
	}
	
	void decode(String encodedString) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decodedBytes);
		String[] split = decodedString.split(Pattern.quote("|"));
		String username = (split[0]);
		String password = (split[1]);
		System.out.println("decode = " + decodedString);
		System.out.println("username = " + username);
		System.out.println("password = " + password);
	}
	
}
