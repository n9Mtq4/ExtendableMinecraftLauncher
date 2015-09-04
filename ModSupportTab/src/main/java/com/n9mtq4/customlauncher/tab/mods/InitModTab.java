package com.n9mtq4.customlauncher.tab.mods;

import com.n9mtq4.customlauncher.tab.mods.hook.ProfileChangeHook;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.annotation.Async;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.profile.ProfileManager;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

/**
 * Created by will on 7/27/15 at 5:10 PM.
 */
public final class InitModTab implements ObjectListener {
	
	@Async
	@Override
	public final void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (e.getMessage().equalsIgnoreCase("tabsafe") && e.getObject() instanceof LauncherTabPanel) {
			
			LauncherTabPanel tabPanel = (LauncherTabPanel) e.getObject();
			ModTab modTab = new ModTab(tabPanel.getMinecraftLauncher());
			baseConsole.pushObject(new Object[]{"Jar Mods", modTab}, "addtab");
			
//			have to use reflection because the launcher isn't loaded when the classloader tries to implement RefreshedProfilesListener
//			reflection can keep the ProfileChangeHook unloaded until it is safe to load
			ProfileManager pm = tabPanel.getMinecraftLauncher().getProfileManager();
			ReflectionHelper.callVoidMethod("addRefreshedProfilesListener", pm, new ProfileChangeHook(modTab));
			
			baseConsole.disableListenerAttribute(this);
			
		}
		
	}
	
}
