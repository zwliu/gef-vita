/**
 * @FileName IconConstant.java
 * @Package com.casa.vide.appassemble.config
 *
 * @Copyright 
 *     Copyright (c) 2013-6-17
 *
 * @Description
 *
 * @author lzw
 * 
 * @date 2013-6-17 上午1:52:10
 *
 * @version v1.0
 */
package com.casa.vide.appassemble.config;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.casa.vide.appassemble.Activator;

/**
 * 程序中的默认常量图标
 * @author lzw
 */
public class IconConstant {
	
	/** APP图元图标*/
	public static ImageDescriptor appImage = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID,"/icons/app.png");
	
	/** VOM图元图标*/
	public static ImageDescriptor vomImage = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID,"/icons/vom.png");
	
	/** VIO图元图标*/
	public static ImageDescriptor vioImage = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID,"/icons/vio.png");
	
	/** Message图元图标*/
	public static ImageDescriptor messageImage = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID,"/icons/message.png");

	/** APP图元小图标*/
	public static ImageDescriptor appDesSm = ImageDescriptor.createFromImageData(IconConstant.appImage.getImageData().scaledTo(16, 16));
	
	/** APP图元大图标*/
	public static ImageDescriptor appDesLg = ImageDescriptor.createFromImageData(IconConstant.appImage.getImageData().scaledTo(20, 20));
	
	/** VOM图元小图标*/
	public static ImageDescriptor vomDesSm = ImageDescriptor.createFromImageData(IconConstant.vomImage.getImageData().scaledTo(16, 16));
	
	/** VOM图元大图标*/
	public static ImageDescriptor vomDesLg = ImageDescriptor.createFromImageData(IconConstant.vomImage.getImageData().scaledTo(20, 20));
	
	/** VIO图元小图标*/
	public static ImageDescriptor vioDesSm = ImageDescriptor.createFromImageData(IconConstant.vioImage.getImageData().scaledTo(16, 16));
	
	/** VIO图元大图标*/
	public static ImageDescriptor vioDesLg = ImageDescriptor.createFromImageData(IconConstant.vioImage.getImageData().scaledTo(20, 20));
	
	/** Message图元小图标*/
	public static ImageDescriptor messageDesSm = ImageDescriptor.createFromImageData(IconConstant.messageImage.getImageData().scaledTo(16, 16));
	
	/** Message图元大图标*/
	public static ImageDescriptor messageDesLg = ImageDescriptor.createFromImageData(IconConstant.messageImage.getImageData().scaledTo(20, 20));
	
	/** 矩形实体图元图标*/
	public static ImageDescriptor rectangleDes = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "/icons/rectangle.png");

	/** 矩形实体图元小图标*/
	public static ImageDescriptor rectangleDesSm = ImageDescriptor.createFromImageData(IconConstant.rectangleDes.getImageData().scaledTo(16, 16));
	
	/** 矩形实体图元大图标*/
	public static ImageDescriptor rectangleDesLg = ImageDescriptor.createFromImageData(IconConstant.rectangleDes.getImageData().scaledTo(20, 20));
	
	/** 连线图标*/
	public static ImageDescriptor lineDes = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "/icons/line.png");

	/** 连线小图标*/
	public static ImageDescriptor lineDesSm = ImageDescriptor.createFromImageData(IconConstant.lineDes.getImageData().scaledTo(16, 16));
	
	/** 连线大图标*/
	public static ImageDescriptor lineDesLg = ImageDescriptor.createFromImageData(IconConstant.lineDes.getImageData().scaledTo(20, 20));
	
	/** 标注图标*/
	public static ImageDescriptor txtDes = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "/icons/txt.png");
	
	/** 标注大图标*/
	public static ImageDescriptor txtDesSm = ImageDescriptor.createFromImageData(IconConstant.txtDes.getImageData().scaledTo(16, 16));
	
	/** 标注小图标*/
	public static ImageDescriptor txtDesLg = txtDes;
	
}
