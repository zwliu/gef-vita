package vita.appassemble.editor;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

import vita.appassemble.factory.NodeCreationFactory;
import vita.appassemble.model.Node;

public class MyTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {

	public MyTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public CreationFactory getFactory(Object template) {
		return new NodeCreationFactory((Class<? extends Node>)template);
	}
	
}
