package com.n9mtq4.customlauncher.tab.ntab;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.annotation.Async;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import net.minecraft.launcher.Launcher;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

/**
 * Created by will on 7/27/15 at 6:58 PM.
 */
public class InitNTab implements ObjectListener {
	
	@Async
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("launchertabpanel")) return;
		if (!(e.getObject() instanceof LauncherTabPanel)) return;
		
		LauncherTabPanel launcherTabPanel = (LauncherTabPanel) e.getObject();
		Launcher minecraftLauncher = launcherTabPanel.getMinecraftLauncher();
		
//		new NTab(launcherTabPanel, minecraftLauncher);
		
	}
	
}
