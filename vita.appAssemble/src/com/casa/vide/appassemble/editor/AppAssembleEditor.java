package com.casa.vide.appassemble.editor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.core.resources.IFile;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.PaletteToolbar;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.casa.vide.appassemble.factory.ImageFactory;
import com.casa.vide.appassemble.factory.NodeCreationFactory;
import com.casa.vide.appassemble.factory.PartCreationFactory;
import com.casa.vide.appassemble.factory.TreePartCreationFactory;
import com.casa.vide.appassemble.imageLibrary.ImageLibrary;
import com.casa.vide.appassemble.model.APP;
import com.casa.vide.appassemble.model.Node;
import com.casa.vide.appassemble.model.VOM;
import com.casa.vide.appassemble.model.VitaEvent;


public class AppAssembleEditor extends GraphicalEditorWithFlyoutPalette {
	
	public final static String ID = "vita.appassemble.editor.AppAssembleEditor";
	private Node content = null;
	private KeyHandler keyHandler = null;
	
	protected class OutlinePage extends ContentOutlinePage{ 
		
		private ScrollableThumbnail thumbnail; 
		private DisposeListener disposeListener;
		
		private SashForm sash; public OutlinePage() { 
			super(new TreeViewer()); 
		} 
		
		public void createControl(Composite parent) { 
			sash = new SashForm(parent, SWT.VERTICAL); 
			getViewer().createControl(sash); 
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new TreePartCreationFactory()); 
			getViewer().setContents(content);
			getSelectionSynchronizer().addViewer(getViewer()); 
			
			Canvas canvas = new Canvas(sash, SWT.BORDER);
			LightweightSystem lws = new LightweightSystem(canvas);
			thumbnail = new ScrollableThumbnail((Viewport)((ScalableRootEditPart)getGraphicalViewer().getRootEditPart()).getFigure());
			thumbnail.setSource(((ScalableRootEditPart)getGraphicalViewer().getRootEditPart()).getLayer(LayerConstants.PRINTABLE_LAYERS));
			lws.setContents(thumbnail);
			disposeListener = new DisposeListener() { 
				@Override 
				public void widgetDisposed(DisposeEvent e) { 
					if (thumbnail != null) { 
						thumbnail.deactivate(); 
						thumbnail = null; 
					} 
				} 
			};
			getGraphicalViewer().getControl().addDisposeListener(disposeListener);
		} 
		
		public void init(IPageSite pageSite) { 
			super.init(pageSite); // On hook les actions de l'editeur sur la toolbar
			IActionBars bars = getSite().getActionBars(); 
			bars.setGlobalActionHandler(ActionFactory.UNDO.getId(), getActionRegistry().getAction(ActionFactory.UNDO.getId())); 
			bars.setGlobalActionHandler(ActionFactory.REDO.getId(), getActionRegistry().getAction(ActionFactory.REDO.getId())); 
			bars.setGlobalActionHandler(ActionFactory.DELETE.getId(), getActionRegistry().getAction(ActionFactory.DELETE.getId())); 
			bars.updateActionBars(); // On associe les raccourcis clavier de l'editeur a l'outline 
			getViewer().setKeyHandler(keyHandler); 
		} 
		
		public Control getControl() { 
			return sash; 
		} 
		
		public void dispose() {
			getSelectionSynchronizer().removeViewer(getViewer()); 
			if (getGraphicalViewer().getControl() != null && !getGraphicalViewer().getControl().isDisposed()) 
				getGraphicalViewer().getControl().removeDisposeListener(disposeListener);
			super.dispose(); 
		} 
	}

	
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
		
		//PaletteSeparator separator2 = new PaletteSeparator();
		//root.add(separator2);
		
		//PaletteGroup nodeGroup = new PaletteGroup("Creation d'elements");
		PaletteDrawer nodeGroup = new PaletteDrawer("模型");
		root.add(nodeGroup);
		nodeGroup.add(new CombinedTemplateCreationEntry("APP", "Create APP type", APP.class, new NodeCreationFactory(APP.class), null, null));
		nodeGroup.add(new CombinedTemplateCreationEntry("VOM", "Create VOM type", VOM.class, new NodeCreationFactory(VOM.class), null, null));
		
		//PaletteSeparator separator3 = new PaletteSeparator();
		//root.add(separator3);
		
		PaletteDrawer imageLib = new PaletteDrawer(ImageLibrary.label);
		root.add(imageLib);		
		for(File file : ImageLibrary.getImages()) {			
			Image img = new Image(Display.getDefault(), file.getPath());
			Image small = new Image(null, img.getImageData().scaledTo(20, 20));
			Image large = new Image(null, img.getImageData().scaledTo(40, 40));
			ImageDescriptor smallIcon = ImageDescriptor.createFromImage(small);
			ImageDescriptor largeIcon = ImageDescriptor.createFromImage(large);
			imageLib.add(new CombinedTemplateCreationEntry(file.getName(), null, img, new ImageFactory(img), smallIcon, largeIcon));
		}
		
		root.setDefaultEntry(selectionToolEntry);
		return root;
	}
	
	@Override
	public PaletteViewerProvider createPaletteViewerProvider() {
		return new MenuPaletteProvider(getEditDomain());
	}
	
	//@Override
	protected void initializePaletteViewer() {
		//super.initializePaletteViewer();
		//getPaletteViewer().addDragSourceListener( new TemplateTransferDragSourceListener(getPaletteViewer()));
		PaletteViewer paletteView = getEditDomain().getPaletteViewer();
		paletteView.addDragSourceListener(new TemplateTransferDragSourceListener(paletteView));
	}

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		initializePaletteViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		content = new Node();
		APP app = new APP("APP ONE");
		VOM vom = new VOM("VOM ONE");
		VitaEvent event = new VitaEvent("Event one");
		app.addChild(vom);
		content.addChild(app);
		content.addChild(event);
		app.setLayout(new Rectangle(20, 20, -1, 150));
		vom.setLayout(new Rectangle(20, 20, -1,-1));
		event.setLayout(new Rectangle(100, 100, 100, 100));
		viewer.setContents(content);
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
		
		keyHandler = new KeyHandler();
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
		else if(type == IContentOutlinePage.class)
			return new OutlinePage();
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
		toXML(monitor, new File("E:/1.xml"));
		getCommandStack().markSaveLocation(); 
	}
	
	public void toPicture(GraphicalViewer viewer, String location, int format) {
		try{
			File file = new File(location);
			if(file.exists()) {
				if(!MessageDialog.openQuestion(null, "保存图片", "图片已存在，是否覆盖？")) {
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
	
	public void toXML(IProgressMonitor monitor, final File file) {
		SafeRunner.run(new SafeRunnable() {

			@Override
			public void run() throws Exception {
				Document document = DocumentHelper.createDocument();
				document.addElement("Scenario");
				try {
					OutputFormat format = OutputFormat.createPrettyPrint();
					XMLWriter writer = new XMLWriter(new FileOutputStream(file),format);
					writer.write(document);
					writer.close();
				}catch (Exception e) {
					e.printStackTrace();
				}		 
			}

		});
	}

}
