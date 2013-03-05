package vita.appassemble.editor;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.core.resources.IFile;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;

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
		ScalableRootEditPart rootEditPart = new ScalableRootEditPart(); 
		viewer.setRootEditPart(rootEditPart);
		
		double[] zoomLevels = new double[] {0.25, 0.5, 0.75, 1.0, 1.5, 2.0, 2.5, 3.0, 4.0, 5.0, 10.0, 20.0};
		ArrayList<String> zoomContributions = new ArrayList<String>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		ZoomManager manager = rootEditPart.getZoomManager();
		manager.setZoomLevels(zoomLevels);
		manager.setZoomLevelContributions(zoomContributions);
		getActionRegistry().registerAction(new ZoomInAction(manager));
		getActionRegistry().registerAction(new ZoomOutAction(manager));
		
		KeyHandler keyHandler = new KeyHandler();
		keyHandler.put(KeyStroke.getPressed((char)26, (int)'z', SWT.CTRL), getActionRegistry().getAction(ActionFactory.UNDO.getId()));
		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		keyHandler.put(KeyStroke.getPressed('+', SWT.KEYPAD_ADD, 0), getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		keyHandler.put(KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, 0), getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));

		viewer.setProperty( MouseWheelHandler.KeyGenerator.getKey(SWT.NONE), MouseWheelZoomHandler.SINGLETON); 
		viewer.setKeyHandler(keyHandler);
	}
	
	@Override
	public Object getAdapter(Class type) {
		if(type == ZoomManager.class)
			return ((ScalableRootEditPart)getGraphicalViewer().getRootEditPart()).getZoomManager();
		return super.getAdapter(type);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
//		IFile file = ((IFileEditorInput)getEditorInput()).getFile();
//		File currentFile = file.getLocation().toFile();	 
//		String fileName = currentFile.getName(); 		
//		System.out.println(file.getProject().getFullPath().append(fileName+".png").toPortableString());
		toPicture(getGraphicalViewer(), "E:/1.png", SWT.IMAGE_PNG);
	}
	
	static public void toPicture(GraphicalViewer viewer, String location, int format) {
		try{
			File file = new File(location);
			if(file.exists()) {
				if(!MessageDialog.openQuestion(null, "保存图片", "该文件已经存在，是否覆盖它？")) {
					return;
				}
			}
			else
				file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			
			IFigure figure = ((AbstractGraphicalEditPart)viewer.getRootEditPart()).getFigure();
			if(figure instanceof Viewport)
				((Viewport)figure).setViewLocation(0, 0);
			Dimension size = figure.getPreferredSize();
			Image image = new Image(Display.getDefault(), size.width, size.height);
			GC gc = new GC(image);
			SWTGraphics graphics = new SWTGraphics(gc);   
			figure.paint(graphics);
			ImageLoader loader = new ImageLoader();   
			loader.data = new ImageData[] {image.getImageData()};   
			loader.save(fos, format);   
			fos.close();  
		}catch(Exception e) {
			e.printStackTrace();   
		}
	}

}
