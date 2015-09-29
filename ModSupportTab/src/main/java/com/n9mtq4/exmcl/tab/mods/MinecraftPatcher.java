package com.n9mtq4.exmcl.tab.mods;

import net.minecraft.launcher.Launcher;
import net.minecraft.launcher.profile.Profile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by will on 7/28/15 at 5:02 PM.
 */
public class MinecraftPatcher {
	
	public static String getVersionJarPath(Launcher launcher) {
		return getVersionJarFile(launcher).getAbsolutePath();
	}
	
	public static File getVersionJarFile(Launcher launcher) {
		
		com.mojang.launcher.Launcher launcher1 = launcher.getLauncher();
		File workingDir = launcher1.getWorkingDirectory();
		
		File jarLocation = new File(workingDir, "versions/" + getMinecraftVersion(launcher) + "/" + getMinecraftVersion(launcher) + ".jar");
		return jarLocation;
		
	}
	
	public static void backupJar(Launcher launcher) throws IOException {
		
		File source = getVersionJarFile(launcher);
		File dest = source.getParentFile();
		dest = new File(dest, source.getName() + ".bak");
		copyFile(source, dest);
		
	}
	
	public static void unzip(Launcher launcher) throws IOException {
		
		File jarFile = getVersionJarFile(launcher);
		String outputDir = new File(jarFile.getParent(), "/" + getMinecraftVersion(launcher)).getAbsolutePath();
		
		unzip(jarFile, outputDir);
		
	}
	
	/**
	 * http://www.mkyong.com/java/how-to-decompress-files-from-a-zip-file/
	 * */
	private static void unzip(File file, String outputDir) throws IOException {
		
		byte[] buffer = new byte[1024];
		File outDir = new File(outputDir);
		if (!outDir.exists()) //noinspection ResultOfMethodCallIgnored
			outDir.mkdirs();
		
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
		ZipEntry ze;
		
		while ((ze = zipInputStream.getNextEntry()) != null) {
			
			String fileName = ze.getName();
			File newFile = new File(outputDir + File.separator + fileName);
			//noinspection ResultOfMethodCallIgnored
			new File(newFile.getParent()).mkdirs();
			
			FileOutputStream fos = new FileOutputStream(newFile);
			
			int len;
			while ((len = zipInputStream.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			
		}
		
		zipInputStream.closeEntry();
		zipInputStream.close();
		
	}
	
	public static void zip(File[] files, File out, File sourceDir) throws IOException {
		
		String[] fileNames = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			fileNames[i] = files[i].getAbsolutePath();
		}
		zip(fileNames, out, sourceDir);
		
	}
	
	/**
	 * http://www.mkyong.com/java/how-to-compress-files-in-zip-format/
	 * */
	public static void zip(String[] files, File out, File sourceDir) throws IOException {
		
		byte[] buffer = new byte[1024];
		
		FileOutputStream fos = new FileOutputStream(out);
		ZipOutputStream zos = new ZipOutputStream(fos);
		
		int subIndex = sourceDir.getAbsolutePath().length();
		
		for (String file : files) {
			
//			ZipEntry ze = new ZipEntry(file);
			ZipEntry ze = new ZipEntry(file.substring(subIndex, file.length()));
			zos.putNextEntry(ze);
			
			FileInputStream in = new FileInputStream(file);
			
			int len;
			while ((len = in.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}
			
			in.close();
			
		}
		
		zos.closeEntry();
		zos.close();
		
	}
	
	public static ArrayList<File> getArrayListOfFiles(File node) {
		
		ArrayList<File> files = new ArrayList<File>();
		if (node.isDirectory()) {
			//noinspection ConstantConditions
			for (File f : node.listFiles()) {
				files.addAll(getArrayListOfFiles(f));
			}
		}else {
			files.add(node);
		}
		
		return files;
		
	}
	
	public static String getMinecraftVersion(Launcher launcher) {
		
		return getMinecraftVersion(launcher.getProfileManager().getSelectedProfile());
		
	}
	
	public static String getMinecraftVersion(Profile profile) {
		
		return profile.getLastVersionId();
		
	}
	
	/**
	 * http://stackoverflow.com/a/115086
	 * */
	protected static void copyFile(File sourceFile, File destFile) throws IOException {
		if(!destFile.exists()) {
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
