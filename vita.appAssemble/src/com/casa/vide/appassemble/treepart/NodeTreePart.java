package com.casa.vide.appassemble.treepart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.casa.vide.appassemble.model.APP;
import com.casa.vide.appassemble.model.Node;
import com.casa.vide.appassemble.model.VOM;


public class NodeTreePart extends AbstractTreeEditPart implements PropertyChangeListener {
	
	@Override
	public void activate() {
		super.activate();
		((Node)getModel()).addPropertyChangeListener(this);
	}
	
	@Override
	public void deactivate() {
		super.deactivate();
		((Node)getModel()).removePropertyChangeListener(this);
	}
	
	@Override
	public List<Object> getModelChildren() {
		return ((Node)getModel()).getChildren();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		String name = arg0.getPropertyName();
		if(name == Node.PROPERTY_ADD || name == Node.PROPERTY_REMOVE) {
			refreshChildren();
		}
		else if(name == Node.PROPERTY_LAYOUT) {
			refreshVisuals();
		}
	}
	
	public void refreshVisuals(){ 
		Object obj = getModel(); 
		if(obj instanceof APP) {
			APP model = (APP)obj;
			setWidgetText(model.getName()); //参数不为null
			setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
		}
		if(obj instanceof VOM) {
			VOM model = (VOM)obj;
			setWidgetText(model.getName());   //参数不为null
			setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEF_VIEW));
		}
	}
	
	@Override
	public DragTracker getDragTracker(Request req) {
		return new SelectEditPartTracker(this);
	}
	
	@Override
	public void performRequest(Request req) {
		if(req.getType().equals(RequestConstants.REQ_OPEN)) {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			try {
				page.showView(IPageLayout.ID_PROP_SHEET);
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
