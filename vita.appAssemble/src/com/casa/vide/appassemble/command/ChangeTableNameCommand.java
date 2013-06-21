package com.casa.vide.appassemble.command;

import org.eclipse.gef.commands.Command;

import com.casa.vide.appassemble.model.VOM;

/**
 * 改变VOM实例名的命令
 * 
 * @author lzw
 */
public class ChangeTableNameCommand extends Command
{

	/**
	 * VOM模型对象
	 */
	private VOM table;
	/**
	 * VOM的新实例名
	 */
	private String name;
	/**
	 * VOM之前的实例名
	 */
	private String oldName;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute()
	{
		table.setName(name);
	}

	/**
	 * 命令能否执行
	 * @return 当新实例名不为null时，返回true
	 */
	public boolean canExecute()
	{
		if (name != null)
		{
			return true;
		}
		else
		{
			name = oldName;
			return false;
		}
	}

	/**
	 * 设置新的实例名
	 * 
	 * @param string
	 *            新的实例名
	 */
	public void setName(String string)
	{
		this.name = string;
	}

	/**
	 * 设置旧实例名
	 * 
	 * @param string
	 *            旧实例名
	 */
	public void setOldName(String string)
	{
		oldName = string;
	}

	/**
	 * 设置VOM模型
	 * @param table
	 *            VOM模型
	 */
	public void setTable(VOM table)
	{
		this.table = table;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo()
	{
		table.setName(oldName);
	}

}