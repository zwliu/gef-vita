package com.casa.vide.appassemble.command;

import org.eclipse.gef.commands.Command;

import com.casa.vide.appassemble.model.Node;


public class NodeChangeLayoutCommand extends Command {
	
	private Node model = null;
	private Object layout = null;
	private Object oldLayout = null;
	
	public void setModel(Object model) {
		if(model instanceof Node) {
			this.model = (Node) model;
			oldLayout = ((Node) model).getLayout();
		}
	}
	
	public void setConstraint(Object constraint) {	
		this.layout = constraint;
	}
	
	@Override
	public boolean canExecute() {
		if(model == null || layout == null)
			return false;
		return true;
	}
	
	@Override
	public void execute() {
		 model.setLayout(layout);
	}
	
	@Override
	public void undo() {
		model.setLayout(oldLayout);
	}
}
