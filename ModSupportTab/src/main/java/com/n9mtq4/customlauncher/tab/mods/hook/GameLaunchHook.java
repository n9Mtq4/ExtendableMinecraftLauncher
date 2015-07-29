package com.n9mtq4.customlauncher.tab.mods.hook;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;

import java.awt.event.ActionEvent;

/**
 * Created by will on 7/28/15 at 9:01 PM.
 */
public class GameLaunchHook extends ConsoleListener {
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("gamelaunch")) return;
		if (!(e.getObject() instanceof ActionEvent)) return;
		
//		Game was launched.
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
}
