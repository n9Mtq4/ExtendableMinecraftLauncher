package com.n9mtq4.customlauncher.tab.forgemods;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.customlauncher.tab.forgemods.data.ModData;
import com.n9mtq4.customlauncher.tab.forgemods.data.ModProfile;
import com.n9mtq4.customlauncher.tab.forgemods.utils.ForgeModManager;
import net.minecraft.launcher.Launcher;

import java.awt.event.ActionEvent;

/**
 * Created by will on 7/29/15 at 2:21 PM.
 * */
public class GameStartHook extends ConsoleListener {
	
	private Launcher launcher;
	private ModData modData;
	
	public GameStartHook(Launcher launcher, ModData modData) {
		this.launcher = launcher;
		this.modData = modData;
	}
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("gamelaunch")) return;
		if (!(e.getObject() instanceof ActionEvent)) return;
		
		ForgeModManager.cleanup(launcher);
		ModProfile modProfile = modData.profiles.get(modData.selectedProfile);
		ForgeModManager.copyToMods(launcher, modProfile);
		
//		baseConsole.dispose();
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
}
