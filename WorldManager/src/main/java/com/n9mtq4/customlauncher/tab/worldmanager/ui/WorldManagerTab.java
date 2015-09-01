package com.n9mtq4.customlauncher.tab.worldmanager.ui;

import com.n9mtq4.logwindow.BaseConsole;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

import javax.swing.*;

/**
 * Created by will on 8/31/15 at 11:42 PM.
 */
public class WorldManagerTab extends JSplitPane {
	
	private final LauncherTabPanel parent;
	private final BaseConsole baseConsole;
	
	public WorldManagerTab(LauncherTabPanel parent, BaseConsole baseConsole) {
		super();
		this.parent = parent;
		this.baseConsole = baseConsole;
	}
	
}
