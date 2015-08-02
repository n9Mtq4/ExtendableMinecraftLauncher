package com.n9mtq4.customlauncher.update;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.EnableActionEvent;

/**
 * Created by will on 8/1/15 at 9:34 PM.
 */
public class UpdateListener extends ConsoleListener {
	
	@Override
	public void onEnable(EnableActionEvent e) {
		
		new Updater();
		e.getBaseConsole().disableListener(this);
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
}
