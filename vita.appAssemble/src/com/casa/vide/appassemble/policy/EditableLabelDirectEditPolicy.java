package com.casa.vide.appassemble.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.jface.viewers.CellEditor;

import com.casa.vide.appassemble.command.ELTextChangeCommand;
import com.casa.vide.appassemble.model.EditableLabelModel;
import com.casa.vide.appassemble.part.EditableLabelPart;

public class EditableLabelDirectEditPolicy extends DirectEditPolicy {
	
	private String oldValue;

	/**
	 * @see DirectEditPolicy#getDirectEditCommand(org.eclipse.gef.requests.DirectEditRequest)
	 */
	protected Command getDirectEditCommand(DirectEditRequest request)
	{
		ELTextChangeCommand cmd = new ELTextChangeCommand();
		EditableLabelModel model = (EditableLabelModel) getHost().getModel();
		cmd.setEL(model);
		cmd.setOldText(model.getText());
		CellEditor cellEditor = request.getCellEditor();
		cmd.setText((String) cellEditor.getValue());
		return cmd;
	}

	/**
	 * @see DirectEditPolicy#showCurrentEditValue(org.eclipse.gef.requests.DirectEditRequest)
	 */
	protected void showCurrentEditValue(DirectEditRequest request)
	{
		String value = (String) request.getCellEditor().getValue();
		EditableLabelPart Part = (EditableLabelPart) getHost();
		Part.handleNameChange(value);
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
		EditableLabelPart part = (EditableLabelPart) getHost();
		part.revertNameChange();
	}
}
