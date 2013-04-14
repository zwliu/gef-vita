package com.casa.vide.appassemble.editor;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;

public class MenuPaletteProvider extends PaletteViewerProvider {

	public MenuPaletteProvider(EditDomain graphicalViewerDomain) {
		super(graphicalViewerDomain);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void configurePaletteViewer(PaletteViewer viewer) {
		viewer.setContextMenu(new MenuPaletteContextMenuProvider(viewer));
	}

}
