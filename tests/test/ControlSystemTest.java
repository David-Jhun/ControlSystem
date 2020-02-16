package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.ControlSystem;
import exceptions.ExistingDocumentException;

public class ControlSystemTest {

	private ControlSystem cs;
	
	private void setupStage1() {
		cs = new ControlSystem();
	}
	
	private void setupStage2() throws ExistingDocumentException {
		cs = new ControlSystem();
		cs.addUser(cs.IDENTITY_CARD, "654789", "Andres", "Perez", "", "");
	}
	
	private void setupStage3() throws ExistingDocumentException{
		cs = new ControlSystem();
		cs.addUser(cs.IDENTITY_CARD, "654789", "Andres", "Perez", "", "");
		cs.addUser(cs.FOREIGNER_ID, "135791", "Josh", "Stuart", "", "");
		cs.addUser(cs.IDENTITY_CARD, "246810", "Antonio", "Uchiha", "", "");
	}
	
	private void setupStage4() {
		cs = new ControlSystem();
		try {
			cs.addUser("Citizenship card", "246810", "Antonio", "Uchiha", "", "");
		}catch( ExistingDocumentException e ) {
			fail();
		}
	}
	
	@Test
	public void testAddUser() {
		setupStage1();
		try {
			cs.addUser(cs.CITIZENSHIP_CARD, "123456", "Pepe", "Morales", "3137886655", "Valle del lili");
		}catch( ExistingDocumentException e ) {
			fail();
		}
		assertEquals(cs.CITIZENSHIP_CARD, cs.getUsers().get(0).getTypeOfDocument());
		assertEquals("123456", cs.getUsers().get(0).getDocumentNumber());
		assertEquals("Pepe", cs.getUsers().get(0).getNames());
		assertEquals("Morales", cs.getUsers().get(0).getLastNames());
		assertEquals("3137886655", cs.getUsers().get(0).getPhone());
		assertEquals("3137886655", cs.getUsers().get(0).getPhone());
		assertEquals("Valle del lili", cs.getUsers().get(0).getAddress());
		assertEquals(1, cs.getUsers().size());
		//-------------------------------------------------------------------------------
		try {
			setupStage2();
			cs.addUser(cs.IDENTITY_CARD, "654789", "Andres", "Perez", "", "");
			throw new ExistingDocumentException("The user is already registered.");
		}catch( ExistingDocumentException e ) {
			assertTrue(true);
		}
		//-------------------------------------------------------------------------------
		try {
			setupStage3();
			cs.addUser(cs.CITIZENSHIP_CARD, "1006048", "Luis", "Hurtado", "", "");
		}catch( ExistingDocumentException e ) {
			fail();
		}
		assertEquals(4, cs.getUsers().size());
		//-------------------------------------------------------------------------------
	}
	
	@Test
	public void testSearchUser() {
		setupStage4();
		try {
			cs.searchUser("123456");
		}catch( NullPointerException e ) {
			fail();
		}
	}
	
	

}
