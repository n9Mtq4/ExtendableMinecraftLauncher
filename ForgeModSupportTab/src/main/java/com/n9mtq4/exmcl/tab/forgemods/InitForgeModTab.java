package com.n9mtq4.exmcl.tab.forgemods;

import com.n9mtq4.exmcl.tab.forgemods.ui.ForgeTab;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.EnableActionEvent;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.EnableListener;
import com.n9mtq4.logwindow.listener.ObjectListener;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

import javax.swing.SwingUtilities;
import java.io.File;

/**
 * Created by will on 7/28/15 at 10:16 PM.<br>
 * This listener creates the ForgeModTab when the launcher
 * is started.
 */
public final class InitForgeModTab implements EnableListener, ObjectListener {
	
	@Override
	public void onEnable(EnableActionEvent enableActionEvent) {
		
		// adds tmp/ to be deleted
		enableActionEvent.getBaseConsole().push(new File("tmp/"), "add to delete");
		
		// adds forge's log junk to be deleted
		File workingDir = new File(System.getProperty("user.dir"));
		File[] children = workingDir.listFiles();
		if (children == null) return;
		for (File child : children) {
			if (child.getName().contains("forge_") && child.getName().endsWith(".jar.log")) {
				enableActionEvent.getBaseConsole().push(child, "add to delete");
			}
		}
	}
	
	@Override
	public final void objectReceived(final SentObjectEvent e, final BaseConsole baseConsole) {
		
		if (e.getMessage().equals("tabsafe") && e.getObject() instanceof LauncherTabPanel) {
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					ForgeTab forgeTab = new ForgeTab((LauncherTabPanel) e.getObject(), baseConsole);
					baseConsole.push(new Object[]{"Forge Mods", forgeTab}, "addtab");
				}
			});
			
			baseConsole.disableListenerAttribute(this);
			
		}
		
	}
	
}
