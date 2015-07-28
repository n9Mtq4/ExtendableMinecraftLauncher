package com.n9mtq4.customlauncher.bootstrapmaker;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;

/**
 * Created by will on 7/27/15 at 1:20 PM.
 */
public class BootstrapArgListener extends ConsoleListener {
	
	/**
	 * Receives args from BootstrapLauncher
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (!consoleActionEvent.getCommand().getArg(0).equals("[args]"));
		
		String[] args = consoleActionEvent.getCommand().getArgs();
		String[] actualArgs = new String[args.length - 1];
		
//		removes the [args] prefix
		for (int i = 1; i < args.length - 1; i++) {
			actualArgs[i - 1] = args[i];
		}
		
		BootstrapMakerListener.args = actualArgs;
		
	}
	
}
