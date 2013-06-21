package com.casa.vide.appassemble.command;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;
//import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

//import com.casa.vide.appassemble.figure.InitElementWizard;
import com.casa.vide.appassemble.model.Message;
import com.casa.vide.appassemble.model.Node;
import com.casa.vide.appassemble.model.VIO;
import com.casa.vide.appassemble.model.VOM;
import com.casa.vide.appassemble.modelinterface.IElement;
import com.casa.vide.modeling.ModelSelector;

/**
 * 添加（创建）图元的命令
 *
 * @author lzw
 */
public class NodeCreateCommand extends Command {
	
	/** 父图元模型*/
	private Node parent = null;
	
	/** 子图元模型*/
	private Node child = null;
	
	/**
	 * 设置父图元模型
	 *
	 * @param parent 父图元模型
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	/**
	 *设置子图元模型 
	 *
	 * @param child 子图元模型
	 */
	public void setChild(Node child) {
		this.child = child;
	}
	
	/**
	 * 设置子图元在父图元中的布局约束
	 *
	 * @param constraint
	 */
	public void setLayout(Object constraint) {
		if(child == null)
			return;
		child.setLayout(constraint);
	}
	
	@Override
	public boolean canExecute() {
		if(parent == null || child == null)
			return false;
		return true;
	}
	
	@Override
	public void execute() {
//		if(child instanceof IElement) {
//			Class<? extends IElement> type = child instanceof VIO ? VIO.class :
//				child instanceof Message ? Message.class : IElement.class;
//			InitElementWizard wizard = new InitElementWizard((VOM)parent, type);
//			Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().
//					getActivePage().getActiveEditor().getSite().getShell();
//			WizardDialog dialog = new WizardDialog(shell, wizard);
//			dialog.create();
//			dialog.getShell().setSize(400, 380);
//			dialog.setTitle("初始化对话框");
//			String message = type == VIO.class ? "初始化VIO" : 
//				type == Message.class ? "初始化Message" : "初始化未知类型";
//			dialog.setMessage(message);
//			if(dialog.open() != WizardDialog.OK ) {
//				return ;
//			}
//			((IElement)child).setName(wizard.getName());
//			((IElement)child).setInstanceName(wizard.getInstanceName());
//			((IElement)child).setVdlName(((VOM)parent).getVdlName());
//		}
		if(child instanceof IElement) {  //设置VIO/Message的VdlName、instanceName、Parent
			((IElement)child).setVdlName(((VOM)parent).getVdlName());			
			if(child instanceof VIO) {
				((IElement)child).setInstanceName("vio"+VIO.getCount());
				((IElement)child).setParent((VOM)parent);
			}
			else {
				((IElement)child).setInstanceName("message"+Message.getCount());
				((IElement)child).setParent((VOM)parent);
			}
		}
		else if(child instanceof VOM)   //
			openModelSelectWizard((VOM)child);
		parent.addChild(child);
	}
	
	/**
	 * 父图元包含子图元时，返回true，命令能够撤销；否则，返回false，不能撤销
	 */
	@Override 
	public boolean canUndo() {
		if(parent == null || child == null)
			return false;
		return parent.contains(child);
	}
	
	@Override
	public void undo() {
		parent.removeChild(child);
	}
	
	/**
	 * 在创建VOM模型时初始化VOM图元
	 *
	 * @param vom VOM图元模型
	 */
	private void openModelSelectWizard(VOM vom) {
		ModelSelector selector = new ModelSelector();
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getSite().getShell();
		if(selector.openModelSelectWizard(shell)) {
			vom.setName(selector.getVomName());
			vom.setInstanceName(selector.getInstanceName());
			vom.setVdlName(selector.getVdlName());
			//设置VIOs
			if(selector.getVIOs() != null) {
				vom.setVIOs(selector.getVIOs());
			}
			//设置Messages
			if(selector.getMessages() != null) {
				vom.setMessages(selector.getMessages());
			}
			//设置imports的key
			if(selector.getImports() != null) {
				HashMap<String, String> map = new HashMap<String, String>();
				for(Iterator<String> iterator = selector.getImports().iterator(); iterator.hasNext(); ) {
					map.put(iterator.next(), null);
				}
				vom.setImports(map);
			}
		}
	}
	
}
