package com.n9mtq4.customlauncher.tab.settings;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;

/**
 * Created by will on 7/27/15 at 6:40 PM.
 */
public class InitSettingsTab implements ObjectListener {
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
//		if (e.getMessage().equalsIgnoreCase("launchertabpanel")) return;
//		if (!(e.getObject() instanceof LauncherTabPanel)) return;
		
//		((LauncherTabPanel) e.getObject()).addTab("n9Mtq4 Settings", new SettingsTab());
		
	}
	
}
