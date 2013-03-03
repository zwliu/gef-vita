package vita.appassemble.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;

public class Node {

	private List<Object> children = new ArrayList<Object>();
	private Rectangle layout = new Rectangle();
	
	public void addChild(Object child) {
		children.add(child);
	}
	
	public void removeChild(Object child) {
		children.remove(child);
	}
	
	public List<Object> getChildren() {
		return children;
	}
	
	public void setLayout(Rectangle rec) {
		layout = rec;
	}
	
	public Rectangle getLayout() {
		return layout;
	}
	
	public boolean contains(Node child) { 
		return children.contains(child); 
	}

}
