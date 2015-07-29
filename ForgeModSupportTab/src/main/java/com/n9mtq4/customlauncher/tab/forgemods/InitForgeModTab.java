package com.n9mtq4.customlauncher.tab.forgemods;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.customlauncher.tab.forgemods.ui.ForgeTab;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

/**
 * Created by will on 7/28/15 at 10:16 PM.
 */
public class InitForgeModTab extends ConsoleListener {
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("launchertabpanel")) return;
		if (!(e.getObject() instanceof LauncherTabPanel)) return;
		
		LauncherTabPanel tabPanel = (LauncherTabPanel) e.getObject();
		
		ForgeTab forgeTab = new ForgeTab(tabPanel.getMinecraftLauncher());
		tabPanel.addTab("Forge Mods", forgeTab);
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
}
