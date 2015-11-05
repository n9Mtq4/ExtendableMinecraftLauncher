package com.n9mtq4.exmcl.tab.forgemods

import com.n9mtq4.exmcl.tab.forgemods.data.ModData
import com.n9mtq4.exmcl.tab.forgemods.data.ModProfile
import com.n9mtq4.exmcl.tab.forgemods.utils.ForgeModManager
import com.n9mtq4.logwindow.BaseConsole
import com.n9mtq4.logwindow.events.ObjectEvent
import com.n9mtq4.logwindow.listener.ObjectListener
import net.minecraft.launcher.Launcher
import java.awt.event.ActionEvent

/**
 * Created by will on 11/3/15 at 11:16 PM.
 *
 * This hooks into the game start event.
 * When the game starts, we run the ForgeModManager class.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public final class GameStartHook(launcher: Launcher, modData: ModData) : ObjectListener {
	
	private val launcher: Launcher
	private var modData: ModData
	
	init {
		this.launcher = launcher
		this.modData = modData
	}
	
	override fun objectReceived(objectEvent: ObjectEvent, baseConsole: BaseConsole) {
		
		if (!objectEvent.message.equals("gamelaunch")) return
		if (objectEvent.contained !is ActionEvent) return
		
		ForgeModManager.cleanup(launcher)
		val modProfile: ModProfile = modData.profiles[modData.selectedProfile]
		ForgeModManager.copyToMods(launcher, modProfile)
		
	}
	
}
