package com.n9mtq4.exmcl.tab.worldmanager;

import com.n9mtq4.exmcl.tab.worldmanager.ui.WorldManagerTab;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

import javax.swing.SwingUtilities;

/**
 * Created by will on 8/31/15 at 11:39 PM.
 */
public final class InitWorldManagerTab implements ObjectListener {
	
	@Override
	public final void objectReceived(final ObjectEvent e, final BaseConsole baseConsole) {
		
		if (e.getMessage().equalsIgnoreCase("tabsafe") && e.getObject() instanceof LauncherTabPanel) {
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					WorldManagerTab worldManagerTab = new WorldManagerTab((LauncherTabPanel) e.getObject(), baseConsole);
					baseConsole.push(new Object[]{"Worlds", worldManagerTab}, "addtab");
				}
			});
			
			baseConsole.disableListenerAttribute(this);
			
		}
		
	}
	
}
