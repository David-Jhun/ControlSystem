package model;

import java.util.ArrayList;

import exceptions.ExistingDocumentException;

public class ControlSystem {
	
	public final static String IDENTITY_CARD = "Identity card";
	public final static String CITIZENSHIP_CARD = "Citizenship card";
	public final static String FOREIGNER_ID = "Foreigner ID";
	
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
	
	public String addShift() {
		String data = "";
		if( letter >= 91 )
			letter = 65;
		if( number > 99 ) {
			number = 0;
			changeLetter();
		}
		Shift shift = new Shift(letter, number);
		data = "" + shift;
		shifts.add(shift);
		changeNumber();
		return data;
	}
	
	public void assignShiftToUser(String documentNumber, String dataShift) {
		Shift shift = searchUnassignShift(dataShift);
		User user = searchUser(documentNumber);
		user.setShift(shift);
		user.getShift().setAssigned(true);
	}
	
	public String consultShiftToAttend() {
		String xd = "";
		if( shifts.isEmpty() ) {
			xd = "\nThere are not shifts to attend.";
		}else {
			for( int i = 0 ; i < shifts.size() ; i++ ) {
				if( shifts.get(i).isAssigned() == true && shifts.get(i + 1) != null ) {
					if( shifts.get(i).getComplete().compareTo(shifts.get(i + 1).getComplete()) < 1 ) {
						xd = shifts.get(i).getComplete();
					}else {
						xd = shifts.get(i + 1).getComplete();
					}
				}else {
					xd = shifts.get(i).getComplete();
				}
			}
		}
		return xd;
	}
	
	public void attendUserShift() {
		for( int i = 0 ; i < users.size() ; i++ ) {
			if( users.get(i).getShift() != null ) {
				if( users.get(i).getShift().isAssigned() == true ) {
					
				}
			}
		}
	}
	
	public Shift searchUnassignShift( String dataShift ) throws NullPointerException{
		Shift shift = null;
		for( int i = 0 ; i < shifts.size() ; i++ ) {
			if( shifts.get(i).isAssigned() == false && shifts.get(i).getComplete().compareTo(dataShift) == 0 ) {
				shift = shifts.get(i);
			}
		}
		return shift;
	}
	
	public void removeAttendedShifts() {
		for( int i = 0 ; i < shifts.size() ; i++ ) {
			if( shifts.get(i).isAttended() == true ) {
				shifts.remove(i);
			}
		}
	}
	
	public boolean existingUser(String documentNumber) {
		boolean status = false;
		for( int i = 0 ; i < users.size() ; i++ ) {
			if( users.get(i).getDocumentNumber().compareTo(documentNumber) == 0 )
				status = true;
		}
		return status;
	}
	
	public boolean existingShift(String dataShift) {
		boolean status = false;
		for( int i = 0 ; i < shifts.size() ; i++ ) {
			if( shifts.get(i).getComplete().compareTo(dataShift) == 0 ) 
				status = true;
		}
		return status;
	}
	
	public void changeLetter() {
		letter++;
	}
	
	public void changeNumber() {
		number++;
	}

}
