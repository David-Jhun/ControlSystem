package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import model.ControlSystem;

public class ControlSystemTest {

	private ControlSystem cs;
	
	private void setupStage1() {
		cs = new ControlSystem();
		cs.addShift();
	}
	
	@Test
	public void testsearchUnassignShift() {
		setupStage1();
		String data = "A00";
		assertEquals(data, cs.searchUnassignShift(data).getComplete());
	}

}
