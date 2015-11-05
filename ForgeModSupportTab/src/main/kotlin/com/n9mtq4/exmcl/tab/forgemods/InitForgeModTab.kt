package com.n9mtq4.exmcl.tab.forgemods

import com.n9mtq4.exmcl.tab.forgemods.ui.ForgeTab
import com.n9mtq4.logwindow.BaseConsole
import com.n9mtq4.logwindow.events.EnableEvent
import com.n9mtq4.logwindow.events.ObjectEvent
import com.n9mtq4.logwindow.listener.EnableListener
import com.n9mtq4.logwindow.listener.ObjectListener
import net.minecraft.launcher.ui.tabs.LauncherTabPanel
import java.io.File
import javax.swing.SwingUtilities

/**
 * Created by will on 11/3/15 at 10:52 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public final class InitForgeModTab : EnableListener, ObjectListener {
	
	override fun onEnable(enableEvent: EnableEvent) {
		
		// adds tml/ to be deleted
		enableEvent.baseConsole.push(File("tmp/"), "add to delete")
		
		// adds forge's log junk to be deleted
		val workingDir: File = File(System.getProperty("user.dir"))
		val children: Array<File> = workingDir.listFiles()
		for (child: File in children) {
			if (child.name.contains("forge_") && child.name.endsWith(".jar.log")) {
				enableEvent.baseConsole.push(child, "add to delete")
			}
		}
		
	}
	
	override fun objectReceived(objectEvent: ObjectEvent, baseConsole: BaseConsole) {
		
		if (objectEvent.message.equals("tabsafe") && objectEvent.contained is LauncherTabPanel) {
			
			SwingUtilities.invokeLater {
				val forgeTab: ForgeTab = ForgeTab(objectEvent.contained as LauncherTabPanel, baseConsole)
				baseConsole.push(arrayOf("Forge Mods", forgeTab), "addtab")
			}
			
			baseConsole.disableListenerAttribute(this)
			
		}
		
	}
	
}
