package com.casa.vide.appassemble.part;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;

import com.casa.vide.appassemble.directedit.ExtendedDirectEditManager;
import com.casa.vide.appassemble.directedit.LabelCellEditorLocator;
import com.casa.vide.appassemble.directedit.TableNameCellEditorValidator;
import com.casa.vide.appassemble.directedit.ValidationMessageHandler;
import com.casa.vide.appassemble.editor.ValidationEnabledGraphicalViewer;
import com.casa.vide.appassemble.figure.EditableLabel;
import com.casa.vide.appassemble.model.EditableLabelModel;
import com.casa.vide.appassemble.policy.EditableLabelDirectEditPolicy;

public class EditableLabelPart extends NodePart {

	protected DirectEditManager manager;
	
	@Override
	protected IFigure createFigure() {
		IFigure figure = new EditableLabel("描述");
		return figure;
	}
	
	@Override
	protected void refreshVisuals() {
		EditableLabel figure = (EditableLabel)getFigure();
		EditableLabelModel model = (EditableLabelModel)getModel();
		figure.setText(model.getText());
		figure.setLayout(model.getLayout());
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		String name = arg0.getPropertyName();
		if(name.equals(EditableLabelModel.PROPERTY_TEXT))
			refreshVisuals();
		super.propertyChange(arg0);
	}
	
	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		super.createEditPolicies();	//������layout
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new EditableLabelDirectEditPolicy());
	}
	
	@Override
	public void performRequest(Request request)
	{
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT)
		{
			if (request instanceof DirectEditRequest
					&& !directEditHitTest(((DirectEditRequest) request).getLocation().getCopy()))
				return;
			performDirectEdit();
		}
	}
	
	private boolean directEditHitTest(Point requestLoc)
	{
		EditableLabel label = (EditableLabel) getFigure();
		label.translateToRelative(requestLoc);
		if (label.containsPoint(requestLoc))
			return true;
		return false;
	}

	protected void performDirectEdit()
	{
		if (manager == null)
		{
			ValidationEnabledGraphicalViewer viewer = (ValidationEnabledGraphicalViewer) getViewer();
			ValidationMessageHandler handler = viewer.getValidationHandler();
			EditableLabel label = (EditableLabel) getFigure();
			manager = new ExtendedDirectEditManager(this, TextCellEditor.class, new LabelCellEditorLocator(label),
					label, new TableNameCellEditorValidator(handler));
		}
		manager.show();
	}
	
	/**
	 * @param handles
	 *            the name change during an edit
	 */
	public void handleNameChange(String value)
	{
		EditableLabel label = (EditableLabel) getFigure();
		label.setVisible(false);
		refreshVisuals();
	}

	/**
	 * Reverts to existing name in model when exiting from a direct edit
	 * (possibly before a commit which will result in a change in the label
	 * value)
	 */
	public void revertNameChange()
	{
		EditableLabel label = (EditableLabel) getFigure();
		EditableLabelModel model = (EditableLabelModel)getModel();
		label.setText(model.getText());
		label.setVisible(true);
		refreshVisuals();
	}
	
}
