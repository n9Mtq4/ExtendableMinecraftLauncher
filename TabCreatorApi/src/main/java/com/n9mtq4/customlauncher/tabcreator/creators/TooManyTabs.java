package com.n9mtq4.customlauncher.tabcreator.creators;

import com.n9mtq4.customlauncher.tabcreator.ui.TabTab;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.EnableActionEvent;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.EnableListener;
import com.n9mtq4.logwindow.listener.ObjectListener;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

import java.awt.Component;
import java.util.ArrayList;

/**
 * Created by will on 9/14/15 at 11:20 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public final class TooManyTabs implements EnableListener, ObjectListener, TabCreator {
	
	private TabTab tabTab;
//	private CustomTabTab tabTab;
	
	private Object tabPanel;
	private boolean tabSafe;
	private ArrayList<Object[]> unaddedTabs;
//			baseConsole.pushObject(tabPanel, "tabsafe");
	
	@Override
	public void onEnable(EnableActionEvent enableActionEvent) {
		
		this.unaddedTabs = new ArrayList<Object[]>();
		tabTab = new TabTab();
		
	}
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (tabPanel == null) {
			tryToGetTabPanel(e, baseConsole);
		}else {
			if (e.getMessage().equalsIgnoreCase("tabsafe_lowlevel") && e.getObject() instanceof LauncherTabPanel) {
				baseConsole.push(new Object[]{"ExMCL", tabTab}, "addtab_lowlevel");
				for (Object[] tab : unaddedTabs) tabTab.add((String) tab[0], (Component) tab[1]);
				this.tabSafe = true;
				baseConsole.push(tabPanel, "tabsafe");
			}
			tryCreatingTab(e, baseConsole);
		}
		
	}
	
	@Override
	public void addTab(String title, Component tab) {
		tabTab.addTab(title, tab);
	}
	
	private void tryCreatingTab(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("addtab")) return;
		if (!(e.getObject() instanceof Object[])) return;
		if (((Object[]) e.getObject()).length != 2) return;
		final Object[] objs = (Object[]) e.getObject();
		if (!(objs[0] instanceof String)) return;
		if (!(objs[1] instanceof Component)) return;
		
		if (!tabSafe) {
//			if it isn't safe, safe the tab for adding later.
			unaddedTabs.add((Object[]) e.getObject());
		}else {
			tabTab.add((String) objs[0], (Component) objs[1]);
		}
		
	}
	
	private void tryToGetTabPanel(SentObjectEvent e, BaseConsole baseConsole) {
		if (!e.getMessage().equalsIgnoreCase("launchertabpanel")) return;
		if (!(e.getObject() instanceof LauncherTabPanel)) return;
		this.tabPanel = e.getObject();
	}
	
}
