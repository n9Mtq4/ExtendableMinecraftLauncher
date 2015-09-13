package com.n9mtq4.customlauncher.tab.forgemods;

import com.n9mtq4.customlauncher.tab.forgemods.ui.ForgeTab;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.EnableActionEvent;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.EnableListener;
import com.n9mtq4.logwindow.listener.ObjectListener;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

import javax.swing.*;
import java.io.File;

/**
 * Created by will on 7/28/15 at 10:16 PM.<br>
 * This listener creates the ForgeModTab when the launcher
 * is started.
 */
public final class InitForgeModTab implements EnableListener, ObjectListener {
	
	@Override
	public void onEnable(EnableActionEvent enableActionEvent) {
		enableActionEvent.getBaseConsole().pushObject(new File("tmp/"), "add to delete");
	}
	
	@Override
	public final void objectReceived(final SentObjectEvent e, final BaseConsole baseConsole) {
		
		if (e.getMessage().equalsIgnoreCase("tabsafe") && e.getObject() instanceof LauncherTabPanel) {
			
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					ForgeTab forgeTab = new ForgeTab((LauncherTabPanel) e.getObject(), baseConsole);
					baseConsole.pushObject(new Object[]{"Forge Mods", forgeTab}, "addtab");
				}
			});
			
			baseConsole.disableListenerAttribute(this);
			
		}
		
	}
	
}
