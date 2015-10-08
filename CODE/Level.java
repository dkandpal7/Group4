//package create;

import java.util.ArrayList;

public class Level {

	public Level(int number,ArrayList<Integer> v) {
		super();
		this.number = number;
		this.v = v;
	}
	int number;
	ArrayList<Integer> v;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public ArrayList<Integer> getV() {
		return v;
	}
	public void setV(ArrayList<Integer> v) {
		this.v = v;
	}
	
}
