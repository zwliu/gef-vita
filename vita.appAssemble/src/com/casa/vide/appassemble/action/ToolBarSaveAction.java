package com.casa.vide.appassemble.action;

import java.text.MessageFormat;
import java.util.EventObject;

import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.gef.ui.actions.SaveAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

@SuppressWarnings("restriction")
public class ToolBarSaveAction extends SaveAction implements CommandStackListener {

	public ToolBarSaveAction(IEditorPart editor) {
		super(editor);
	}
	
	protected void init() {
		super.init();
		setToolTipText(MessageFormat.format(GEFMessages.SaveAction_Label, new Object[] { "" }).trim()); //$NON-NLS-1$
		setText(MessageFormat.format(GEFMessages.UndoAction_Label, new Object[] { "" }).trim());
		setId(ActionFactory.SAVE.getId());

		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_ETOOL_SAVE_EDIT));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_ETOOL_SAVE_EDIT_DISABLED));
	}

	@Override
	public void commandStackChanged(EventObject event) {
		if(calculateEnabled()) {
			this.setEnabled(true);
		}
		else
			this.setEnabled(false);
	}
	
}
