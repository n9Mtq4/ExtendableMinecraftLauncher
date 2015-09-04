package com.n9mtq4.customlauncher.echo;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.ui.ConsoleUI;
import com.n9mtq4.logwindow.utils.Colour;
import net.minecraft.launcher.ui.tabs.ConsoleTab;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

import java.awt.*;

/**
 * Created by will on 9/3/15 at 10:15 PM.
 */
public class PushToConsoleTab implements ConsoleUI, ObjectListener {
	
//	has to be object because the Minecraft launcher hasn't been added yet.
	private Object consoleTab = null;
	private GetConsoleTab getConsoleTab;
	private BaseConsole baseConsole;
	
	@Override
	public void init() {
		
		getConsoleTab = new GetConsoleTab();
		baseConsole.addListenerAttribute(getConsoleTab);
		
	}
	
	@Override
	public void dispose() {
		
	}
	
	@Override
	public void print(String s, Colour colour) {
		if (consoleTab == null) return;
		((ConsoleTab) consoleTab).print(s);
	}
	
	@Override
	public void printImage(Image image) {
//		nothing
	}
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("args") || !(e.getObject() instanceof String[])) return;
		
		String[] args = (String[]) e.getObject();
		if (contains(args, "consoletabdebug")) {
			this.baseConsole = baseConsole;
			baseConsole.addGui(this);
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
		public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
			
			if (!e.getMessage().equals("launchertabpanel") || !(e.getObject() instanceof LauncherTabPanel)) return;
			
			LauncherTabPanel panel = (LauncherTabPanel) e.getObject();
			PushToConsoleTab.this.consoleTab = panel.getConsole();
			
		}
	}
	
}
