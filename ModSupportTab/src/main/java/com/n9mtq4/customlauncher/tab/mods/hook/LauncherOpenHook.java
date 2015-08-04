package com.n9mtq4.customlauncher.tab.mods.hook;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import net.minecraft.launcher.Launcher;
import net.minecraft.launcher.profile.Profile;

import java.io.*;

/**
 * Created by will on 7/28/15 at 9:02 PM.
 */
public class LauncherOpenHook extends ConsoleListener {
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("minecraftlauncher")) return;
		if (!(e.getObject() instanceof Launcher)) return;
		
		Launcher launcher = (Launcher) e.getObject();
		Profile currentProfile = launcher.getProfileManager().getSelectedProfile();
		
		if (!currentProfile.getLastVersionId().endsWith("_umcl")) {
//			we need to make a new version!
		}
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
	/**
	 * http://www.mkyong.com/java/how-to-copy-directory-in-java/
	 * */
	private static void copyFolder(File src, File dest)
			throws IOException {
		
		if(src.isDirectory()){
			
			//if directory not exists, create it
			if(!dest.exists()){
				//noinspection ResultOfMethodCallIgnored
				dest.mkdirs();
			}
			
			//list all the directory contents
			String files[] = src.list();
			
			for (String file : files) {
				//construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				//recursive copy
				copyFolder(srcFile,destFile);
			}
			
		}else{
			//if file, then copy it
			//Use bytes stream to support all file types
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);
			
			byte[] buffer = new byte[1024];
			
			int length;
			//copy the file content in bytes 
			while ((length = in.read(buffer)) > 0){
				out.write(buffer, 0, length);
			}
			
			in.close();
			out.close();
//			System.out.println("File copied from " + src + " to " + dest);
		}
	}
	
}
