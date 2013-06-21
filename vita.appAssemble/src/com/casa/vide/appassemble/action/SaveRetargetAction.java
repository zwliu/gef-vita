/**
 * @FileName 
 * @Package com.casa.vide.appassemble.action
 * 
 * @Copyright
 * 	   Copyright (C) 2013 JTang Middleware All Rights Reserved 
 * 
 * @Description
 * 	   
 * @author lzw
 * 
 * @date 2013-05-20
 * 
 * @version v1.0
 */
package com.casa.vide.appassemble.action;

import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.LabelRetargetAction;

/**
 * 保存操作的RetargetAction，并未真正执行保存动作，只是通过动作的ID来获取真正的保存动作（ToolBarSaveAction），
 * 进而执行保存动作，作用是可以添加到CommonActionBarContributor中。
 * 
 * @author lzw
 * 
 */
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
