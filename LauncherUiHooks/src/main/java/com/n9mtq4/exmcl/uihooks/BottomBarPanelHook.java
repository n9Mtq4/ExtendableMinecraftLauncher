package com.n9mtq4.exmcl.uihooks;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.ui.BottomBarPanel;
import net.minecraft.launcher.ui.LauncherPanel;

/**
 * Created by will on 7/28/15 at 2:25 PM.
 */
public final class BottomBarPanelHook implements ObjectListener {
	
	@Override
	public final void objectReceived(ObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("launcherpanel")) return;
		if (!(e.getContained() instanceof LauncherPanel)) return;
		
		LauncherPanel launcherPanel = (LauncherPanel) e.getContained();
		BottomBarPanel bottomBarPanel = ReflectionHelper.getObject("bottomBar", launcherPanel);
		
		e.getInitiatingBaseConsole().push(bottomBarPanel, "bottombarpanel");
		
	}
	
}
