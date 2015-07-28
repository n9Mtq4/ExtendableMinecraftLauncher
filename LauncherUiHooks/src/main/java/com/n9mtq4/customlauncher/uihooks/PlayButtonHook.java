package com.n9mtq4.customlauncher.uihooks;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.ui.bottombar.PlayButtonPanel;

import javax.swing.*;

/**
 * Created by will on 7/27/15 at 6:15 PM.
 */
public class PlayButtonHook extends ConsoleListener {
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("playbuttonpanel")) return;
		if (!(e.getObject() instanceof PlayButtonPanel)) return;
		
		PlayButtonPanel playButtonPanel = (PlayButtonPanel) e.getObject();
		JButton playButton = ReflectionHelper.getObject("playButton", playButtonPanel);
		
		e.getBaseConsole().pushObject(playButton, "playbutton");
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
}
