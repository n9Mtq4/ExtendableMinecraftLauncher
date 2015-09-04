package com.n9mtq4.customlauncher.echo;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ConsoleActionEvent;
import com.n9mtq4.logwindow.events.EnableActionEvent;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.EnableListener;
import com.n9mtq4.logwindow.listener.ListenerContainer;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.listener.StringListener;
import com.n9mtq4.reflection.ReflectionHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by will on 3/31/15.<br>
 * This listener is for debugging purposes.
 * It takes any String or Object that is pushed to the
 * BaseConsole and prints it out so I can see it.
 */
public final class EchoListener implements EnableListener, StringListener, ObjectListener {
	
	/**
	 * It is important that Echoer is the first listener, so use ReflectionHelper to 
	 * accomplish that goal.
	 * */
	@Override
	public final void onEnable(EnableActionEvent e) {
//		gets the listeners
		ArrayList<ListenerContainer> l = ReflectionHelper.getObject("listenerContainers", e.getBaseConsole());
//		gets the listener container that is handling the methods for this listener
		ListenerContainer container = e.getBaseConsole().getContainerFromAttribute(this);
//		removes the container from the list
		l.remove(container);
//		re-adds it first
		l.add(0, container);
		e.getBaseConsole().println("Used hack to make Echoer the first listener");
	}
	
	/**
	 * Prints the string sent to the BaseConsole
	 * */
	@Override
	public final void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		baseConsole.println(consoleActionEvent.getCommand().getText());
		
	}
	
	/**
	 * Prints the object sent to the BaseConsole
	 * */
	@Override
	public final void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (e.getObject() == null) {
			e.getBaseConsole().println("Object: " + e.getMessage() + " | (null)");
			return;
		}
		
		if (e.getObject() instanceof Object[]) {
			e.getBaseConsole().println("Object: " + e.getMessage() + " | (" + Arrays.toString((Object[]) e.getObject()) + ")");
		}else if (e.getObject() instanceof Collection) {
			e.getBaseConsole().println("Object: " + e.getMessage() + " | (" + Arrays.toString(((Collection) e.getObject()).toArray()) + ")");
		}else {
			e.getBaseConsole().println("Object: " + e.getMessage() + " | (" + e.getObject().toString() + ")");
		}
		
	}
	
	
}
