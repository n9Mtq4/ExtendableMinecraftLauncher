package com.n9mtq4.customlauncher.gamerunner;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.EnableActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.Launcher;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by will on 8/5/15 at 12:31 PM.
 */
public class GameLaunchListener extends ConsoleListener {
	
	private Launcher launcher;
	
	@Override
	public void onEnable(EnableActionEvent e) {
//		gets the listeners
		ArrayList<ConsoleListener> l = ReflectionHelper.getObject("listeners", e.getBaseConsole());
//		removes this listener from the list
		l.remove(this);
//		re-adds it first
		l.add(this);
		e.getBaseConsole().println("Used hack to make GameLaunchListener the last listener");
	}
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		tryLauncherGet(e, baseConsole);
		tryGameStart(e, baseConsole);
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
	private void tryLauncherGet(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("minecraftlauncher")) return;
		if (!(e.getObject() instanceof Launcher)) return;
		
		launcher = (Launcher) e.getObject();
		
	}
	
	private void tryGameStart(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("gamelaunch")) return;
		if (!(e.getObject() instanceof ActionEvent)) return;
		
		boolean launchSuccessful = AdvancedGameLauncher.launch(launcher, baseConsole);
		
		if (launchSuccessful)
			ReflectionHelper.setBoolean(true, "canceled", e);
		else
			baseConsole.println("Failed to launch. Letting the default launcher launch.");
		
	}
	
}
