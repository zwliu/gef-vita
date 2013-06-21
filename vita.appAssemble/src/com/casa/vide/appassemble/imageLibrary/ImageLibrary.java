package com.casa.vide.appassemble.imageLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

import com.casa.vide.appassemble.factory.ImageFactory;

/**
 * 图片库
 *
 * @author lzw
 */
public class ImageLibrary {
	
	/** 图片库显示的名称*/
	public static String label = "图片库";
	
	/** 图片库所在的目录*/
	private static File directory; 
	static {
		directory = new File("C:\\Users\\Public\\Pictures\\Sample Pictures");
	}

	/**
	 * 获取图片库中的图片文件
	 *
	 * @return 返回图片库中的图片文件
	 */
	public static File[] getImages() {
		return directory.listFiles(new ImageFilter());
	}
	
	/**
	 * 往图片库和PaletteViewer中添加图片
	 *
	 * @param viewer PaletteViewer
	 */
	public static void addImages(PaletteViewer viewer) {
		FileDialog dialog = new FileDialog(viewer.getControl().getShell(), SWT.OPEN | SWT.MULTI);
		dialog.setText("导入图片");
		String[] extensions = {"*.png;*.bmp;*.jpg;*.jpeg"};
		dialog.setFilterExtensions(extensions);
		if(dialog.open() != null) {
			File[] files = new File[dialog.getFileNames().length];
			int i = 0;
			for(String str : dialog.getFileNames())
				files[i++] = new File(dialog.getFilterPath()+File.separator+str);
			//将图片添加到图片库目录
			for(File file : files) {
				try {
					FileInputStream in = new FileInputStream(file);
					FileOutputStream out = new FileOutputStream(directory+File.separator+file.getName());
					final int length = 1048576;
					byte[] buffer = new byte[length]; 
					while(true) {
						try {
							int bytes = in.read(buffer);
							if(bytes != -1)
								out.write(buffer, 0, bytes);
							else {
								in.close();
								out.flush();
								out.close();
								break;
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return ;
						}
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return ;
				}
			}
			//将图片添加到paletteviewer中图片库
			for(Object obj : viewer.getPaletteRoot().getChildren()) {
				if(obj instanceof PaletteDrawer) {
					if(((PaletteDrawer)obj).getLabel().equals(label)) {
						
						for(File file : files) {			
							Image img = new Image(Display.getDefault(), file.getPath());
							Image small = new Image(null, img.getImageData().scaledTo(20, 20));
							Image large = new Image(null, img.getImageData().scaledTo(40, 40));
							ImageDescriptor smallIcon = ImageDescriptor.createFromImage(small);
							ImageDescriptor largeIcon = ImageDescriptor.createFromImage(large);
							((PaletteDrawer)obj).add(new CombinedTemplateCreationEntry(file.getName(), null, Image.class, new ImageFactory(img), smallIcon, largeIcon));
						}
					}
				}
			}
		}
	}
	
	public static void deleteImages() {
		
	}
	
}
