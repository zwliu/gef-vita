package vita.appassemble.treepart;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import vita.appassemble.model.APP;
import vita.appassemble.model.Node;
import vita.appassemble.model.VOM;

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
			setWidgetText(model.getName()); 
			setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT));
		}
		if(obj instanceof VOM) {
			VOM model = (VOM)obj;
			setWidgetText(model.getName()); 
			setWidgetImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEF_VIEW));
		}
	}

}
