package vita.appassemble.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;

public class AppAssembleEditor extends GraphicalEditorWithPalette {
	
	public final static String ID = "vita.appassemble.editor.AppAssembleEditor";
	
	public AppAssembleEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initializeGraphicalViewer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

}
