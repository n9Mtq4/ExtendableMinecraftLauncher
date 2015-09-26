package com.n9mtq4.customlauncher.uihooks;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.ui.bottombar.PlayButtonPanel;

import javax.swing.JButton;

/**
 * Created by will on 7/27/15 at 6:15 PM.
 */
public class PlayButtonHook implements ObjectListener {
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("playbuttonpanel")) return;
		if (!(e.getObject() instanceof PlayButtonPanel)) return;
		
		PlayButtonPanel playButtonPanel = (PlayButtonPanel) e.getObject();
		JButton playButton = ReflectionHelper.getObject("playButton", playButtonPanel);
		
		e.getBaseConsole().push(playButton, "playbutton");
		
	}
	
}
