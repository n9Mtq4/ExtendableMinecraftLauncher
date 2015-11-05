package com.n9mtq4.exmcl.uihooks;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.ui.LauncherPanel;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

/**
 * Created by will on 7/27/15 at 5:56 PM.
 */
public final class TabPanelHook implements ObjectListener {
	
	/**
	 * This listens for the minecraft launcher to be sent,
	 * and then sends a LauncherTabPanel back
	 * */
	@Override
	public final void objectReceived(ObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("launcherpanel")) return;
		if (!(e.getContained() instanceof LauncherPanel)) return;
		
		LauncherPanel launcherPanel = (LauncherPanel) e.getContained();
		LauncherTabPanel launcherTabPanel = ReflectionHelper.getObject("tabPanel", launcherPanel);
		
		baseConsole.push(launcherTabPanel, "launchertabpanel");
		
	}
	
}
