package com.n9mtq4.exmcl.launcher;

import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
		
//		initialize the base console and other things
		new BootstrapLauncher(args);
		
	}
	
	/**
	 * Makes sure that the linux / other launcher is present in
	 * ./Minecraft.jar. It either adds it to the classpath or alerts the
	 * user.
	 * */
	private static void checkBootstrap() {
		
		try {
			
//			turns file into BufferedReader
			InputStream in = CustomMinecraftLauncher.class.getResourceAsStream("/mclauncher.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String firstLine = reader.readLine();
			
			File bootstrap = new File(firstLine);
			
//			if Minecraft.jar doesn't exist
			if (!bootstrap.exists()) {
				reader.close();
				in.close();
				throw new IOException("no Minecraft.jar");
			}
			
//			if it does exist
//			load bootstrap
			JarLoader.addFile(bootstrap);
			System.out.println("Added " + bootstrap + " to the classpath");
			
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
	 * Adds every thing in libs/ to the classpath
	 * */
	private static void loadLibs() {
		
		File libDir = new File("libs/");
		File[] libs = libDir.listFiles();
		if (libs == null) return;
		for (File file : libs) {
			
//			checks to make sure its loadable
			if (!file.isDirectory() && (file.getName().endsWith(".jar") || file.getName().endsWith(".zip")) && !file.getName().startsWith(".")) {
				try {
					JarLoader.addFile(file);
					System.out.println("Succeeded in adding " + file.getAbsolutePath() + " as a library!");
				}catch (IOException e) {
					System.out.println("Failed to add " + file.getAbsolutePath() + " as a library!");
					e.printStackTrace();
				}
			}
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
