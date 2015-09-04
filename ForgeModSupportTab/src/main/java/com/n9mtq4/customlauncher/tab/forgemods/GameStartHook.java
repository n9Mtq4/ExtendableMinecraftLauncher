package com.n9mtq4.customlauncher.tab.forgemods;

import com.n9mtq4.customlauncher.tab.forgemods.data.ModData;
import com.n9mtq4.customlauncher.tab.forgemods.data.ModProfile;
import com.n9mtq4.customlauncher.tab.forgemods.utils.ForgeModManager;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import net.minecraft.launcher.Launcher;

import java.awt.event.ActionEvent;

/**
 * Created by will on 7/29/15 at 2:21 PM.<br>
 * This hooks into the game start event.
 * When the game starts, we run the ForgeModManager class.
 * */
public final class GameStartHook implements ObjectListener {
	
	private final Launcher launcher;
	private ModData modData;
	
	public GameStartHook(Launcher launcher, ModData modData) {
		this.launcher = launcher;
		this.modData = modData;
	}
	
	@Override
	public final void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("gamelaunch")) return;
		if (!(e.getObject() instanceof ActionEvent)) return;
		
		ForgeModManager.cleanup(launcher);
		ModProfile modProfile = modData.profiles.get(modData.selectedProfile);
		ForgeModManager.copyToMods(launcher, modProfile);
		
//		baseConsole.dispose();
		
	}
	
}
