package com.n9mtq4.customlauncher.tab.mods;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

/**
 * Created by will on 7/27/15 at 5:10 PM.
 */
public class InitModTab extends ConsoleListener {
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("launchertabpanel")) return;
		if (!(e.getObject() instanceof LauncherTabPanel)) return;
		
		LauncherTabPanel tabPanel = (LauncherTabPanel) e.getObject();
		tabPanel.addTab("Mods", new ModTab(tabPanel.getMinecraftLauncher()));
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
}
