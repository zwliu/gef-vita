package com.casa.vide.appassemble.command;

import org.eclipse.gef.commands.Command;

import com.casa.vide.appassemble.model.Node;


public class NodeDeleteCommand extends Command {
	
	private Node parent = null;
	private Node child = null;
	
	public void setParent(Object parent) {
		if(parent instanceof Node)
			this.parent = (Node)parent;
	}
	
	public void setChild(Object child) {
		if(child instanceof Node)
			this.child = (Node)child;
	}
	
	@Override
	public boolean canExecute() {
		if(parent == null || child == null)
			return false;
		return true;
	}
	
	@Override
	public void execute() {
		parent.removeChild(child);
	}
	
	@Override
	public void undo() {
		if(!parent.contains(child))
			parent.addChild(child);
	}
}
