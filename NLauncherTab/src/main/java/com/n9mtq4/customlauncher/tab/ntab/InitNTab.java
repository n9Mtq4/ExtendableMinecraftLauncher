package com.n9mtq4.customlauncher.tab.ntab;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.annotation.Async;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

/**
 * Created by will on 7/27/15 at 6:58 PM.
 */
public final class InitNTab implements ObjectListener {
	
	@Async
	@Override
	public final void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (e.getMessage().equalsIgnoreCase("tabsafe") && e.getObject() instanceof LauncherTabPanel) {
			
			NTab nTab = new NTab((LauncherTabPanel) e.getObject(), ((LauncherTabPanel) e.getObject()).getMinecraftLauncher());
			baseConsole.push(new Object[]{"NLauncher", nTab}, "addtab");
			
			baseConsole.disableListenerAttribute(this);
			
		}
		
	}
	
}
