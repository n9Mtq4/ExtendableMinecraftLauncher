package com.n9mtq4.customlauncher.uihooks;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.ui.BottomBarPanel;
import net.minecraft.launcher.ui.LauncherPanel;
import net.minecraft.launcher.ui.bottombar.PlayButtonPanel;

/**
 * Created by will on 7/27/15 at 6:23 PM.
 */
public class PlayButtonPanelHook extends ConsoleListener {
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("launcherpanel")) return;
		if (!(e.getObject() instanceof LauncherPanel)) return;
		
		LauncherPanel launcherPanel = (LauncherPanel) e.getObject();
		BottomBarPanel bottomBarPanel = ReflectionHelper.getObject("bottomBar", launcherPanel);
		PlayButtonPanel playButtonPanel = ReflectionHelper.getObject("playButtonPanel", bottomBarPanel);
		
		e.getBaseConsole().pushObject(playButtonPanel, "playbuttonpanel");
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
}
