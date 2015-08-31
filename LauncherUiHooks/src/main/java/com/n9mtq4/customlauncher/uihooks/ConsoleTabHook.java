package com.n9mtq4.customlauncher.uihooks;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import net.minecraft.launcher.ui.tabs.ConsoleTab;

/**
 * Created by will on 8/4/15 at 10:16 PM.
 */
public class ConsoleTabHook implements ObjectListener {
	
	private ConsoleTab consoleTab;
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (consoleTab == null) {
			if (e.getMessage().equals("allcomponents") && (e.getObject() instanceof ConsoleTab)) {
				consoleTab = (ConsoleTab) e.getObject();
			}
		}else {
			if (e.getMessage().equals("print") && (e.getObject() instanceof String)) {
				consoleTab.print((String) e.getObject());
			}else if (e.getMessage().equals("println") && (e.getObject() instanceof String)) {
				consoleTab.print(((String) e.getObject()) + "\n");
			}
		}
		
	}
	
}
