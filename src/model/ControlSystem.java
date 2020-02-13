package model;

import java.util.ArrayList;

import exceptions.ExistingDocumentException;

public class ControlSystem {
	
	private int letter;
	private int number;
	
	private ArrayList<User> users;
	
	private ArrayList<Shift> shifts;
	
	public ControlSystem() {
		letter  = 65;
		number = 0;
		users = new ArrayList<User>();
		shifts = new ArrayList<Shift>();
	}

	public ArrayList<User> getUsers() {
		return users;
	}
	
	public ArrayList<Shift> getShifts() {
		return shifts;
	}

	public void addUser(String typeOfDocument, String documentNumber, String names, String lastNames, String phone, String address) throws ExistingDocumentException{
		User user = new User(typeOfDocument, documentNumber, names, lastNames, phone, address);
		users.add(user);
	}
	
	public User searchUser(String documentNumber) throws NullPointerException{
		User user = null;
		for(int i = 0; i < users.size() ; i++) {
			if(users.get(i).getDocumentNumber().compareTo(documentNumber) == 0) {
				user = users.get(i);
			}
		}
		return user;
	}
	
	public void addShift() {
		if( letter >= 91 )
			letter = 65;
		if( number > 99 ) {
			number = 0;
			changeLetter();
		}
		Shift shift = new Shift(letter, number);
		shifts.add(shift);
		changeNumber();
	}
	
	public void assignShiftToUser(String documentNumber) {
		Shift shift = searchUnassignShift();
		User user = searchUser(documentNumber);
		user.setShift(shift);
		user.getShift().setAssigned(true);
	}
	
	public void attendUserShift() {
		for( int i = 0 ; i < users.size() ; i++ ) {
			if( users.get(i).getShift() != null ) {
				if( users.get(i).getShift().isAssigned() == true ) {
					
				}
			}
		}
	}
	
	
	
	public Shift searchUnassignShift() throws NullPointerException{
		Shift shift = null;
		for( int i = 0 ; i < shifts.size() ; i++ ) {
			if( shifts.get(i).isAssigned() == false ) {
				shift = shifts.get(i);
			}
		}
		return shift;
	}
	
	public void removeAttendedShifts() {
		for( int i = 0 ; i < shifts.size() ; i++ ) {
			if( shifts.get(i).isStatus() == true ) {
				shifts.remove(i);
			}
		}
	}
	
	public void changeLetter() {
		letter++;
	}
	
	public void changeNumber() {
		number++;
	}

}
