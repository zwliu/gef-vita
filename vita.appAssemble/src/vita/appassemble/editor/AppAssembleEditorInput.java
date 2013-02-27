package vita.appassemble.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class AppAssembleEditorInput implements IEditorInput {

	public String name = null;
	
	public AppAssembleEditorInput(String name) {
		this.name =name;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof AppAssembleEditorInput))
		return false;
		return ((AppAssembleEditorInput)o).getName().equals(getName());
	}
	
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return (name != null);
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return  ImageDescriptor.getMissingImageDescriptor();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return this.name;
	}

}
