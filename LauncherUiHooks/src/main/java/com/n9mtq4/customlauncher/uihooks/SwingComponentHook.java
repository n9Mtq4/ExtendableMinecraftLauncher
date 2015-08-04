package com.n9mtq4.customlauncher.uihooks;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.annotation.Async;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.SwingUserInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by will on 7/28/15 at 2:18 PM.
 */
public class SwingComponentHook extends ConsoleListener {
	
	@Async
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("swinguserinterface")) return;
		if (!(e.getObject() instanceof SwingUserInterface)) return;
		
		JFrame frame = (JFrame) ReflectionHelper.getObject("frame", (SwingUserInterface) e.getObject());
		
		ArrayList<Component> components = getAllComponents(frame);
		for (Component component : components) {
			e.getBaseConsole().pushObject(component, "allcomponents");
		}
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
	public static ArrayList<Component> getAllComponents(final Container c) {
		Component[] comps = c.getComponents();
		ArrayList<Component> compList = new ArrayList<Component>();
		for (Component comp : comps) {
			compList.add(comp);
			if (comp instanceof Container)
				compList.addAll(getAllComponents((Container) comp));
		}
		return compList;
	}
	
}
