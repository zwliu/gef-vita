package com.casa.vide.appassemble.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Image;

import com.casa.vide.appassemble.model.Shape;

/**
 * 修改实体图元的图像背景的命令
 *
 * @author lzw
 */
public class NodeChangeBackgroundCommand extends Command {

	/**
	 * 实体图元的模型实例
	 */
	private Shape shape;
	
	/**
	 * 实体图元的背景图像
	 */
	private Image background;
	
	/**
	 * 实体图元的旧的背景图像
	 */
	private Image oldBackground;

	/**
	 * 设置实体图元模型
	 *
	 * @param shape 实体图元模型
	 */
	public void setNode(Shape shape) {
		oldBackground = shape.getBackground();
		this.shape = shape;
	}
    
	/**
	 * 设置实体图元模型的背景图像
	 *
	 * @param background 背景图像
	 */
	public void setBackground(Image background) {
		this.background = background;
	}
	
	/**
	 * 命令能执行时，返回true；否则，返回false
	 * @return 当实体图元模型和背景都不是null时，返回true；否则，返回false
	 */
	@Override
	public boolean canExecute() {
		if(shape == null || background == null)
			return false;
		return true;
	}
	
	@Override
	public void execute() {
		shape.setBackground(background);
	}
	
	@Override
	public void undo() {
		shape.setBackground(oldBackground);
	}
}
