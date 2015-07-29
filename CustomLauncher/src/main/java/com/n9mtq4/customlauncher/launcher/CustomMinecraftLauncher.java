package com.n9mtq4.customlauncher.launcher;

import javax.swing.*;
import java.io.*;

/**
 * Created by will on 7/27/15 at 12:34 PM.
 */
public class CustomMinecraftLauncher {
	
	/**
	 * Method that is declared in META-INF to run on double click
	 * */
	public static void main(String[] args) {
		
		checkBootstrap();
		loadLibs();
		
		new BootstrapLauncher(args);
		
	}
	
	private static void checkBootstrap() {
		
		try {
			
//			turns file into BufferedReader
			InputStream in = CustomMinecraftLauncher.class.getResourceAsStream("/libs.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String firstLine = reader.readLine();
			
			File bootstrap = new File(firstLine);
			
			if (!bootstrap.exists()) {
				reader.close();
				in.close();
				throw new IOException("no Minecraft.jar");
			}
			reader.close();
			in.close();
			
		}catch (IOException e) {
			
//			https://s3.amazonaws.com/Minecraft.Download/launcher/Minecraft.jar
			JOptionPane.showMessageDialog(null, "There is no Minecraft Launcher. Please download the launcher Jar file from minecraft.net under\n" +
					"\"Download it here\" > \"Show all platforms\" > \"Minecraft for Linux / Other\"",
					"Error", JOptionPane.ERROR_MESSAGE);
			
			System.exit(1);
			
		}
		
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
			
			reader.close();
			in.close();
			
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
