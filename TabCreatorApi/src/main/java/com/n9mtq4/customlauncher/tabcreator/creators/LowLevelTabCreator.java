package com.n9mtq4.customlauncher.tabcreator.creators;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

import java.awt.Component;

/**
 * Created by will on 8/31/15 at 11:58 PM.
 */
public final class LowLevelTabCreator implements ObjectListener, TabCreator {
	
	private static final String COMMAND_NAME = "addtab_lowlevel";
	
	private Object tabPanel;
	
	@Override
	public final void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (tabPanel == null) tryToGetTabPanel(e, baseConsole);
		else tryCreatingTab(e, baseConsole);
		
	}
	
	@Override
	public final void addTab(final String title, final Component tab) {
		((LauncherTabPanel) tabPanel).addTab(title, tab);
	}
	
	private void tryCreatingTab(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase(COMMAND_NAME)) return;
		if (!(e.getObject() instanceof Object[])) return;
		if (((Object[]) e.getObject()).length != 2) return;
		final Object[] objs = (Object[]) e.getObject();
		if (!(objs[0] instanceof String)) return;
		if (!(objs[1] instanceof Component)) return;
		
		addTab((String) objs[0], (Component) objs[1]);
		baseConsole.println("Added tab name: " + objs[0] + ", an instance of " + objs[1].getClass().getName());
		
	}
	
	private void tryToGetTabPanel(SentObjectEvent e, BaseConsole baseConsole) {
		if (!e.getMessage().equalsIgnoreCase("launchertabpanel")) return;
		if (!(e.getObject() instanceof LauncherTabPanel)) return;
		this.tabPanel = e.getObject();
		baseConsole.push(tabPanel, "tabsafe_lowlevel");
	}
	
}
