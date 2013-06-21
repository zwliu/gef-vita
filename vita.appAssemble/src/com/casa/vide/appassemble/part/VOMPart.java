package com.casa.vide.appassemble.part;

import java.beans.PropertyChangeEvent;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.casa.vide.appassemble.directedit.ExtendedDirectEditManager;
import com.casa.vide.appassemble.directedit.LabelCellEditorLocator;
import com.casa.vide.appassemble.directedit.TableNameCellEditorValidator;
import com.casa.vide.appassemble.directedit.ValidationMessageHandler;
import com.casa.vide.appassemble.editor.ValidationEnabledGraphicalViewer;
import com.casa.vide.appassemble.figure.EditableLabel;
import com.casa.vide.appassemble.figure.VOMFigure;
import com.casa.vide.appassemble.model.NodePropertySource;
import com.casa.vide.appassemble.model.VOM;
import com.casa.vide.appassemble.policy.TableDirectEditPolicy;

/**
 * VOM图元的EditPart
 *
 * @author lzw
 */
public class VOMPart extends NodePart {

	protected DirectEditManager manager;
	
	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		VOMFigure figure = new VOMFigure();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		super.createEditPolicies();	//������layout
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new TableDirectEditPolicy());
	}
	
//	@Override
//	public List<Object> getModelChildren() {
//		return new ArrayList<Object>();
//	}
	@Override
	public IFigure getContentPane() {
		VOMFigure figure = (VOMFigure)getFigure();
		return figure.getContentPane();
	}
	
	@Override
	protected void refreshVisuals() {
		VOMFigure figure = (VOMFigure)getFigure();
		VOM model = (VOM)getModel();
		figure.setInstanceName(model.getInstanceName());
		figure.setName(model.getName());
		figure.setLayout(model.getLayout());
		figure.setBackgroundColor(model.getColor());
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		String name = arg0.getPropertyName();
		if(name.equals(VOM.PROPERTY_NAME))
			refreshVisuals();
		else if(name.equals(VOM.PROPERTY_IMPORTS))
		{
			VOM model = (VOM)getModel();
			Map<String, String> imports = model.getImports();
			if(imports != null && imports.size() > 0)
			{
				String[] values = new String[imports.size()];
				Iterator<String> keyIterator = imports.keySet().iterator();
				Iterator<String> valueIterator = imports.values().iterator();
				for(int i = 0; i < imports.size(); i++)
				{
					String str = keyIterator.next();
					str += " - ";
					str += valueIterator.next();
					values[i] = str;
				}
				NodePropertySource property = (NodePropertySource)((VOM)getModel()).getAdapter(IPropertySource.class);
				for(IPropertyDescriptor des : property.getPropertyDescriptors())
					if(des.getId().equals(VOM.PROPERTY_IMPORTS) && des instanceof ComboBoxPropertyDescriptor)
					{
						des = new ComboBoxPropertyDescriptor(VOM.PROPERTY_IMPORTS, "imports", values);						
					}
			}
		}
		super.propertyChange(arg0);
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
		VOMFigure figure = (VOMFigure) getFigure();
		EditableLabel nameLabel = figure.getNameLabel();
		nameLabel.translateToRelative(requestLoc);
		if (nameLabel.containsPoint(requestLoc))
			return true;
		return false;
	}

	protected void performDirectEdit()
	{
		if (manager == null)
		{
			ValidationEnabledGraphicalViewer viewer = (ValidationEnabledGraphicalViewer) getViewer();
			ValidationMessageHandler handler = viewer.getValidationHandler();
			VOMFigure figure = (VOMFigure) getFigure();
			EditableLabel nameLabel = figure.getNameLabel();
			manager = new ExtendedDirectEditManager(this, TextCellEditor.class, new LabelCellEditorLocator(nameLabel),
					nameLabel, new TableNameCellEditorValidator(handler));
		}
		manager.show();
	}
	
	/**
	 * @param handles
	 *            the name change during an edit
	 */
	public void handleNameChange(String value)
	{
		VOMFigure tableFigure = (VOMFigure) getFigure();
		EditableLabel label = tableFigure.getNameLabel();
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
		VOMFigure tableFigure = (VOMFigure) getFigure();
		EditableLabel label = tableFigure.getNameLabel();
		VOM table = (VOM)getModel();
		label.setText(table.getName());
		label.setVisible(true);
		refreshVisuals();
	}

}
