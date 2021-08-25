package com.aequillibrium.utilities;

import java.io.File;

public class fileUtils {
	public void clearScreenshotsDirectory() {
		File path = new File("./ErrorScreenshots/");
		if(!path.exists()) {
			path.mkdir();	
		}
		File[] files = path.listFiles();
		for (File file : files) {
			file.delete();
		}
		
	}
}
