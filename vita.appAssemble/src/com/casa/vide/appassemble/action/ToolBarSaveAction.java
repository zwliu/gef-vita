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

import java.text.MessageFormat;
import java.util.EventObject;

import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.gef.ui.actions.SaveAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/**
 * <p>保存编辑器的动作，动作的图标状态随保存状态改变。</p>
 * <p>实现CommandStackListener，通过监听命令堆栈的状态变化，来改变动作的图标</p>
 *
 * @author lzw
 */
@SuppressWarnings("restriction")
public class ToolBarSaveAction extends SaveAction implements CommandStackListener {
	
	/**
	 * 编辑器保存动作的构造函数
	 * 
	 * @param editor GEF编辑器
	 */
	public ToolBarSaveAction(IEditorPart editor) {
		super(editor);
	}
	
	/**
	 * 动作的初始化，初始化动作的Text、Id、ToolTipText、ImageDescriptor、DisabledImageDescriptor
	 */
	protected void init() {
		super.init();
		setToolTipText(MessageFormat.format(GEFMessages.SaveAction_Label, new Object[] { "" }).trim()); //$NON-NLS-1$
		setText(MessageFormat.format(GEFMessages.UndoAction_Label, new Object[] { "" }).trim());
		setId(ActionFactory.SAVE.getId());

		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_ETOOL_SAVE_EDIT));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_ETOOL_SAVE_EDIT_DISABLED));
	}
    
	/**
	 * 命令堆栈状态改变时，改变动作的状态（Enabled/Disabled）
	 */
	@Override
	public void commandStackChanged(EventObject event) {
		if(calculateEnabled()) {
			this.setEnabled(true);
		}
		else
			this.setEnabled(false);
	}
	
}
