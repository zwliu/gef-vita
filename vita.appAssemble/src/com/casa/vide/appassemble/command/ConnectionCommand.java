package com.casa.vide.appassemble.command;

import org.eclipse.gef.commands.Command;

import com.casa.vide.appassemble.model.Connection;
import com.casa.vide.appassemble.model.Shape;

/**
 * 添加和修改连接的命令
 *
 * @author lzw
 */
public class ConnectionCommand extends Command {
	
	/** 旧的连接源*/
	private Shape oldSource;
	
	/** 就的连接目标*/
	private Shape oldTarget;
	
	/** 新的连接源*/
	private Shape source;
	
	/** 新的连接目标*/
	private Shape target;
	
	/** 连接模型*/
	private Connection connection;
	
	/**
	 * 设置连接模型
	 *
	 * @param conn 连接模型
	 */
	public void setConnection(Connection conn) {
		connection = conn;
		source = conn.getSource();
		target = conn.getTarget();
		oldSource = source;
		oldTarget = target;
	}
	
	/**
	 * 获取连接模型
	 *
	 * @return 返回连接模型
	 */
	public Connection getConnection() {
		return connection;
	}
	
	/**
	 * 判断是否能执行
	 */
	public boolean canExecute() {
		if(connection != null)
			return true;
		return false;
	}
	
	/**
	 * 命令执行，连接首先从原来的连接源和连接目标上移除，再设置新的连接源和连接目标
	 */
	public void execute() {
		connection.detachSource();
		connection.detachTarget();
		connection.setSource(source);
		connection.setTarget(target);
	}
	
	/**
	 * 设置连接源
	 *
	 * @param s 连接源
	 */
	public void setSource(Shape s) {
		oldSource = source;
		source = s;
	}
	
	/**
	 * 设置连接目标
	 *
	 * @param t 连接目标
	 */
	public void setTarget(Shape t) {
		oldTarget = target;
		target = t;
	}
	
	/**
	 * 命令撤销
	 */
	public void undo() {
		connection.detachSource();
		connection.detachTarget();
		connection.setSource(oldSource);
		connection.setTarget(oldTarget);
	}
	
}
