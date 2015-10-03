package com.n9mtq4.exmcl.update;

import com.n9mtq4.logwindow.annotation.Async;
import com.n9mtq4.logwindow.events.EnableEvent;
import com.n9mtq4.logwindow.listener.EnableListener;

/**
 * Created by will on 8/1/15 at 9:34 PM.
 */
public final class UpdateListener implements EnableListener {
	
	@Async
	@Override
	public final void onEnable(EnableEvent e) {
		
		new Updater();
		e.getBaseConsole().disableListenerAttribute(this);
		
	}
	
}
