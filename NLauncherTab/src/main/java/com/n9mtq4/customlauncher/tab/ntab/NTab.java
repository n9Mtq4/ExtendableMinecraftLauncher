package com.n9mtq4.customlauncher.tab.ntab;

import net.minecraft.launcher.Launcher;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;
import net.minecraft.launcher.ui.tabs.WebsiteTab;

import javax.swing.*;

/**
 * Created by will on 7/27/15 at 7:17 PM.
 */
public class NTab extends JScrollPane {
	
	private LauncherTabPanel tabPanel;
	private Launcher mcLauncher;
	
	private WebsiteTab rootPanel;
	
	public NTab(LauncherTabPanel tabPanel, Launcher mcLauncher) {
		super();
		
		this.tabPanel = tabPanel;
		this.mcLauncher = mcLauncher;
		
		this.rootPanel = new WebsiteTab(mcLauncher);
		this.setViewportView(rootPanel);
		
		tabPanel.addTab("n9Mtq4", this);
		tabPanel.setSelectedIndex(tabPanel.indexOfTab("n9Mtq4"));
		
		rootPanel.setPage("http://n9mtq4.com/UniqueMinecraftLauncher/webview.html");
		
	}
	
}
