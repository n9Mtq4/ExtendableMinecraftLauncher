package com.n9mtq4.exmcl.uihooks;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.events.ObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by will on 7/27/15 at 5:57 PM.
 */
public final class GameLaunchHookSafe implements ObjectListener {
	
	@Override
	public final void objectReceived(final ObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("playbutton")) return;
		if (!(e.getContained() instanceof JButton)) return;
		
		final JButton playButton = (JButton) e.getContained();
		
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e1) {
				e.getInitiatingBaseConsole().push(e1, "gamelaunch");
			}
		});
		
	}
	
}
