package com.n9mtq4.customlauncher.tab.worldmanager;

import com.n9mtq4.customlauncher.tab.worldmanager.ui.WorldManagerTab;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

import javax.swing.*;

/**
 * Created by will on 8/31/15 at 11:39 PM.
 */
public final class InitWorldManagerTab implements ObjectListener {
	
	@Override
	public final void objectReceived(final SentObjectEvent e, final BaseConsole baseConsole) {
		
		if (e.getMessage().equalsIgnoreCase("tabsafe") && e.getObject() instanceof LauncherTabPanel) {
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					WorldManagerTab worldManagerTab = new WorldManagerTab((LauncherTabPanel) e.getObject(), baseConsole);
					baseConsole.pushObject(new Object[]{"Worlds", worldManagerTab}, "addtab");
				}
			});
			
			baseConsole.disableListenerAttribute(this);
			
		}
		
	}
	
}
