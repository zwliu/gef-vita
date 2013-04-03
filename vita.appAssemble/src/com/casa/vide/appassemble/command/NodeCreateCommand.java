package com.casa.vide.appassemble.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.casa.vide.appassemble.model.Node;


public class NodeCreateCommand extends Command {
	
	private Node parent = null;
	private Node child = null;
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public void setChild(Node child) {
		this.child = child;
	}
	
	public void setLayout(Rectangle rec) {
		if(child == null)
			return;
		child.setLayout(rec);
	}
	
	@Override
	public boolean canExecute() {
		if(parent == null || child == null)
			return false;
		return true;
	}
	
	@Override
	public void execute() {
		parent.addChild(child);
	}
	
	@Override 
	public boolean canUndo() {
		if(parent == null || child == null)
			return false;
		return parent.contains(child);
	}
	
	@Override
	public void undo() {
		parent.removeChild(child);
	}
	
}
