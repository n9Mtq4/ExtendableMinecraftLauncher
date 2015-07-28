package com.n9mtq4.customlauncher.uihooks;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.ui.LauncherPanel;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

/**
 * Created by will on 7/27/15 at 5:56 PM.
 */
public class TabPanelHook extends ConsoleListener {
	
	/**
	 * This listens for the minecraft launcher to be sent,
	 * and then sends a LauncherTabPanel back
	 * */
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("launcherpanel")) return;
		if (!(e.getObject() instanceof LauncherPanel)) return;
		
		LauncherPanel launcherPanel = (LauncherPanel) e.getObject();
		LauncherTabPanel launcherTabPanel = ReflectionHelper.getObject("tabPanel", launcherPanel);
		
		baseConsole.pushObject(launcherTabPanel, "launchertabpanel");
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
//		nothing
	}
	
}
