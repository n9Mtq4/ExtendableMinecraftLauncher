package com.n9mtq4.customlauncher.bootstrapmaker;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.utils.JarLoader;
import net.minecraft.bootstrap.Bootstrap;
import net.minecraft.bootstrap.FatalBootstrapError;
import net.minecraft.launcher.Launcher;

import javax.swing.*;
import java.io.File;
import java.lang.reflect.Constructor;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.net.URLClassLoader;

import static com.n9mtq4.console.lib.utils.ReflectionHelper.getObject;

/**
 * Created by will on 3/28/15.
 */
public class MyBootStrap extends Bootstrap {
	
	public Launcher launcher;
	private BaseConsole parent;
	
	public MyBootStrap(BaseConsole parent, File workDir, Proxy proxy, PasswordAuthentication proxyAuth, String[] remainderArgs) {
		super(workDir, proxy, proxyAuth, remainderArgs);
		this.parent = parent;
	}
	
	@Override
	public void startLauncher(File launcherJar) {
		
		println("Starting launcher.");
		println("Modded Minecraft Launcher by n9Mtq4. http://n9mtq4.com/?page=projects#minecraftlauncher");
		try {
			
			JarLoader.addFile(launcherJar);
			File workDir = getObject("workDir", this, Bootstrap.class);
			Proxy proxy = getObject("proxy", this, Bootstrap.class);
			PasswordAuthentication proxyAuth = getObject("proxyAuth", this, Bootstrap.class);
			String[] remanderArgs = getObject("remainderArgs", this, Bootstrap.class);
			
			Class aClass = new URLClassLoader(new URL[] { launcherJar.toURI().toURL() }).loadClass("net.minecraft.launcher.Launcher");
			Constructor constructor = aClass.getConstructor(JFrame.class, File.class, Proxy.class, PasswordAuthentication.class, String[].class, Integer.class);
//			last int is the bootstrap version. were spoofing it so we aren't prompted to update
			launcher = (Launcher) constructor.newInstance(this, workDir, proxy, proxyAuth, remanderArgs, 30);
//			launcher = ReflectionHelper.callConstructor(aClass, this, workDir, proxy, proxyAuth, remanderArgs, 30);
//			BootstrapEvent.fireMinecraftLauncherCreated(launcher);
			parent.pushObject(launcher, "minecraftlauncher");
		}catch (Exception e) {
			throw new FatalBootstrapError("Unable to start: " + e);
		}
		
	}
	
}
