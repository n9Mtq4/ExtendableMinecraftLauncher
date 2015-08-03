package com.n9mtq4.customlauncher.echo;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.EnableActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.reflection.ReflectionHelper;

import java.util.ArrayList;

/**
 * Created by will on 3/31/15.<br>
 * This listener is for debugging purposes.
 * It takes any String or Object that is pushed to the
 * BaseConsole and prints it out so I can see it.
 */
public class EchoListener extends ConsoleListener {
	
	/**
	 * It is important that Echoer is the first listener, so use ReflectionHelper to 
	 * accomplish that goal.
	 * */
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
	
	/**
	 * Prints the string sent to the BaseConsole
	 * */
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		baseConsole.println(consoleActionEvent.getCommand().getText());
		
	}
	
	/**
	 * Prints the object sent to the BaseConsole
	 * */
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		e.getBaseConsole().println("Object: " + e.getMessage() + " | (" + e.getObject().getClass().getSimpleName() + ")");
		
	}
	
	
}
