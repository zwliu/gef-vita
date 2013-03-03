package vita.appassemble.model;

public class APP extends Node {

	private String name;
	
	public APP() {
		name = null;
	}
	
	public APP(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
