package com.n9mtq4.customlauncher.launcher;

import java.io.*;

/**
 * Created by will on 7/27/15 at 12:34 PM.
 */
public class CustomMinecraftLauncher {
	
	/**
	 * Method that is declared in META-INF to run on double click
	 * */
	public static void main(String[] args) {
		
		loadLibs();
		
		new BootstrapLauncher(args);
		
	}
	
	/**
	 * Adds every thing listed in the libs.txt file to the classpath
	 * */
	private static void loadLibs() {
		
//		turns file into BufferedReader
		InputStream in = CustomMinecraftLauncher.class.getResourceAsStream("/libs.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		try {
			
//			loops through every line
			String line;
			while ((line = reader.readLine()) != null) {
				
//				ignore blank lines and comments
				if (!line.startsWith("#") && !line.trim().equals("")) {
					
//					add it to the classpath
					JarLoader.addFile(new File(line));
					
				}
				
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Gets the directory that we are running in
	 * */
	public static File getFolderFile() {
		
		File f = new File(System.getProperty("java.class.path"));
		File f1 = new File(f.getAbsolutePath());
		return f1.getParentFile();
		
	}
	
}
