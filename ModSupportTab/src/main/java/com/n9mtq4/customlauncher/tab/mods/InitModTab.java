package com.n9mtq4.customlauncher.tab.mods;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.customlauncher.tab.mods.hook.ProfileChangeHook;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.profile.ProfileManager;
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
		ModTab modTab = new ModTab(tabPanel.getMinecraftLauncher());
		tabPanel.addTab("Jar Mods", modTab);
		
//		have to use reflection because the launcher isn't loaded when the classloader tries to implement RefreshedProfilesListener
//		reflection can keep the ProfileChangeHook unloaded until it is safe to load
		ProfileManager pm = tabPanel.getMinecraftLauncher().getProfileManager();
		ReflectionHelper.callVoidMethod("addRefreshedProfilesListener", pm, new ProfileChangeHook(modTab));
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
}
