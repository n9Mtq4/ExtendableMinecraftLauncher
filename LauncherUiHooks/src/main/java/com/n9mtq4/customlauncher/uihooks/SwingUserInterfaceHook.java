package com.n9mtq4.customlauncher.uihooks;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.Launcher;
import net.minecraft.launcher.SwingUserInterface;

/**
 * Created by will on 7/27/15 at 5:58 PM.
 */
public class SwingUserInterfaceHook extends ConsoleListener {
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("minecraftlauncher")) return;
		if (!(e.getObject() instanceof Launcher)) return;
		
		SwingUserInterface ui = ReflectionHelper.getObject("userInterface", e.getObject());
		
		e.getBaseConsole().pushObject(ui, "swinguserinterface");
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
}
