package com.casa.vide.appassemble.editor;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.ContextMenuProvider;
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
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.SaveAction;
import org.eclipse.gef.ui.actions.SelectAllAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.casa.vide.appassemble.action.ToolBarSaveAction;
import com.casa.vide.appassemble.action.ImplementAction;
import com.casa.vide.appassemble.action.PublishAction;
import com.casa.vide.appassemble.action.SubscribeAction;
import com.casa.vide.appassemble.directedit.StatusLineValidationMessageHandler;
import com.casa.vide.appassemble.factory.NodeCreationFactory;
import com.casa.vide.appassemble.factory.PartCreationFactory;
import com.casa.vide.appassemble.factory.TreePartCreationFactory;
import com.casa.vide.appassemble.model.APP;
import com.casa.vide.appassemble.model.Message;
import com.casa.vide.appassemble.model.Node;
import com.casa.vide.appassemble.model.Scenario;
import com.casa.vide.appassemble.model.VIO;
import com.casa.vide.appassemble.model.VOM;
import com.casa.vide.appassemble.modelinterface.IAPP;
import com.casa.vide.appassemble.modelinterface.IBasicElement;
import com.casa.vide.appassemble.modelinterface.IVOM;
import com.casa.vide.appassemble.modelinterface.IElement.ModelEventType;


public class AppAssembleEditor extends GraphicalEditorWithFlyoutPalette {
	
	public final static String ID = "vita.appassemble.editor.AppAssembleEditor";
	private Node content;
	private KeyHandler keyHandler;
	private String file;
	private  Scenario scenario;
	
	protected class OutlinePage extends ContentOutlinePage{ 
		
		private ScrollableThumbnail thumbnail; 
		private DisposeListener disposeListener;
		private SashForm sash; 		
		
