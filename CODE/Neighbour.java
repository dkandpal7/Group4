//package create;

public class Neighbour {

private int vn;
private Neighbour next;
public int getVn() {
	return vn;
}
public void setVn(int vn) {
	this.vn = vn;
}
public Neighbour getNext() {
	return next;
}
public void setNext(Neighbour next) {
	this.next = next;
}
public Neighbour(int vn, Neighbour next) {
	this.vn = vn;
	this.next = next;
}
}
