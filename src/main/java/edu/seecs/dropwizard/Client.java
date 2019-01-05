package edu.seecs.dropwizard;

import java.io.IOException;
import java.util.Scanner;
import org.apache.http.client.ClientProtocolException;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Client {

	public static void main(String[] args) throws JsonProcessingException {

		ClientApp fixture = new ClientApp();
		int choice = 0;
		int id;
		String name;
		String fathername;
		String organization;
		String mobile;
		Person person;

		System.out.println("Menu {POST = 1;  DELETE = 2; PUT = 3; GET = 4}");
		Scanner input = new Scanner(System.in);
		choice = input.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Stuendent ID?");
			id = input.nextInt();
			System.out.println("Stuendent name?");
			name = input.nextLine();
			name = input.nextLine();
			System.out.println("Stuendent fathername?");
			fathername = input.nextLine();
			System.out.println("Stuendent organization?");
			organization = input.nextLine();
			System.out.println("Stuendent mobile?");
			mobile = input.nextLine();
			person = new Person(id, name, fathername, organization, mobile);
			try {
				fixture.doPost(person);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Student record added successfully!");
			break;
		case 2:
			System.out.println("Stuendent ID?");
			id = input.nextInt();
			try {
				fixture.doDelete(id);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Student record delete successfully!");
			break;
		case 3:
			System.out.println("Stuendent ID?");
			id = input.nextInt();
			System.out.println("Stuendent name?");
			name = input.nextLine();
			name = input.nextLine();
			System.out.println("Stuendent fathername?");
			fathername = input.nextLine();
			System.out.println("Stuendent organization?");
			organization = input.nextLine();
			System.out.println("Stuendent mobile?");
			mobile = input.nextLine();
			person = new Person(id, name, fathername, organization, mobile);
			try {
				fixture.doPut(person);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Student record updated successfully!");
			break;
		case 4:
			System.out.println("Stuendent ID?");
			id = input.nextInt();
			try {
				fixture.doGet(id);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Student record retrieved successfully!");
			break;
		default:
			System.out.println("Invalid choice");
			input.close();
		}

	}

}
