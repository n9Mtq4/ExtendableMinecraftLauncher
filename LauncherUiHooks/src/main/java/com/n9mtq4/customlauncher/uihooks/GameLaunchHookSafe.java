package com.n9mtq4.customlauncher.uihooks;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by will on 7/27/15 at 5:57 PM.
 */
public class GameLaunchHookSafe extends ConsoleListener {
	
	@Override
	public void objectReceived(final SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("playbutton")) return;
		if (!(e.getObject() instanceof JButton)) return;
		
		final JButton playButton = (JButton) e.getObject();
		
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e1) {
				e.getBaseConsole().push("gamelaunch");
			}
		});
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
}
