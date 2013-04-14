package com.casa.vide.appassemble.imageLibrary;

import java.io.File;
import java.io.FileFilter;

public class ImageFilter implements FileFilter {

	String[] imageFormat = {".png", ".bmp", ".jpg", ".jpeg"};
	
	@Override
	public boolean accept(File pathname) {
		if(pathname.isDirectory()) {
			return false;
		}
		else {
			String name = pathname.getName();
			for(String format : imageFormat) {
				if(name.endsWith(format))
					return true;
			}
			return false;
		}
	}

}
