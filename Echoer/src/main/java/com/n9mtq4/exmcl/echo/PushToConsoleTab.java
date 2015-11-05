package com.n9mtq4.exmcl.echo;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.ui.ConsoleUI;
import com.n9mtq4.logwindow.utils.Colour;
import net.minecraft.launcher.ui.tabs.ConsoleTab;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

/**
 * Created by will on 9/3/15 at 10:15 PM.
 */
public final class PushToConsoleTab implements ConsoleUI, ObjectListener {
	
//	has to be object because the Minecraft launcher hasn't been added yet.
	private Object consoleTab = null;
	private GetConsoleTab getConsoleTab;
	private BaseConsole baseConsole;
	
	@Override
	public final void init() {
		
		getConsoleTab = new GetConsoleTab();
		baseConsole.addListenerAttribute(getConsoleTab);
		
	}
	
	@Override
	public final void dispose() {
		
	}
	
	@Override
	public void print(Object o, Colour colour) {
		if (consoleTab == null) return;
		((ConsoleTab) consoleTab).print(String.valueOf(o));
	}
	
	@Override
	public final void objectReceived(ObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("args") || !(e.getContained() instanceof String[])) return;
		
		String[] args = (String[]) e.getContained();
		if (contains(args, "consoletabdebug")) {
			this.baseConsole = baseConsole;
			baseConsole.addConsoleUi(this);
		}
		baseConsole.disableListenerAttribute(this);
		
	}
	
	private static boolean contains(String[] args, String key) {
		for (String s : args) {
			if (s.equals(key)) return true;
		}
		return false;
	}
	
	private class GetConsoleTab implements ObjectListener {
		@Override
		public final void objectReceived(ObjectEvent e, BaseConsole baseConsole) {
			
			if (!e.getMessage().equals("launchertabpanel") || !(e.getContained() instanceof LauncherTabPanel)) return;
			
			LauncherTabPanel panel = (LauncherTabPanel) e.getContained();
			PushToConsoleTab.this.consoleTab = panel.getConsole();
			
		}
	}
	
}
