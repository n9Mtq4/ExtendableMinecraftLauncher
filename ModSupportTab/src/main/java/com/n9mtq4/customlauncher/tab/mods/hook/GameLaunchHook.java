package com.n9mtq4.customlauncher.tab.mods.hook;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;

import java.awt.event.ActionEvent;

/**
 * Created by will on 7/28/15 at 9:01 PM.
 */
public class GameLaunchHook implements ObjectListener {
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("gamelaunch")) return;
		if (!(e.getObject() instanceof ActionEvent)) return;
		
//		Game was launched.
		
	}
	
}
