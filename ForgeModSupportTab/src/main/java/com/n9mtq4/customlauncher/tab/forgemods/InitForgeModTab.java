package com.n9mtq4.customlauncher.tab.forgemods;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.customlauncher.tab.forgemods.ui.ForgeTab;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

import javax.swing.*;

/**
 * Created by will on 7/28/15 at 10:16 PM.
 */
public class InitForgeModTab extends ConsoleListener {
	
	@Override
	public void objectReceived(SentObjectEvent e, final BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("launchertabpanel")) return;
		if (!(e.getObject() instanceof LauncherTabPanel)) return;
		
		final LauncherTabPanel tabPanel = (LauncherTabPanel) e.getObject();
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ForgeTab forgeTab = new ForgeTab(tabPanel.getMinecraftLauncher(), baseConsole);
				tabPanel.addTab("Forge Mods", forgeTab);
			}
		});
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
}
