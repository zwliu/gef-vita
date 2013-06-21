package com.casa.vide.appassemble.command;

import org.eclipse.gef.commands.Command;

import com.casa.vide.appassemble.model.Node;

/**
 * 删除图元的命令
 *
 * @author lzw
 */
public class NodeDeleteCommand extends Command {
	
	/** 父图元模型*/
	private Node parent = null;
	
	/** 子图元模型*/
	private Node child = null;
	
	/**
	 * 设置父图元模型
	 *
	 * @param parent 父图元模型
	 */
	public void setParent(Object parent) {
		if(parent instanceof Node)
			this.parent = (Node)parent;
	}
	
	/**
	 * 设置子图元模型
	 *
	 * @param child 子图元模型
	 */
	public void setChild(Object child) {
		if(child instanceof Node)
			this.child = (Node)child;
	}
	
	@Override
	public boolean canExecute() {
		if(parent == null || child == null)
			return false;
		return true;
	}
	
	@Override
	public void execute() {
		parent.removeChild(child);
	}
	
	@Override
	public void undo() {
		if(!parent.contains(child))
			parent.addChild(child);
	}
}
