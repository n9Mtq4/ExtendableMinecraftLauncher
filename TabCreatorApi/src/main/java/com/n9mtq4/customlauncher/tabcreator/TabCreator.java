package com.n9mtq4.customlauncher.tabcreator;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

import java.awt.*;

/**
 * Created by will on 8/31/15 at 11:58 PM.
 */
public class TabCreator implements ObjectListener {
	
	private Object tabPanel;
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (tabPanel == null) tryToGetTabPanel(e, baseConsole);
		else tryCreatingTab(e, baseConsole);
		
	}
	
	private void tryCreatingTab(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("addtab")) return;
		if (!(e.getObject() instanceof Object[])) return;
		if (((Object[]) e.getObject()).length != 2) return;
		final Object[] objs = (Object[]) e.getObject();
		if (!(objs[0] instanceof String)) return;
		if (!(objs[1] instanceof Component)) return;
		
		((LauncherTabPanel) tabPanel).addTab((String) objs[0], (Component) objs[1]);
		baseConsole.println("Added tab name: " + objs[0] + ", an instance of " + objs[1].getClass().getName());
		
	}
	
	private void tryToGetTabPanel(SentObjectEvent e, BaseConsole baseConsole) {
		if (!e.getMessage().equalsIgnoreCase("launchertabpanel")) return;
		if (!(e.getObject() instanceof LauncherTabPanel)) return;
		this.tabPanel = e.getObject();
		baseConsole.pushObject(tabPanel, "tabsafe");
	}
	
}
