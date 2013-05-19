package com.casa.vide.appassemble.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Image;

import com.casa.vide.appassemble.model.Shape;

public class NodeChangeBackgroundCommand extends Command {

	private Shape shape;
	private Image background;
	private Image oldBackground;

	public void setNode(Shape shape) {
		oldBackground = shape.getBackground();
		this.shape = shape;
	}

	public void setBackground(Image background) {
		this.background = background;
	}
	
	@Override
	public boolean canExecute() {
		if(shape == null || background == null)
			return false;
		return true;
	}
	
	@Override
	public void execute() {
		shape.setBackground(background);
	}
	
	@Override
	public void undo() {
		shape.setBackground(oldBackground);
	}
}
