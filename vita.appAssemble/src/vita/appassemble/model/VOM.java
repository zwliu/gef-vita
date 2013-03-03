package vita.appassemble.model;

public class VOM extends Node {
	
	private String name;
	
	public VOM() {
		name = null;
	}
	
	public VOM(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
