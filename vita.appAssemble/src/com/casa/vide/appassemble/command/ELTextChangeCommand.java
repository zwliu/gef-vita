package com.casa.vide.appassemble.command;

import org.eclipse.gef.commands.Command;

import com.casa.vide.appassemble.model.EditableLabelModel;

/**
 * 改变EditableLabel的内容的命令
 *
 * @author lzw
 */
public class ELTextChangeCommand extends Command {

	/**
	 * EditableLabel的模型
	 */
	private EditableLabelModel el;
	
	/** EditableLabel的新内容*/
	private String text;
	
	/**　EditableLabel的旧内容*/
	private String oldText;

	/**
	 * 命令执行，设置Editable的内容
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute()
	{
		el.setText(text);
	}

	/**
	 * 判断命令能否执行
	 * @return 新内容不为null时，返回true；否则，返回false
	 */
	public boolean canExecute()
	{
		if (text != null)
		{
			return true;
		}
		else
		{
			text = oldText;
			return false;
		}
	}

	/**
	 * 设置新内容
	 * 
	 * @param string
	 *            新内容
	 */
	public void setText(String string)
	{
		this.text = string;
	}

	/**
	 * 设置旧内容
	 * 
	 * @param string
	 *            旧内容
	 */
	public void setOldText(String string)
	{
		oldText = string;
	}

	/**
	 * 设置EditableLanel的模型
	 * @param table
	 *           EditableLabel的模型
	 */
	public void setEL(EditableLabelModel el)
	{
		this.el = el;
	}

	/**
	 * 命令撤销
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo()
	{
		el.setText(oldText);
	}
	
}