		public OutlinePage() { 
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
			
			ContextMenuProvider provider = new AppAssembleContextMenuProvider(getViewer(), getActionRegistry());
			getViewer().setContextMenu(provider);
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
		content = new Node();
		keyHandler = new KeyHandler();
		file = "E:/1.xml";
		load(new File(file));
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
		nodeGroup.add(new CombinedTemplateCreationEntry("VIO", "Create VIO type", VIO.class, new NodeCreationFactory(VIO.class), null, null));
		nodeGroup.add(new CombinedTemplateCreationEntry("Message", "Create Message type", Message.class, new NodeCreationFactory(Message.class), null, null));
		
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
		
		keyHandler.put(KeyStroke.getPressed((char)26, (int)'z', SWT.CTRL), getActionRegistry().getAction(ActionFactory.UNDO.getId()));
		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		keyHandler.put(KeyStroke.getPressed('+', SWT.KEYPAD_ADD, 0), getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		keyHandler.put(KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, 0), getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
		keyHandler.put(KeyStroke.getPressed((char)1, (int)'a', SWT.CTRL), getActionRegistry().getAction(ActionFactory.SELECT_ALL.getId()));

		viewer.setProperty( MouseWheelHandler.KeyGenerator.getKey(SWT.NONE), MouseWheelZoomHandler.SINGLETON); 
		viewer.setKeyHandler(keyHandler);
		
		ContextMenuProvider provider = new AppAssembleContextMenuProvider(viewer, getActionRegistry());
		viewer.setContextMenu(provider);
	}
	
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class type) {
		if(type == ZoomManager.class)
			return ((ScalableRootEditPart)getGraphicalViewer().getRootEditPart()).getZoomManager();
		else if(type == IContentOutlinePage.class)
			return new OutlinePage();
		return super.getAdapter(type);
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
	public void createActions() {
		super.createActions();
		ActionRegistry registry = getActionRegistry();
		List selectionActions = getSelectionActions();
		IAction action;
		action = new ImplementAction(this); 
		registry.registerAction(action);
		selectionActions.add(action.getId());
		action = new SubscribeAction(this); 
		registry.registerAction(action);
		selectionActions.add(action.getId());
		action = new PublishAction(this); 
		registry.registerAction(action);
		selectionActions.add(action.getId());
		ToolBarSaveAction saveAction = new ToolBarSaveAction(this);
		registry.registerAction(saveAction);//new AppAssembleSaveAction(this));
		this.getCommandStack().addCommandStackListener(saveAction);
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
//		IFile file = ((IFileEditorInput)getEditorInput()).getFile();
//		File currentFile = file.getLocation().toFile();	 
//		String fileName = currentFile.getName(); 		
//		System.out.println(file.getProject().getFullPath().append(fileName+".png").toPortableString());
		//toPicture(getGraphicalViewer(), "E:/1.png", SWT.IMAGE_PNG);
		freshScenario();
		toXML(monitor);
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
	
	/**
	 * 将建模结果保存为XML文件
	 * @param monitor 进度模拟器
	 */
	public void toXML(IProgressMonitor monitor) {
		SafeRunner.run(new SafeRunnable() {
			@Override
			public void run() throws Exception {
				Document document = DocumentHelper.createDocument();
				Element eleScenario = document.addElement("Scenario");
				Map<String, IVOM> voms = scenario.getVOMs();
				eleScenario.addAttribute("name", eleScenario.getName());
				//添加VOM
				if(voms != null){// && voms.size() > 0) {
					Element eleVOMs = eleScenario.addElement("VOMs");
					eleVOMs.addAttribute("vom_number", String.valueOf(voms.size()));
					for(IVOM vom : voms.values()) {
						Element eleVOM = eleVOMs.addElement("VOM");
						eleVOM.addAttribute("vom_name", vom.getName());
						eleVOM.addAttribute("instance_name", vom.getInstanceName());
						eleVOM.addAttribute("vdl_name", ((VOM)vom).getVdlName());
						//添加set<String> vios、messages
						Set<String> vios = ((VOM)vom).getVIOs();
						StringBuffer strVios = new StringBuffer();
						if(vios != null) {
							for(String str : vios) {
								strVios.append(str);
								strVios.append("; ");
							}
						}
						eleVOM.addAttribute("vios", strVios.toString());
						Set<String> messages = ((VOM)vom).getMessages();
						StringBuffer strMessages = new StringBuffer();
						if(messages != null) {
							for(String str : messages) {
								strMessages.append(str);
								strMessages.append("; ");
							}
						}
						eleVOM.addAttribute("messages", strMessages.toString());
						//添加imports
						Map<String, String> imports = ((VOM)vom).getImports();
						StringBuffer strImports = new StringBuffer();
						if(imports != null) {
							for(String key : imports.keySet()) {
								strImports.append(key);
								strImports.append(":");
								strImports.append(imports.get(key));
								strImports.append("; ");
							}
						}
						eleVOM.addAttribute("imports", strImports.toString());
						//eleVOM.add
						//额外的位置信息、颜色信息
						setXMLInfoOfNode(eleVOM, (Node)vom);
						//eleVOM.addAttribute("color", ((VOM)vom).getColor());
						//添加vio、message
						setXMLInfoOfBasicElements(eleVOM, vom.getVIOIns(), "VIOIns", "vio_number", "VIO", "name");
						setXMLInfoOfBasicElements(eleVOM, vom.getVIOOuts(), "VIOOuts", "vio_number", "VIO", "name");
						setXMLInfoOfBasicElements(eleVOM, vom.getMessageIns(), "MessageIns", "Inmess_number", "Message", "message_name");
						setXMLInfoOfBasicElements(eleVOM, vom.getMessageOuts(), "MessageOuts", "Outmess_number", "Message", "message_name");
					}
				}
				//添加APP
				Vector<IAPP> apps = scenario.getAPPs();
				if(apps != null) {
					Element eleAPPs = eleScenario.addElement("APPs");
					eleAPPs.addAttribute("app_number", String.valueOf(apps.size()));
					for(IAPP app : apps) {
						Element eleAPP = eleAPPs.addElement("App");
						eleAPP.addAttribute("name", app.getName());
						eleAPP.addAttribute("vom_number", String.valueOf(app.getVomNames().size()));
						for(String name : app.getVomNames()) {
							Element eleVom = eleAPP.addElement("VOM");
							eleVom.addAttribute("vom_name", name);
						}
						Element eleMonitor = eleAPP.addElement("Monitor");
						eleMonitor.addAttribute("isopened", app.getMonitorOpen());
						Element eleTimeReg = eleAPP.addElement("TimeRegulation");
						eleTimeReg.addAttribute("flag", app.getTimeRegulation());
						setXMLInfoOfNode(eleAPP, (Node)app);
					}
				}
				try {
					OutputFormat format = OutputFormat.createPrettyPrint();
					XMLWriter writer = new XMLWriter(new FileOutputStream(new File(file)),format);
					writer.write(document);
					writer.close();
				}catch (Exception e) {
					e.printStackTrace();
				}		 
			}

		});
	}
	
	private void setXMLInfoOfNode(Element eleVOM, Node node) {
		Object constraint = node.getLayout();
		if(constraint instanceof Rectangle) {
			eleVOM.addAttribute("x", String.valueOf(((Rectangle)constraint).x));
			eleVOM.addAttribute("y", String.valueOf(((Rectangle)constraint).y));
			eleVOM.addAttribute("width", String.valueOf(((Rectangle)constraint).width));
			eleVOM.addAttribute("height", String.valueOf(((Rectangle)constraint).height));
			Color color = node.getColor();
			if(color != null) {
				RGB rgb = color.getRGB();
				eleVOM.addAttribute("color",  rgb.red+","+rgb.green+","+rgb.blue);
			}
		}
	}
	
	private void getXMLInfoOfNode(Element eleVOM, Node node) {
		if(eleVOM.attribute("x") != null) {
			Rectangle rec = new Rectangle();
			try {
				rec.x = Integer.parseInt(eleVOM.attributeValue("x"));
				rec.y = Integer.parseInt(eleVOM.attributeValue("y"));
				rec.width = Integer.parseInt(eleVOM.attributeValue("width"));
				rec.height = Integer.parseInt(eleVOM.attributeValue("height"));
				node.setLayout(rec);
			}catch(NumberFormatException e) {
				e.printStackTrace();
			}
			String strColor = eleVOM.attributeValue("color");
			if(strColor != null) {
				String[] rgb = strColor.split(",");
				if(rgb.length == 3) {
					node.setColor(new Color(null, Integer.parseInt(rgb[0]), 
							Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2])));
				}
			}
		}
	}
	
