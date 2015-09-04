package com.n9mtq4.customlauncher.cleaner;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ConsoleActionEvent;
import com.n9mtq4.logwindow.events.DisableActionEvent;
import com.n9mtq4.logwindow.events.EnableActionEvent;
import com.n9mtq4.logwindow.listener.DisableListener;
import com.n9mtq4.logwindow.listener.EnableListener;
import com.n9mtq4.logwindow.listener.StringListener;

import java.io.File;

/**
 * Created by will on 7/30/15 at 1:15 PM.<br>
 * This listener cleans up the ExMCL folder
 */
public final class Cleaner implements EnableListener, StringListener, DisableListener {
	
	@Override
	public final void onEnable(EnableActionEvent e) {
		
		clean(e.getBaseConsole());
		
	}
	
	@Override
	public final void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (consoleActionEvent.getCommand().getArg(0).equalsIgnoreCase("cleanup")) {
			clean(baseConsole);
		}
		
	}
	
	@Override
	public final void onDisable(DisableActionEvent e) {
		
		clean(e.getBaseConsole());
		
	}
	
	/**
	 * Removes every file in the tmp/ directory.
	 * */
	private void clean(BaseConsole baseConsole) {
		
		File tmpDir = new File("tmp/");
		if (!tmpDir.exists()) return;
		File[] tmpFiles = tmpDir.listFiles();
		
		if (tmpFiles == null) return;
		for (File file : tmpFiles) {
			
			try {
				boolean success = file.delete();
				baseConsole.println((success ? "Successfully" : "Failed to") + " deleted file " + file.getAbsolutePath());
				System.out.println((success ? "Successfully" : "Failed to") + " deleted file " + file.getAbsolutePath());
			}catch (Exception e) {
//				just in case
//				when dealing loops, it is good to prevent one bad apple from
//				spoiling the rest
				e.printStackTrace();
				baseConsole.printStackTrace(e);
			}
			
		}
		
	}
	
}
