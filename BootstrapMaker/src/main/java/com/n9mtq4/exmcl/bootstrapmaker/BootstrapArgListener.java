package com.n9mtq4.exmcl.bootstrapmaker;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;

/**
 * Created by will on 7/27/15 at 1:20 PM.<br>
 * This is a listener that intercepts the command line args
 * that are sent to the CustomLauncher. This then pushes them
 * onto BootstrapMaker for allowing the MC Launcher to get them too.
 */
//TODO: not working properly
public final class BootstrapArgListener implements ObjectListener {
	
	/**
	 * Receives args from BootstrapLauncher
	 * */
	@Override
	public final void objectReceived(ObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("args")) return;
		if (!(e.getContained() instanceof String[])) return;
		
		BootstrapMakerListener.args = (String[]) e.getContained();
		
	}
	
}
