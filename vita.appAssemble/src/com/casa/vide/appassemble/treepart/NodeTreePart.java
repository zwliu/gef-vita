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
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.casa.vide.appassemble.config.IconConstant;
import com.casa.vide.appassemble.model.APP;
import com.casa.vide.appassemble.model.Message;
import com.casa.vide.appassemble.model.Node;
import com.casa.vide.appassemble.model.VIO;
import com.casa.vide.appassemble.model.VOM;

/**
 * Outline中树形缩进图的TreeEditPart
 *
 * @author lzw
 */
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
	
	/**
	 * 设置各图元的文字和图标
	 */
	public void refreshVisuals(){ 
		Object obj = getModel(); 
		if(obj instanceof APP) {
			APP model = (APP)obj;
			setWidgetText(model.getName()); //参数不为null
			setWidgetImage(IconConstant.appDesSm.createImage());
		}
		else if(obj instanceof VOM) {
			VOM model = (VOM)obj;
			setWidgetText(model.getInstanceName());   //参数不为null
			setWidgetImage(IconConstant.vomDesSm.createImage());
		}
		else if(obj instanceof VIO) {
			VIO model = (VIO)obj;
			setWidgetText(model.getInstanceName());
			setWidgetImage(IconConstant.vioDesSm.createImage());
		}
		else if(obj instanceof Message) {
			Message model = (Message)obj;
			setWidgetText(model.getInstanceName());
			setWidgetImage(IconConstant.messageDesSm.createImage());
		}
	}
	
	@Override
	public DragTracker getDragTracker(Request req) {
		return new SelectEditPartTracker(this);
	}
	
	@Override
	public void performRequest(Request req) {
		if(req.getType().equals(RequestConstants.REQ_OPEN)) {  //双击打开属性页
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
