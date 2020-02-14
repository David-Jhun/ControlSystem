package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.EmptyFieldException;
import exceptions.ExistingDocumentException;
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
			System.out.println();
			System.out.println("Welcome to the user menu.");
			System.out.println("Choose the option that you want the program does.");
			System.out.println("1. Register an user.");
			System.out.println("2. Generate a shift.");
			System.out.println("3. Assign shift to the user.");
			System.out.println("4. Search a user by its document number.");
			System.out.println("7. Exit from the menu.");
			System.out.println();
			try {
				options = dataReader.nextInt();
				dataReader.nextLine();
			}catch( InputMismatchException e ) {
				System.out.println();
				System.out.println("Enter a valid option.");
				dataReader.nextLine();
			}catch( Exception e ) {
				System.out.println();
				System.out.println("Error!");
				dataReader.nextLine();
			}
			switch( options ) {
			case 1:
				menuAddUser();
				break;
			case 2:
				menuGenerateShift();
				break;
			case 3:
				menuAssignShiftToUser();
				break;
			case 4:
				menuSearchUser();
				break;
			case 5:
				break;
			case 6:
				break;
			}
		}while( options != 7 );
	}
	
	public void menuAddUser() {
		int option = 0;
		boolean xd = true;
		String typeOfDocument = "";
		while( xd ) {
			System.out.println();
			System.out.println("Enter the option for the type of document.");
			System.out.println("1. Identity card.");
			try {
				option = dataReader.nextInt();
				dataReader.nextLine();
				xd = false;
			}catch( InputMismatchException e ) {
				System.out.println();
				System.out.println("Enter a valid option.");
				dataReader.nextLine();
			}catch( Exception e ) {
				System.out.println();
				System.out.println("Error!");
				dataReader.nextLine();
			}
		}
		switch(option) {
		case 1:
			typeOfDocument = cs.IDENTITY_CARD;
			break;
		case 2:
			typeOfDocument = cs.CITIZENSHIP_CARD;
			break;
		case 3:
			typeOfDocument = cs.FOREIGNER_ID;
			break;
		}
		System.out.println("Type the document number.");
		String documentNumber = dataReader.nextLine();
		System.out.println("Type the user's names.");
		String names = dataReader.nextLine();
		System.out.println("Type the user's last names.");
		String lastNames = dataReader.nextLine();
		System.out.println("Type the user's phone.");
		String phone = dataReader.nextLine();
		System.out.println("Type the user's address.");
		String address = dataReader.nextLine();
		try {
			if( typeOfDocument.equals("") ) {
				throw new EmptyFieldException("This field can't be empty.");
			}else if( documentNumber.equals("") ){
				throw new EmptyFieldException("This field can't be empty.");
			}else if( names.equals("") ) {
				throw new EmptyFieldException("This field can't be empty.");
			}else if( lastNames.equals("") ) {
				throw new EmptyFieldException("This field can't be empty.");
			}
			if( cs.existingUser(documentNumber) ) {
				throw new ExistingDocumentException("The user is already registered.");
			}else {
				cs.addUser(typeOfDocument, documentNumber, names, lastNames, phone, address);
				System.out.println("The user was successfully added.");
			}
		}catch( EmptyFieldException e ) {
			System.out.println();
			System.out.println(e.getMessage());
		}catch( ExistingDocumentException e  ) {
			System.out.println();
			System.out.println(e.getMessage());
		}
	}
	
	public void menuGenerateShift() {
		cs.addShift();
		System.out.println("The shift was successfully generated.");
	}
	
	public void menuAssignShiftToUser() {
		System.out.println("Type the document number from the user.");
		String documentNumber = dataReader.nextLine();
		if( cs.searchUser(documentNumber) == null ) {
			System.out.println();
			System.out.println("The user you tried to assign a shift is not registered on the system.");
		}else {
			if( cs.getShifts().isEmpty() ) {
				System.out.println();
				System.out.println("There are not shifts on the system.");
			}else {
				cs.assignShiftToUser(documentNumber);
				System.out.println("The shift was successfully assigned to the user.");
			}
		}
	}
	
	public void menuSearchUser() {
		System.out.println("Type the document number of the user.");
		String documentNumber = dataReader.nextLine();
		if( cs.searchUser(documentNumber) == null ) {
			System.out.println("The user was not found.");
		}else {
			System.out.println(cs.searchUser(documentNumber));
		}
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}

}