	private void setXMLInfoOfBasicElements(Element eleVOM, Vector<IBasicElement> iBasicEles, 
			String elementName, String numberName, String name, String attributeName) {
		if(iBasicEles != null) {
			Element eleVIOIns = eleVOM.addElement(elementName);
			eleVIOIns.addAttribute(numberName, String.valueOf(iBasicEles.size()));
			for(IBasicElement vio : iBasicEles) {
				Element eleVIO = eleVIOIns.addElement(name);
				eleVIO.addAttribute(attributeName, vio.getName());
				eleVIO.addAttribute("instance_name", vio.getInstanceName());
				eleVIO.addAttribute("vdl_name", vio.getVdlName());
				//额外的位置信息、颜色信息
				setXMLInfoOfNode(eleVIO, (Node)vio);
			}
		}
	}
	
	private Vector<IBasicElement> getXMLInfoOfBasicElements(VOM vom, Element eleVOM, String elementName, 
			String name, String attributeName) {
		Vector<IBasicElement> iBasicEles = new Vector<IBasicElement>();
		Element ele = eleVOM.element(elementName);
		if(ele != null) {
			List<?> listEle = ele.elements(name);
			if(listEle != null) {
				IBasicElement basicEle = null;
				boolean isVIO = name.equals("VIO") ? true : false;
				ModelEventType type = elementName.contains("In") ? ModelEventType.SUBSCRIPTION : ModelEventType.PUBLISH;
				for(Iterator<?> iterator = listEle.iterator(); iterator.hasNext(); ) {
					Element e = (Element)iterator.next();
					if(isVIO) {
						basicEle = new VIO();
						((VIO)basicEle).setName(e.attributeValue(attributeName));
						((VIO)basicEle).setInstanceName(e.attributeValue("instance_name"));
						((VIO)basicEle).setVdlName(e.attributeValue("vdl_name"));
						((VIO)basicEle).setType(type);
						((VIO)basicEle).setParent(vom);
					}
					else {
						basicEle = new  Message();		
						((Message)basicEle).setName(e.attributeValue(attributeName));
						((Message)basicEle).setInstanceName(e.attributeValue("instance_name"));
						((Message)basicEle).setVdlName(e.attributeValue("vdl_name"));
						((Message)basicEle).setType(type);
						((Message)basicEle).setParent(vom);
					}
					getXMLInfoOfNode(e, (Node)basicEle);
					iBasicEles.add(basicEle);
				}
			}
		}
		return iBasicEles;
	}
	
