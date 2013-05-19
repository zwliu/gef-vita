package com.casa.vide.appassemble.command;

import org.eclipse.gef.commands.Command;

import com.casa.vide.appassemble.model.Connection;
import com.casa.vide.appassemble.model.Shape;

public class ConnectionCommand extends Command {
	
	private Shape oldSource;
	private Shape oldTarget;
	private Shape source;
	private Shape target;
	private Connection connection;
	
	public void setConnection(Connection conn) {
		connection = conn;
		source = conn.getSource();
		target = conn.getTarget();
		oldSource = source;
		oldTarget = target;
	}
	
	public boolean canExecute() {
		if(connection != null)
			return true;
		return false;
	}
	
	public void execute() {
		connection.detachSource();
		connection.detachTarget();
		connection.setSource(source);
		connection.setTarget(target);
	}
	
	public void setSource(Shape s) {
		oldSource = source;
		source = s;
	}
	
	public void setTarget(Shape t) {
		oldTarget = target;
		target = t;
	}
	
	public void undo() {
		connection.detachSource();
		connection.detachTarget();
		connection.setSource(oldSource);
		connection.setTarget(oldTarget);
	}
	
}
