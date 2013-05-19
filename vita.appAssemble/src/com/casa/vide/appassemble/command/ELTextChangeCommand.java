package com.casa.vide.appassemble.command;

import org.eclipse.gef.commands.Command;

import com.casa.vide.appassemble.model.EditableLabelModel;

public class ELTextChangeCommand extends Command {

	private EditableLabelModel el;
	private String text, oldText;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute()
	{
		el.setText(text);
	}

	/**
	 * @return whether we can apply changes
	 */
	public boolean canExecute()
	{
		if (text != null)
		{
			return true;
		}
		else
		{
			text = oldText;
			return false;
		}
	}

	/**
	 * Sets the new Column text
	 * 
	 * @param string
	 *            the new text
	 */
	public void setText(String string)
	{
		this.text = string;
	}

	/**
	 * Sets the old Column text
	 * 
	 * @param string
	 *            the old text
	 */
	public void setOldText(String string)
	{
		oldText = string;
	}

	/**
	 * @param table
	 *            The table to set.
	 */
	public void setEL(EditableLabelModel el)
	{
		this.el = el;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo()
	{
		el.setText(oldText);
	}
	
}
