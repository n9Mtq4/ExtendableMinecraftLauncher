package com.n9mtq4.customlauncher.launcher;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.gui.GuiJFrameLite;

import java.io.File;

/**
 * Created by will on 7/27/15 at 12:48 PM.
 */
public class BootstrapLauncher {
	
	/**
	 * Pass the launch args into minecraft
	 * */
	private String[] args;
	
	/**
	 * The internal server that handles communication
	 * */
	private BaseConsole baseConsole;
	
	public BootstrapLauncher(String[] args) {
		
//		passing args into member field
		this.args = args;
		
//		init baseconsole
		this.baseConsole = new BaseConsole();
//		show log if in debugmode
		if (args.length >= 1 && args[0].equals("DEBUGBUILD")) {
			baseConsole.addGui(new GuiJFrameLite());
//			show System.out.print in baseconsole
			baseConsole.redirectStdoutOn(true);
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
		
		String s = "[args]";
		for (String s1 : args) {
			s += " " + s1;
		}
		baseConsole.push(s);
		baseConsole.push("[request] newbootstrap");
		
	}
	
}