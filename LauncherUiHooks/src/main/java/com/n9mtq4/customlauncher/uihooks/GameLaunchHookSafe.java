package com.n9mtq4.customlauncher.uihooks;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by will on 7/27/15 at 5:57 PM.
 */
public final class GameLaunchHookSafe implements ObjectListener {
	
	@Override
	public final void objectReceived(final SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("playbutton")) return;
		if (!(e.getObject() instanceof JButton)) return;
		
		final JButton playButton = (JButton) e.getObject();
		
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e1) {
				e.getBaseConsole().pushObject(e1, "gamelaunch");
			}
		});
		
	}
	
}
