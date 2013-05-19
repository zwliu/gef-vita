/*
 * Created on Jul 18, 2004
 */
package com.casa.vide.appassemble.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import com.casa.vide.appassemble.command.ChangeTableNameCommand;
import com.casa.vide.appassemble.model.VOM;
import com.casa.vide.appassemble.part.VOMPart;

/**
 * EditPolicy for the direct editing of table names
 * 
 * @author Phil Zoio
 */
public class TableDirectEditPolicy extends DirectEditPolicy
{

	private String oldValue;

	/**
	 * @see DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
	 */
	protected Command getDirectEditCommand(DirectEditRequest request)
	{
		ChangeTableNameCommand cmd = new ChangeTableNameCommand();
		VOM table = (VOM) getHost().getModel();
		cmd.setTable(table);
		cmd.setOldName(table.getName());
		CellEditor cellEditor = request.getCellEditor();
		cmd.setName((String) cellEditor.getValue());
		return cmd;
	}

	/**
	 * @see DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
	 */
	protected void showCurrentEditValue(DirectEditRequest request)
	{
		String value = (String) request.getCellEditor().getValue();
		VOMPart tablePart = (VOMPart) getHost();
		tablePart.handleNameChange(value);
	}

	/**
	 * @param to
	 *            Saves the initial text value so that if the user's changes are not committed then 
	 */
	protected void storeOldEditValue(DirectEditRequest request)
	{
		
		CellEditor cellEditor = request.getCellEditor();
		oldValue = (String) cellEditor.getValue();
	}

	/**
	 * @param request
	 */
	protected void revertOldEditValue(DirectEditRequest request)
	{
		CellEditor cellEditor = request.getCellEditor();
		cellEditor.setValue(oldValue);
		VOMPart tablePart = (VOMPart) getHost();
		tablePart.revertNameChange();
	}
}