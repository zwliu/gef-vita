package com.casa.vide.appassemble.imageLibrary;

import java.io.File;

public class ImageLibrary {

	private static File directory; 
	static {
		directory = new File("C:\\Users\\Public\\Pictures\\Sample Pictures");
	}
	
	public File[] getImages() {
		return directory.listFiles(new ImageFilter());
	}
}
