package com.casa.vide.appassemble.action;

import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.LabelRetargetAction;

@SuppressWarnings("restriction")
public class SaveRetargetAction extends LabelRetargetAction {

	public SaveRetargetAction() {
		super(ActionFactory.SAVE.getId(), GEFMessages.SaveAction_Label); 
		setToolTipText(GEFMessages.SaveAction_Tooltip);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_ETOOL_SAVE_EDIT));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(
				ISharedImages.IMG_ETOOL_SAVE_EDIT_DISABLED));
	}
}
