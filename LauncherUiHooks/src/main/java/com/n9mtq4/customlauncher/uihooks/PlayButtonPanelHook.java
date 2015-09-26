package com.n9mtq4.customlauncher.uihooks;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.ui.BottomBarPanel;
import net.minecraft.launcher.ui.bottombar.PlayButtonPanel;

/**
 * Created by will on 7/27/15 at 6:23 PM.
 */
public final class PlayButtonPanelHook implements ObjectListener {
	
	@Override
	public final void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("bottombarpanel")) return;
		if (!(e.getObject() instanceof BottomBarPanel)) return;
		
		BottomBarPanel bottomBarPanel = (BottomBarPanel) e.getObject();
		
		PlayButtonPanel playButtonPanel = ReflectionHelper.getObject("playButtonPanel", bottomBarPanel);
		
		e.getBaseConsole().push(playButtonPanel, "playbuttonpanel");
		
	}
	
}
