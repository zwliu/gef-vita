package com.casa.vide.appassemble.editor;

import org.eclipse.gef.ui.palette.PaletteContextMenuProvider;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;

import com.casa.vide.appassemble.imageLibrary.ImageLibrary;

public class MenuPaletteContextMenuProvider extends PaletteContextMenuProvider {

	public MenuPaletteContextMenuProvider(PaletteViewer palette) {
		super(palette);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void buildContextMenu(IMenuManager menu) {
		super.buildContextMenu(menu);
		Action addImageAction = new Action("import images") {
			public void run() {
				ImageLibrary.addImages(getPaletteViewer());
			}
		};
		menu.add(addImageAction);
	}

}
