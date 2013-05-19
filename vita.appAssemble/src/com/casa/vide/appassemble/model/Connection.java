package com.casa.vide.appassemble.model;

public class Connection {

	private Shape source;
	private Shape target;
	
	public Connection() {
		this(null, null);
	}
	
	public Connection(Shape source, Shape target) {
		setSource(source);
		setTarget(target);
	}

	public Shape getSource() {
		return source;
	}

	public void setSource(Shape source) {
		this.source = source;
		if(source != null)
			source.addSourceConnection(this);
	}

	public Shape getTarget() {
		return target;
	}

	public void setTarget(Shape target) {
		this.target = target;
		if(target != null)
			target.addTargetConnection(this);
	}
	
	public void detachSource() {
		if(source != null)
			source.removeSourceConnection(this);
	}
	
	public void detachTarget() {
		if(target != null)
			target.removeTargetConnection(this);
		
	}
	
}
