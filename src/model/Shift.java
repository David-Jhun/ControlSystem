package model;

public class Shift {
	
	private boolean status;
	private boolean assigned;
	private char letter;
	private int number;
	
	public Shift(int letter, int number) {
		status = false;
		assigned = false;
		this.letter = (char)letter;
		this.number = number;
	}

	public boolean isStatus() {
		return status;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public char getLetter() {
		return letter;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}
	
	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		String data = "";
		if( number < 10 ) {
			switch(number) {
			case 0:
				data = "\n" + letter + "00";
				break;
			case 1:
				data = "\n" + letter + "01";
				break;
			case 2:
				data = "\n" + letter + "02";
				break;
			case 3:
				data = "\n" + letter + "03";
				break;
			case 4:
				data = "\n" + letter + "04";
				break;
			case 5:
				data = "\n" + letter + "05";
				break;
			case 6:
				data = "\n" + letter + "06";
				break;
			case 7:
				data = "\n" + letter + "07";
				break;
			case 8:
				data = "\n" + letter + "08";
				break;
			case 9:
				data = "\n" + letter + "09";
				break;
			}
		}else {
			data = "\n" + letter + number;
		}
		return data;
	}
	
}
