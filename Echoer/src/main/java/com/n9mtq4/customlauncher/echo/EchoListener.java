package com.n9mtq4.customlauncher.echo;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.EnableActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.reflection.ReflectionHelper;

import java.util.ArrayList;

/**
 * Created by will on 3/31/15.
 */
public class EchoListener extends ConsoleListener {
	
	@Override
	public void onEnable(EnableActionEvent e) {
//		gets the listeners
		ArrayList<ConsoleListener> l = e.getBaseConsole().getListeners();
//		removes this listener from the list
		l.remove(this);
//		re-adds it first
		l.add(0, this);
		ReflectionHelper.setObject(l, "listeners", e.getBaseConsole(), BaseConsole.class);
		e.getBaseConsole().println("Used hack to make Echoer the first listener");
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		baseConsole.println(consoleActionEvent.getCommand().getText());
		
	}
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		e.getBaseConsole().println("Object: " + e.getMessage() + " | (" + e.getObject().getClass().getSimpleName() + ")");
		
	}
	
	
}
