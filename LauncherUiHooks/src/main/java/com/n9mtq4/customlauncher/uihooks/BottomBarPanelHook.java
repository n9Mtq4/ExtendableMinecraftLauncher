package com.n9mtq4.customlauncher.uihooks;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.ui.BottomBarPanel;
import net.minecraft.launcher.ui.LauncherPanel;

/**
 * Created by will on 7/28/15 at 2:25 PM.
 */
public class BottomBarPanelHook implements ObjectListener {
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("launcherpanel")) return;
		if (!(e.getObject() instanceof LauncherPanel)) return;
		
		LauncherPanel launcherPanel = (LauncherPanel) e.getObject();
		BottomBarPanel bottomBarPanel = ReflectionHelper.getObject("bottomBar", launcherPanel);
		
		e.getBaseConsole().pushObject(bottomBarPanel, "bottombarpanel");
		
	}
	
}
