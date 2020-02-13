package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.ControlSystem;

public class Main {
	
	private ControlSystem cs;
	
	private Scanner dataReader;
	
	public Main() {
		cs = new ControlSystem();
		dataReader = new Scanner(System.in);
		employeeMenu();
		dataReader.close();
	}
	
	public void employeeMenu() {
		int options = 0;
		do {
			System.out.println("Welcome to the user menu.");
			System.out.println("Choose the option that you want the program does.");
			try {
				options = dataReader.nextInt();
			}catch( InputMismatchException e ) {
				System.out.println();
				System.out.println("Enter a valid option.");
				System.out.println();
				dataReader.nextLine();
			}catch( Exception e ) {
				System.out.println();
				System.out.println("Error!");
				System.out.println();
				dataReader.nextLine();
			}
			switch( options ) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			}
		}while( options != 6 );
	}
	
	public void menuAddUser() {
		System.out.println("Enter the option for the type of document.");
		try {
			int option = dataReader.nextInt();
		}catch( InputMismatchException e ) {
			System.out.println("Digite una opcion valida.");
		}
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}

}
