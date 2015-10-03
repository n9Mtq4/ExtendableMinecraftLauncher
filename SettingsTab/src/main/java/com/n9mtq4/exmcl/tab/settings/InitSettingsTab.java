package com.n9mtq4.exmcl.tab.settings;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.annotation.Async;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

/**
 * Created by will on 7/27/15 at 6:40 PM.
 */
public final class InitSettingsTab implements ObjectListener {
	
	@Async
	@Override
	public final void objectReceived(ObjectEvent e, BaseConsole baseConsole) {
		
		if (e.getMessage().equalsIgnoreCase("tabsafe") && e.getObject() instanceof LauncherTabPanel) {
			
			SettingsTab settingsTab = new SettingsTab();
			baseConsole.push(new Object[]{"Settings", settingsTab}, "addtab");
			
			baseConsole.disableListenerAttribute(this);
			
		}
		
	}
	
}
