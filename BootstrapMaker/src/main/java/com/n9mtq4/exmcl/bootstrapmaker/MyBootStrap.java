package com.n9mtq4.exmcl.bootstrapmaker;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.utils.JarLoader;
import net.minecraft.bootstrap.Bootstrap;
import net.minecraft.bootstrap.FatalBootstrapError;
import net.minecraft.launcher.Launcher;

import javax.swing.JFrame;
import java.io.File;
import java.lang.reflect.Constructor;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.net.URLClassLoader;

import static com.n9mtq4.logwindow.utils.ReflectionHelper.getObject;

/**
 * Created by will on 3/28/15.<br>
 * This class overrides Mojang's Bootstrap so rather than
 * creating the Launcher in-scope, it saves it to a member field
 * and pushes it to the BaseConsole so we can access it from the
 * plugins.
 */
public final class MyBootStrap extends Bootstrap {
	
	private static final int EMULATED_BOOTSTRAP_VERSION = 30;
	
	public Launcher launcher;
	private BaseConsole parent;
	
	public MyBootStrap(BaseConsole parent, File workDir, Proxy proxy, PasswordAuthentication proxyAuth, String[] remainderArgs) {
		super(workDir, proxy, proxyAuth, remainderArgs);
		this.parent = parent;
	}
	
	@Override
	public final void startLauncher(File launcherJar) {
		
		println("Starting launcher.");
		println("Modded Minecraft Launcher by n9Mtq4. http://n9mtq4.com/?page=projects#minecraftlauncher");
		try {
			
			JarLoader.addFile(launcherJar);
			File workDir = getObject("workDir", this, Bootstrap.class);
			Proxy proxy = getObject("proxy", this, Bootstrap.class);
			PasswordAuthentication proxyAuth = getObject("proxyAuth", this, Bootstrap.class);
			String[] remainderArgs = getObject("remainderArgs", this, Bootstrap.class);
			
			Class aClass = new URLClassLoader(new URL[] { launcherJar.toURI().toURL() }).loadClass("net.minecraft.launcher.Launcher");
			Constructor constructor = aClass.getConstructor(JFrame.class, File.class, Proxy.class, PasswordAuthentication.class, String[].class, Integer.class);
//			last int is the bootstrap version. were spoofing it so we aren't prompted to update
			launcher = (Launcher) constructor.newInstance(this, workDir, proxy, proxyAuth, remainderArgs, EMULATED_BOOTSTRAP_VERSION);
//			launcher = ReflectionHelper.callConstructor(aClass, this, workDir, proxy, proxyAuth, remainderArgs, 30);
//			BootstrapEvent.fireMinecraftLauncherCreated(launcher);
//			send the launcher to the base console!
			parent.push(this, "jframe");
			parent.push(launcher, "minecraftlauncher");
		}catch (Exception e) {
			throw new FatalBootstrapError("Unable to start: " + e);
		}
		
	}
	
}
