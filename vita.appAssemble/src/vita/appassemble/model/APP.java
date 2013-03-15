package vita.appassemble.model;

import org.eclipse.swt.graphics.Color;

public class APP extends Node {

	public static final String PROPERTY_NAME = "AppName";
	public static final String PROPERTY_COLOR = "AppColor";
	private String name;
	private Color color;
	
	public static Color createRandomColor() { /** Just for Fun :) **/ return new Color(null, (new Double(Math.random() * 128)).intValue() + 128 , (new Double(Math.random() * 128)).intValue() + 128 , (new Double(Math.random() * 128)).intValue() + 128 ); }
	
	public APP() {
		name = null;
		color = createRandomColor();
	}
	
	public APP(String name) {
		this.name = name;
		color = createRandomColor();
	}
	
	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		listeners.firePropertyChange(PROPERTY_NAME, oldName, name);
	}
	
	public String getName() {
		return name;
	}
	
	public void setColor(Color color) {
		Color oldColor = this.color;
		this.color = color;
		listeners.firePropertyChange(PROPERTY_COLOR, oldColor, color);
	}
	
	public Color getColor() {
		return color;
	}
	
}
