package com.n9mtq4.exmcl.tab.worldmanager

import com.n9mtq4.exmcl.tab.worldmanager.ui.WorldManagerTab
import com.n9mtq4.logwindow.BaseConsole
import com.n9mtq4.logwindow.events.ObjectEvent
import com.n9mtq4.logwindow.listener.ObjectListener
import net.minecraft.launcher.ui.tabs.LauncherTabPanel
import javax.swing.SwingUtilities

/**
 * Created by will on 11/5/15 at 5:23 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public final class InitWorldManagerTab : ObjectListener {
	
	override fun objectReceived(objectEvent: ObjectEvent, baseConsole: BaseConsole) {
		
		if (!objectEvent.message.equals("tabsafe")) return
		if (objectEvent.contained !is LauncherTabPanel) return
		
		SwingUtilities.invokeLater { 
			val worldManagerTab = WorldManagerTab(objectEvent.contained as LauncherTabPanel, objectEvent.initiatingBaseConsole)
			objectEvent.initiatingBaseConsole.push(worldManagerTab.constructInfo(), "addtab")
		}
		
	}
	
}
