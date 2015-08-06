package com.n9mtq4.customlauncher.gamerunner;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.utils.JarLoader;
import net.minecraft.launcher.Launcher;
import net.minecraft.launcher.SwingUserInterface;
import net.minecraft.launcher.game.GameLaunchDispatcher;
import net.minecraft.launcher.profile.Profile;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by will on 8/5/15 at 12:39 PM.
 */
public class AdvancedGameLauncher {
	
	protected static boolean launch(Launcher launcher, BaseConsole baseConsole) {
		
		try {
			
			GameLaunchDispatcher dispatcher = launcher.getLaunchDispatcher();
			
//			START MOJANG CODE
			if(dispatcher.isRunningInSameFolder()) {
				int result = JOptionPane.showConfirmDialog(((SwingUserInterface) launcher.getUserInterface()).getFrame(),
						"You already have an instance of Minecraft running. If you launch another one in the same folder, " +
								"they may clash and corrupt your saves.\nThis could cause many issues, in singleplayer or " +
								"otherwise. We will not be responsible for anything that goes wrong.\n" +
								"Do you want to start another instance of Minecraft, despite this?\n" +
								"You may solve this issue by launching the game in a different folder " +
								"(see the \"Edit Profile\" button)", "Duplicate instance warning", 0);
				if(result == 0) {
					play(launcher, baseConsole);
				}
			} else {
				play(launcher, baseConsole);
			}
//			END MOJANG CODE
			
			throw new Exception("dev");
//			return true;
			
		}catch (Exception e) {
			e.printStackTrace();
			baseConsole.printStackTrace(e);
			return false;
		}
		
	}
	
	private static void play(Launcher launcher, BaseConsole baseConsole) throws IOException {
		
		Profile selectedProfile = launcher.getProfileManager().getSelectedProfile();
		JarLoader.addFile(getVersionJarFile(launcher));
		
//		ReflectionHelper.callStaticVoidMethod("main", ReflectionHelper.getClass("net.minecraft.client.Main"), new Class[]{String[].class}, new Object[]{new String[]{}});
		
	}
	
	private static File getVersionJarFile(Launcher launcher) {
		
		return new File(getVersionDir(launcher), getMinecraftVersion(launcher) + ".jar");
		
	}
	
	private static File getVersionDir(Launcher launcher) {
		
		com.mojang.launcher.Launcher launcher1 = launcher.getLauncher();
		File workingDir = launcher1.getWorkingDirectory();
		
		File versionDir = new File(workingDir, "versions/" + getMinecraftVersion(launcher) + "/");
		return versionDir;
		
	}
	
	private static String getMinecraftVersion(Launcher launcher) {
		
		return getMinecraftVersion(launcher.getProfileManager().getSelectedProfile());
		
	}
	
	private static String getMinecraftVersion(Profile profile) {
		
		return profile.getLastVersionId();
		
	}
	
}
