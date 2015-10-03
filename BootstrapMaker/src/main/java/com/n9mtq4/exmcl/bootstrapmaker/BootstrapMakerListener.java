package com.n9mtq4.exmcl.bootstrapmaker;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.utils.StringParser;

import java.io.IOException;

/**
 * Created by will on 7/27/15 at 1:17 PM.<br>
 * Listeners for if a bootstrap is wanted.
 */
public final class BootstrapMakerListener implements ObjectListener {
	
	protected static String[] args = new String[]{};
	
	/**
	 * Listens for when a new bootstrap is needed
	 * */
	@Override
	public final void objectReceived(final ObjectEvent sentObjectEvent, final BaseConsole baseConsole) {
		
//		makes sure that text has been requested
		if (!sentObjectEvent.isUserInputString()) return;
		StringParser parser = new StringParser(sentObjectEvent);
		
//		makes sure a bootstrap is wanted
		if (!parser.getArg(0).equals("[request]")) return;
		if (parser.getLength() != 2) return;
		if (!parser.getArg(1).equals("newbootstrap"));
		
		try {
			
//			makes the bootstrap
			BootstrapUtils.makeABootstrap(args, baseConsole);
			
		}catch (IOException e) {
//			prints the error
			baseConsole.printStackTrace(e);
			e.printStackTrace();
		}
		
	}
	
}
