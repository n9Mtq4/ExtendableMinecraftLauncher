package com.n9mtq4.customlauncher.bootstrapmaker;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;

import java.io.IOException;

/**
 * Created by will on 7/27/15 at 1:17 PM.<br>
 * Listeners for if a bootstrap is wanted.
 */
public class BootstrapMakerListener extends ConsoleListener {
	
	protected static String[] args = new String[]{};
	
	/**
	 * Listens for when a new bootstrap is needed
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
//		makes sure the command wants a new bootstrap
		if (!consoleActionEvent.getCommand().getArg(0).equalsIgnoreCase("[request]")) return;
		if (consoleActionEvent.getCommand().getLength() != 2) return;
		if (!consoleActionEvent.getCommand().getArg(1).equalsIgnoreCase("newbootstrap")) return;
		
		try {
			
//			make the bootstrap
			BootstrapUtils.makeABootstrap(args, baseConsole);
			
		}catch (IOException e) {
			baseConsole.printStackTrace(e);
			e.printStackTrace();
		}
		
	}
	
}
