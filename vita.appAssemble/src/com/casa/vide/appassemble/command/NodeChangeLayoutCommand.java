package com.casa.vide.appassemble.command;

import org.eclipse.gef.commands.Command;

import com.casa.vide.appassemble.model.Node;

/**
 * 修改图元在XYLayout中布局约束（Rectangle）的命令，通过该命令实现图元resize的功能
 *
 * @author lzw
 */
public class NodeChangeLayoutCommand extends Command {
	
	/** 图元基本模型*/
	private Node model = null;
	
	/** 新的布局约束*/
	private Object layout = null;
	
	/** 旧的布局约束*/
	private Object oldLayout = null;
	
	/**
	 * 设置图元基本模型
	 *
	 * @param model 图元基本模型
	 */
	public void setModel(Object model) {
		if(model instanceof Node) {
			this.model = (Node) model;
			oldLayout = ((Node) model).getLayout();
		}
	}
	
	/**
	 * 设置新的布局约束
	 *
	 * @param constraint 新的布局约束
	 */
	public void setConstraint(Object constraint) {	
		this.layout = constraint;
	}
	
	/**
	 * 图元基本模型和新的布局约束均不是null时，返回true，表示命令能够执行；否则，返回false，命令不能执行
	 */
	@Override
	public boolean canExecute() {
		if(model == null || layout == null)
			return false;
		return true;
	}
	
	@Override
	public void execute() {
		 model.setLayout(layout);
	}
	
	@Override
	public void undo() {
		model.setLayout(oldLayout);
	}
}
