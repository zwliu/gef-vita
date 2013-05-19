package com.casa.vide.appassemble.editor;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.ActionFactory;

import com.casa.vide.appassemble.action.ToolBarSaveAction;
import com.casa.vide.appassemble.directedit.StatusLineValidationMessageHandler;
import com.casa.vide.appassemble.factory.ExpNodeCreationFactory;
import com.casa.vide.appassemble.factory.ExpPartCreationFactory;
import com.casa.vide.appassemble.factory.ImageFactory;
import com.casa.vide.appassemble.imageLibrary.ImageLibrary;
import com.casa.vide.appassemble.model.EditableLabelModel;
import com.casa.vide.appassemble.model.Node;
import com.casa.vide.appassemble.model.Shape;
import com.casa.vide.appassemble.model.Shape.ShapeType;

public class ExpSchemeEditor extends GraphicalEditorWithFlyoutPalette {

	public static String ID = "vita.appassemble.editor.ExpSchemeEditor";
	private KeyHandler keyHandler = null;
	
	public ExpSchemeEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}
	
	/**
	 * 初始化Palette，添加需要的工具
	 */
	@Override
	protected PaletteRoot getPaletteRoot() {
		PaletteRoot root = new PaletteRoot();
		//添加操作工具
		PaletteDrawer manipulateDrawer = new PaletteDrawer("操作");
		SelectionToolEntry selectionToolEntry = new SelectionToolEntry();
		manipulateDrawer.add(selectionToolEntry);
		manipulateDrawer.add(new MarqueeToolEntry());
		root.add(manipulateDrawer);
		//添加图形工具
		PaletteDrawer shapesDrawer = new PaletteDrawer("图形");
		shapesDrawer.add(new CombinedTemplateCreationEntry("矩形", null, Shape.class, new ExpNodeCreationFactory(ShapeType.TYPE_RECTANGLE), null, null));
		shapesDrawer.add(new CombinedTemplateCreationEntry("标注", null, EditableLabelModel.class, new ExpNodeCreationFactory(EditableLabelModel.class), null, null));
		root.add(shapesDrawer);
		//添加连线工具
		PaletteDrawer connectionsDrawer = new PaletteDrawer("连线");
		connectionsDrawer.add(new ConnectionCreationToolEntry("连线", null, null, null, null));
		root.add(connectionsDrawer);
		//添加图片库
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
	protected void createGraphicalViewer(Composite parent) {
		ValidationEnabledGraphicalViewer viewer = new ValidationEnabledGraphicalViewer(new StatusLineValidationMessageHandler(getEditorSite()));
		viewer.createControl(parent);
		setGraphicalViewer(viewer);
		configureGraphicalViewer();
		hookGraphicalViewer();
		initializeGraphicalViewer();
	}
	
	@Override
	public void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		Node node = new Node();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(node);
	}
	
	@Override
	public void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new ExpPartCreationFactory());
		
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
	public void doSave(IProgressMonitor monitor) {
		
	}
	
	@Override
	public void createActions() {
		super.createActions();
		ActionRegistry registry = getActionRegistry();
		ToolBarSaveAction saveAction = new ToolBarSaveAction(this);
		registry.registerAction(saveAction);//new AppAssembleSaveAction(this));
		this.getCommandStack().addCommandStackListener(saveAction);
	}
	
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
		if(type == ZoomManager.class)
			return ((ScalableRootEditPart)getGraphicalViewer().getRootEditPart()).getZoomManager();
		return super.getAdapter(type);
	}

}
