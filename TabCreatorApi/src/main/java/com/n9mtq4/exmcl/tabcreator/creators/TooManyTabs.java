package com.n9mtq4.exmcl.tabcreator.creators;

import com.n9mtq4.exmcl.tabcreator.ui.TabTab;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.EnableEvent;
import com.n9mtq4.logwindow.events.ObjectEvent;
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
	public void onEnable(EnableEvent enableEvent) {
		
		this.unaddedTabs = new ArrayList<Object[]>();
		tabTab = new TabTab();
		
	}
	
	@Override
	public void objectReceived(ObjectEvent e, BaseConsole baseConsole) {
		
		if (tabPanel == null) {
			tryToGetTabPanel(e, baseConsole);
		}else {
			if (e.getMessage().equalsIgnoreCase("tabsafe_lowlevel") && e.getContained() instanceof LauncherTabPanel) {
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
	
	private void tryCreatingTab(ObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("addtab")) return;
		if (!(e.getContained() instanceof Object[])) return;
		if (((Object[]) e.getContained()).length != 2) return;
		final Object[] objs = (Object[]) e.getContained();
		if (!(objs[0] instanceof String)) return;
		if (!(objs[1] instanceof Component)) return;
		
		if (!tabSafe) {
//			if it isn't safe, safe the tab for adding later.
			unaddedTabs.add((Object[]) e.getContained());
		}else {
			tabTab.add((String) objs[0], (Component) objs[1]);
		}
		
	}
	
	private void tryToGetTabPanel(ObjectEvent e, BaseConsole baseConsole) {
		if (!e.getMessage().equalsIgnoreCase("launchertabpanel")) return;
		if (!(e.getContained() instanceof LauncherTabPanel)) return;
		this.tabPanel = e.getContained();
	}
	
}
