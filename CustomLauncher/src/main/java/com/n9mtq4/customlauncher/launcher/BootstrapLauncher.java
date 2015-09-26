package com.n9mtq4.customlauncher.launcher;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.managers.StdOutRedirection;
import com.n9mtq4.logwindow.ui.GuiJFrameLite;

import java.io.File;

/**
 * Created by will on 7/27/15 at 12:48 PM.
 */
public final class BootstrapLauncher {
	
	/**
	 * Pass the launch args into minecraft
	 * */
	private final String[] args;
	
	/**
	 * The internal server that handles communication
	 * */
	private final BaseConsole baseConsole;
	
	public BootstrapLauncher(String[] args) {
		
//		passing args into member field
		this.args = args;
		
//		init baseconsole
		this.baseConsole = new BaseConsole();
//		show log if in debugmode
		if (contains(args, "DEBUGBUILD")) {
			baseConsole.addConsoleUi(new GuiJFrameLite(baseConsole));
//			show System.out.print in baseconsole
//			baseConsole.redirectStdoutOn(true);
			StdOutRedirection.addToBaseConsole(baseConsole, true);
		}
		
//		add plugins
		System.out.println(new File("plugins/").getAbsolutePath());
		baseConsole.loadPlugins(new File("plugins/").getAbsolutePath());
		
//		make a bootstrap
		makeBootstrap();
		
	}
	
	/**
	 * Tell the plugin to make a new bootstrap
	 * */
	private void makeBootstrap() {
		
/*		String s = "[args]";
		for (String s1 : args) {
			s += " " + s1;
		}
		baseConsole.push(s);*/
		baseConsole.push(args, "args");
		baseConsole.pushString("[request] newbootstrap");
		
	}
	
	private static boolean contains(String[] args, String key) {
		for (String s : args) {
			if (s.equals(key)) return true;
		}
		return false;
	}
	
}
