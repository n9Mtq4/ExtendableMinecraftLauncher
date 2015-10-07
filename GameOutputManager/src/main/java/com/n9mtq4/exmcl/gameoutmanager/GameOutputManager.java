package com.n9mtq4.exmcl.gameoutmanager;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.AdditionEvent;
import com.n9mtq4.logwindow.events.EnableEvent;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.AdditionListener;
import com.n9mtq4.logwindow.listener.EnableListener;
import com.n9mtq4.logwindow.listener.ObjectListener;
import net.minecraft.launcher.ui.tabs.GameOutputTab;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import java.awt.Component;

/**
 * Created by will on 10/5/15 at 11:34 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public class GameOutputManager implements AdditionListener, EnableListener, ObjectListener {
	
	private Object launcherTabPanel;
	private BaseConsole parent;
	private JTabbedPane gameOutputTabs;
	private boolean tabSafe;
	
	@Override
	public void onAddition(AdditionEvent additionEvent) {
		this.tabSafe = false;
	}
	
	@Override
	public void onEnable(EnableEvent enableEvent) {
		this.parent = enableEvent.getBaseConsole();
	}
	
	@Override
	public void objectReceived(ObjectEvent objectEvent, BaseConsole baseConsole) {
		
		if (launcherTabPanel == null) {
			tryGettingTabPanel(objectEvent, baseConsole);
		}else if (objectEvent.getMessage().equals("tabsafe_lowlevel") && objectEvent.getObject() instanceof LauncherTabPanel) {
			tabSafe = true;
		}else {
			
			if (!objectEvent.getMessage().equals("swinguserinterface showGameOutputTab")) return;
			
			fireNewGameOutTabEvent(objectEvent);
			
		}
		
	}
	
	private void addNewTab(String title, Component component) {
		gameOutputTabs.add(cropTabName(title), component);
	}
	
	private void addGameOutputTabs() {
		if (gameOutputTabs == null) gameOutputTabs = new JTabbedPane();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Object[] tabInfo = {"Game Output", gameOutputTabs};
				parent.push(tabInfo, "addtab_lowlevel");
			}
		});
	}
	
	private void tryGettingTabPanel(ObjectEvent e, BaseConsole baseConsole) {
		if (!e.getMessage().equalsIgnoreCase("launchertabpanel")) return;
		if (!(e.getObject() instanceof LauncherTabPanel)) return;
		this.launcherTabPanel = e.getObject();
	}
	
	private void fireNewGameOutTabEvent(ObjectEvent event) {
		
		if (gameOutputTabs == null && tabSafe) addGameOutputTabs();
		
		LauncherTabPanel tabPanel = (LauncherTabPanel) launcherTabPanel;
		
		for (int i = 0; i < tabPanel.getTabCount(); i++) {
			Component tab = tabPanel.getComponentAt(i);
			if (tab instanceof GameOutputTab) {
				addNewTab(tabPanel.getTitleAt(i), tab);
//				tabPanel.removeTabAt(i);
			}
		}
		
	}
	
	private static String cropTabName(String title) {
		return title.substring(title.indexOf("(") + 1, title.indexOf(")"));
	}
	
}
