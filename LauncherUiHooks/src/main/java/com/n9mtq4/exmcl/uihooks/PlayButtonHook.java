package com.n9mtq4.exmcl.uihooks;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.reflection.ReflectionHelper;
import net.minecraft.launcher.ui.bottombar.PlayButtonPanel;

import javax.swing.JButton;

/**
 * Created by will on 7/27/15 at 6:15 PM.
 */
public class PlayButtonHook implements ObjectListener {
	
	@Override
	public void objectReceived(ObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("playbuttonpanel")) return;
		if (!(e.getContained() instanceof PlayButtonPanel)) return;
		
		PlayButtonPanel playButtonPanel = (PlayButtonPanel) e.getContained();
		JButton playButton = ReflectionHelper.getObject("playButton", playButtonPanel);
		
		e.getInitiatingBaseConsole().push(playButton, "playbutton");
		
	}
	
}
