package com.n9mtq4.exmcl.tab.mods.hook;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;

import java.awt.event.ActionEvent;

/**
 * Created by will on 7/28/15 at 9:01 PM.
 */
public final class GameLaunchHook implements ObjectListener {
	
	@Override
	public final void objectReceived(ObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("gamelaunch")) return;
		if (!(e.getContained() instanceof ActionEvent)) return;
		
//		Game was launched.
		
	}
	
}
