package com.n9mtq4.exmcl.gamerunner;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.EnableEvent;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.EnableListener;
import com.n9mtq4.logwindow.listener.ListenerContainer;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.Launcher;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by will on 8/5/15 at 12:31 PM.
 */
public class GameLaunchListener implements EnableListener, ObjectListener {
	
	private Launcher launcher;
	
	@Override
	public void onEnable(EnableEvent e) {
//		gets the listeners
		ArrayList<ListenerContainer> l = ReflectionHelper.getObject("listenerContainers", e.getBaseConsole());
//		gets the listener container that is handling the methods for this listener
		ListenerContainer container = e.getBaseConsole().getContainerFromAttribute(this);
//		removes the container from the list
		l.remove(container);
//		re-adds it last
		l.add(container);
		e.getBaseConsole().println("Used hack to make GameLaunchListener the last listener");
	}
	
	@Override
	public void objectReceived(ObjectEvent e, BaseConsole baseConsole) {
		
		tryLauncherGet(e, baseConsole);
		tryGameStart(e, baseConsole);
		
	}
	
	private void tryLauncherGet(ObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("minecraftlauncher")) return;
		if (!(e.getContained() instanceof Launcher)) return;
		
		launcher = (Launcher) e.getContained();
		
	}
	
	private void tryGameStart(ObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("gamelaunch")) return;
		if (!(e.getContained() instanceof ActionEvent)) return;
		
		boolean launchSuccessful = AdvancedGameLauncher.launch(launcher, baseConsole);
		
		if (launchSuccessful)
			ReflectionHelper.setBoolean(true, "canceled", e);
		else
			baseConsole.println("Failed to launch. Letting the default launcher launch.");
		
	}
	
}
