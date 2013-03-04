package vita.appassemble.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;

import vita.appassemble.factory.NodeCreationFactory;
import vita.appassemble.factory.PartCreationFactory;
import vita.appassemble.model.APP;
import vita.appassemble.model.Node;
import vita.appassemble.model.VOM;

public class AppAssembleEditor extends GraphicalEditorWithPalette {
	
	public final static String ID = "vita.appassemble.editor.AppAssembleEditor";
	
	public AppAssembleEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		// TODO Auto-generated method stub
		PaletteRoot root = new PaletteRoot();
		
		PaletteGroup manipGroup = new PaletteGroup("Manipulation d'objets");
		SelectionToolEntry selectionToolEntry = new SelectionToolEntry();
		manipGroup.add(selectionToolEntry);
		manipGroup.add(new MarqueeToolEntry());
		root.add(manipGroup);
		
		PaletteSeparator separator2 = new PaletteSeparator();
		root.add(separator2);
		
		PaletteGroup nodeGroup = new PaletteGroup("Creation d'elements");
		root.add(nodeGroup);
		nodeGroup.add(new CombinedTemplateCreationEntry("APP", "Create APP type", APP.class, new NodeCreationFactory(APP.class), null, null));
		nodeGroup.add(new CombinedTemplateCreationEntry("VOM", "Create VOM type", VOM.class, new NodeCreationFactory(VOM.class), null, null));
		
		root.setDefaultEntry(selectionToolEntry);
		return root;
	}
	
	@Override
	protected void initializePaletteViewer() {
		super.initializePaletteViewer();
		getPaletteViewer().addDragSourceListener( new TemplateTransferDragSourceListener(getPaletteViewer()));
	}

	@Override
	protected void initializeGraphicalViewer() {
		// TODO Auto-generated method stub
		GraphicalViewer viewer = getGraphicalViewer();
		Node node = new Node();
		APP app = new APP("APP ONE");
		VOM vom = new VOM("VOM ONE");
		app.addChild(vom);
		node.addChild(app);
		app.setLayout(new Rectangle(20, 20, -1, 150));
		vom.setLayout(new Rectangle(20, 20, -1,-1));
		viewer.setContents(node);
		viewer.addDropTargetListener(new MyTemplateTransferDropTargetListener(viewer));
	}
	
	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new PartCreationFactory());
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

}
