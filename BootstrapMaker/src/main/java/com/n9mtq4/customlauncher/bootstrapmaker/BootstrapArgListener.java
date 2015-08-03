package com.n9mtq4.customlauncher.bootstrapmaker;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;

/**
 * Created by will on 7/27/15 at 1:20 PM.<br>
 * This is a listener that intercepts the command line args
 * that are sent to the CustomLauncher. This then pushes them
 * onto BootstrapMaker for allowing the MC Launcher to get them too.
 */
//TODO: not working properly
public class BootstrapArgListener extends ConsoleListener {
	
	/**
	 * Receives args from BootstrapLauncher
	 * */
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("args")) return;
		if (!(e.getObject() instanceof String[])) return;
		
		BootstrapMakerListener.args = (String[]) e.getObject();
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
}
