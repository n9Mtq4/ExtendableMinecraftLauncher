package com.n9mtq4.customlauncher.tab.forgemods.utils;

import com.n9mtq4.customlauncher.tab.forgemods.data.ModData;
import com.n9mtq4.customlauncher.tab.forgemods.data.ModEntry;
import com.n9mtq4.customlauncher.tab.forgemods.data.ModProfile;
import com.n9mtq4.customlauncher.tab.forgemods.ui.ForgeTab;
import net.minecraft.launcher.Launcher;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by will on 7/29/15 at 1:16 PM.
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class ForgeModManager {
	
	public static void copyToMods(Launcher launcher, ModProfile modProfile) {
		
		com.mojang.launcher.Launcher launcher1 = launcher.getLauncher();
		File workingDir = launcher1.getWorkingDirectory();
		File modDir = new File(workingDir, "mods/");
		modDir.mkdirs();
		
		for (ModEntry mod : modProfile.getModList()) {
			
			if (mod.isEnabled()) {
				try {
					copyFile(mod.getFile(), new File(modDir, mod.getName()));
				}catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error setting up mod " + mod.getName() + "!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
		
	}
	
	public static void cleanup(Launcher launcher) {
		
		com.mojang.launcher.Launcher launcher1 = launcher.getLauncher();
		File workingDir = launcher1.getWorkingDirectory();
		File modDir = new File(workingDir, "mods/");
		if (!modDir.exists()) return;
		
		File[] mods = modDir.listFiles();
		if (mods == null) return;
		for (File file : mods) {
			if (!file.isDirectory() && (file.getName().endsWith(".jar") || file.getName().endsWith(".zip"))) {
				System.out.println("Deleted: " + file.getName());
				file.delete();
			}
		}
		
	}
	
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void firstRunCleanup(Launcher launcher, ModData modData, ForgeTab forgeTab) throws IOException {
		
		com.mojang.launcher.Launcher launcher1 = launcher.getLauncher();
		File workingDir = launcher1.getWorkingDirectory();
		File hasRun = new File(workingDir, "mods/umcl.txt");
//		has been run before
		if (hasRun.exists()) return;
		
		/*
		* copy mods/*.jar into mods_umcl/*.jar
		* delete mods/*.jar
		* make file mods/umcl.txt
		* move all jars in mods_umcl/ into default profile
		* */
		
		File modsDir = new File(workingDir, "mods/");
		File umclModsDir = new File(workingDir, "mods_umcl/");
		File umclRan = new File(workingDir, "mods/umcl.txt");
		
//		COPY MODS/*.JAR INTO MODS_UMCL/*.JAR
		File[] filesInModsDir = modsDir.listFiles(); // get modsContents
		if (filesInModsDir == null) {
//			if null make the ran file and stop
//			make text file
			umclRan.getParentFile().mkdirs();
			umclRan.createNewFile();
			return;
		}
//		not null, copy children, and delete the original
		for (File file : filesInModsDir) {
			
			try {
				if (!file.isDirectory() && (file.getName().endsWith(".jar") || file.getName().endsWith(".zip"))) {
					File newFile = new File(umclModsDir, file.getName());
					copyFile(file, newFile);
					file.delete();
				}
			}catch (IOException e) {
//				DON'T BUBBLE AND BREAK EVERYTHING!
				e.printStackTrace();
			}
			
		}
		
//		make the ran file
		umclRan.getParentFile().mkdirs();
		umclRan.createNewFile();
		
//		get the default profile, or add it if it doesn't exist
		ModProfile defaultProfile = modData.getProfileByName("Default");
		if (defaultProfile == null) {
			defaultProfile = new ModProfile("Default");
			modData.profiles.add(defaultProfile);
		}
		
		File[] filesInUmclModsDir = umclModsDir.listFiles();
		if (filesInUmclModsDir == null) return;
		for (File file : filesInUmclModsDir) {
			
			if (!file.isDirectory() && (file.getName().endsWith(".jar") || file.getName().endsWith(".zip"))) {
				defaultProfile.addMod(file);
			}
			
		}
		
		forgeTab.refresh();
		
	}
	
	/**
	 * http://stackoverflow.com/a/115086
	 * */
	private static void copyFile(File sourceFile, File destFile) throws IOException {
		if(!destFile.exists()) {
			destFile.getParentFile().mkdirs();
			destFile.createNewFile();
		}
		
		FileChannel source = null;
		FileChannel destination = null;
		
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		}
		finally {
			if(source != null) {
				source.close();
			}
			if(destination != null) {
				destination.close();
			}
		}
	}
	
}
