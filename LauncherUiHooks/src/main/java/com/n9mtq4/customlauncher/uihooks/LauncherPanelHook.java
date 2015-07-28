package com.n9mtq4.customlauncher.uihooks;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.SwingUserInterface;
import net.minecraft.launcher.ui.LauncherPanel;

/**
 * Created by will on 7/27/15 at 6:04 PM.
 */
public class LauncherPanelHook extends ConsoleListener {
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("swinguserinterface")) return;
		if (!(e.getObject() instanceof SwingUserInterface)) return;
		
		SwingUserInterface ui = (SwingUserInterface) e.getObject();
		LauncherPanel launcherPanel = ReflectionHelper.getObject("launcherPanel", ui);
		
		e.getBaseConsole().pushObject(launcherPanel, "launcherpanel");
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
}
