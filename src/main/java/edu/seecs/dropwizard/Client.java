package edu.seecs.dropwizard;

import java.io.IOException;
import java.util.Scanner;
import org.apache.http.client.ClientProtocolException;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Client {

	public static void main(String[] args) throws JsonProcessingException {

		ClientApp fixture = new ClientApp();
		int choice = 0;
		String username;
		String password;
		int id;
		String name;
		String fathername;
		String organization;
		String mobile;
		Person person;
		System.out.println("<--------------------------------------------------------------------------------->");
		System.out.println("<-----------------------------------HTTP CLIENT----------------------------------->");
		while (true) {
			System.out.println("<--------------------------------------------------------------------------------->");
			System.out.println("");
			System.out.print("Menu {GET = 1;  POST = 2;  DELETE = 3}:  ");
			Scanner input = new Scanner(System.in);
			choice = input.nextInt();
			switch (choice) {
			case 1:
				System.out.print("User Name: ");
				input.nextLine();
				username = input.nextLine();
				System.out.print("Password: ");
				password = input.nextLine();
				System.out.print("Student ID: ");
				id = input.nextInt();
				try {
					fixture.doGet(username, password, id);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 2:
				System.out.println("Student ID?");
				id = input.nextInt();
				System.out.println("Student username?");
				username = input.nextLine();
				username = input.nextLine();
				System.out.println("Student password?");
				password = input.nextLine();
				System.out.println("Student name?");
				name = input.nextLine();
				System.out.println("Student fathername?");
				fathername = input.nextLine();
				System.out.println("Student organization?");
				organization = input.nextLine();
				System.out.println("Student mobile?");
				mobile = input.nextLine();
				person = new Person(id, username, password, name, fathername, organization, mobile);
				try {
					fixture.doPost(person);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;

			case 3:
				System.out.print("User Name: ");
				input.nextLine();
				username = input.nextLine();
				System.out.print("Password: ");
				password = input.nextLine();
				System.out.print("Student ID: ");
				id = input.nextInt();
				try {
					fixture.doDelete(username, password, id);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				break;

			default:
				System.out.println("Invalid choice");
				input.close();
			}

		}
	}

}