	/**
	 * 加载XML文件格式的建模结果，转化为可视化图形化建模结果
	 * @param xmlFile  读取的XML文件
	 */
	public void load(final File xmlFile) {
		scenario = new Scenario();
		scenario.setName(xmlFile.getName());
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(xmlFile);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  //提示关闭编辑器
		}
		Element scenario = document.getRootElement();
		if(scenario != null && scenario.getName().equals("Scenario")) {
			Element eleVOMs = scenario.element("VOMs");
			Map<String, IVOM> voms = new HashMap<String, IVOM>();
			if(eleVOMs != null) {
				List<?> listVOM = eleVOMs.elements("VOM");  //读取所有VOM节点
				if(listVOM != null) {
					for(Iterator<?> iterator = listVOM.iterator(); iterator.hasNext(); ) {
						Element eleVOM = (Element)iterator.next();
						VOM tmpVOM = new VOM();
						tmpVOM.setName(eleVOM.attributeValue("vom_name"));
						tmpVOM.setInstanceName(eleVOM.attributeValue("instance_name"));
						tmpVOM.setVdlName(eleVOM.attributeValue("vdl_name"));
						//vios、messages
						Set<String> vios = new HashSet<String>();
						String strTemp = eleVOM.attributeValue("vios");
						if(strTemp != null && strTemp.contains("; ")) {
							for(String str : strTemp.split("; ")) 
								vios.add(str);
						}
						tmpVOM.setVIOs(vios);
						Set<String> messages = new HashSet<String>();
						strTemp = eleVOM.attributeValue("messages");
						if(strTemp != null && strTemp.contains("; ")) {
							for(String str : strTemp.split("; ")) 
								messages.add(str);
						}
						tmpVOM.setMessages(messages);
						//imports
						Map<String, String> imports = new HashMap<String, String>();
						strTemp = eleVOM.attributeValue("imports");
						if(strTemp != null && strTemp.contains("; ")) {
							for(String str : strTemp.split("; ")) {
								String[] keyValue = str.split(":");
								if(keyValue.length == 2) {
									imports.put(keyValue[0], keyValue[1]);
								}
								else
									System.out.print("解析VOM：：imports出错"); //对话框提示解析出错
							}
						}
						tmpVOM.setImports(imports);
						//node中信息
						getXMLInfoOfNode(eleVOM, tmpVOM);
						//message、 vio
						Vector<IBasicElement> vioIns = getXMLInfoOfBasicElements(tmpVOM, eleVOM, "VIOIns", "VIO", "name");
						Vector<IBasicElement> vioOuts = getXMLInfoOfBasicElements(tmpVOM, eleVOM, "VIOOuts", "VIO", "name");
						Vector<IBasicElement> messageIns = getXMLInfoOfBasicElements(tmpVOM, eleVOM, "MessageIns", "Message", "message_name");
						Vector<IBasicElement> messageOuts = getXMLInfoOfBasicElements(tmpVOM, eleVOM, "MessageOuts", "Message", "message_name");						
						tmpVOM.setVIOIns(vioIns);
						tmpVOM.setVIOOuts(vioOuts);
						tmpVOM.setMessageIns(messageIns);
						tmpVOM.setMessageOuts(messageOuts);
						tmpVOM.getChildren().addAll(vioIns);
						tmpVOM.getChildren().addAll(vioOuts);
						tmpVOM.getChildren().addAll(messageIns);
						tmpVOM.getChildren().addAll(messageOuts);
						voms.put(tmpVOM.getName(), tmpVOM);						
					}
				}
			}
			Element eleAPPs = scenario.element("APPs");
			if(eleAPPs != null) {
				List<?> listApp = eleAPPs.elements("App");
				if(listApp != null) {
					for(Iterator<?> iterator = listApp.iterator(); iterator.hasNext(); ) {
						APP app = new APP();
						Element eleApp = (Element)iterator.next();
						app.setName(eleApp.attributeValue("name"));
						Element eleMonitor = eleApp.element("Monitor");
						if(eleMonitor != null)
							app.setMonitorOpen(eleMonitor.attributeValue("isopened"));
						Element eleTimeReg = eleApp.element("TimeRegulation");
						if(eleTimeReg != null)
							app.setTimeRegulation(eleTimeReg.attributeValue("flag"));
						List<?> listVom = eleApp.elements("VOM");
						if(listVom != null) {
							for(Iterator<?> iter = listVom.iterator(); iter.hasNext(); ) {
								Element eleVom = (Element)iter.next();
								VOM vom = (VOM)voms.get(eleVom.attributeValue("vom_name"));
								if(vom != null)
									app.addChild(vom);
							}
						}
						getXMLInfoOfNode(eleApp, app);
						content.addChild(app);
					}
				}
			}
		}
		else {
			System.out.print("XML file is not valid");  //XML文件不合法，
		}
	}
	
	public Scenario getScenario() {
		return scenario;
	}
	
	public void freshScenario() {
		scenario.getAPPs().clear();
		if(content.getChildren() != null) {
			for(Object obj : content.getChildren()) {
				if(obj instanceof IAPP) {
					scenario.addChild((IAPP)obj);
				}
			}
		}
	}

}
