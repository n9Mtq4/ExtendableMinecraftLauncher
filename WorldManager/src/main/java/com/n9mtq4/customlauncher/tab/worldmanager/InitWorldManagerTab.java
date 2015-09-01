package com.n9mtq4.customlauncher.tab.worldmanager;

import com.n9mtq4.customlauncher.tab.worldmanager.ui.WorldManagerTab;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.annotation.Async;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

/**
 * Created by will on 8/31/15 at 11:39 PM.
 */
public class InitWorldManagerTab implements ObjectListener {
	
	@Async
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (e.getMessage().equalsIgnoreCase("tabsafe") && e.getObject() instanceof LauncherTabPanel) {
			
			WorldManagerTab worldManagerTab = new WorldManagerTab((LauncherTabPanel) e.getObject(), baseConsole);
			baseConsole.pushObject(new Object[]{"Worlds", worldManagerTab}, "addtab");
			
			baseConsole.disableListenerAttribute(this);
			
		}
		
	}
	
}
