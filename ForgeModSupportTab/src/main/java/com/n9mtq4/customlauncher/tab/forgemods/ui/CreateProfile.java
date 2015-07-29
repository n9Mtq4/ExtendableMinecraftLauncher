package com.n9mtq4.customlauncher.tab.forgemods.ui;

import net.minecraft.launcher.Launcher;

/**
 * Created by will on 7/29/15 at 11:02 AM.
 */
public class CreateProfile {
	
	private ForgeTab forgeTab;
	private Launcher launcher;
	
	public CreateProfile(ForgeTab forgeTab, Launcher launcher) {
		this.forgeTab = forgeTab;
		this.launcher = launcher;
	}
	
	public void onCreate() {
		
		forgeTab.refreshList();
		
	}
	
}
