package com.casa.vide.appassemble.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Image;

import com.casa.vide.appassemble.model.APP;

public class NodeChangeBackgroundCommand extends Command {

	private APP app;
	private Image background;
	private Image oldBackground;

	public void setNode(APP app) {
		oldBackground = app.getBackground();
		this.app = app;
	}

	public void setBackground(Image background) {
		this.background = background;
	}
	
	@Override
	public boolean canExecute() {
		if(app == null || background == null)
			return false;
		return true;
	}
	
	@Override
	public void execute() {
		app.setBackground(background);
	}
	
	@Override
	public void undo() {
		app.setBackground(oldBackground);
	}
}
