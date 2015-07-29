package com.n9mtq4.customlauncher.tab.mods;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.customlauncher.tab.mods.hook.ProfileChangeHook;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by will on 7/27/15 at 5:10 PM.
 */
public class InitModTab extends ConsoleListener {
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("launchertabpanel")) return;
		if (!(e.getObject() instanceof LauncherTabPanel)) return;
		
		LauncherTabPanel tabPanel = (LauncherTabPanel) e.getObject();
		ModTab modTab = new ModTab(tabPanel.getMinecraftLauncher());
		tabPanel.addTab("Jar Mods", modTab);
		
/*		TODO: priority LOW
		TODO: find a way to get this to work
		TODO: the launcher.jar isn't loaded at this point wish makes it difficult to do anything
		TODO: this could be accomplished with reflection.*/
//		tabPanel.getMinecraftLauncher().getProfileManager().addRefreshedProfilesListener(new ProfileChangeHook(modTab));
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
}
