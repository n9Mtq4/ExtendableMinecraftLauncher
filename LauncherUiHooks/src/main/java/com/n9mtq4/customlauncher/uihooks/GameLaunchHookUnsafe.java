package com.n9mtq4.customlauncher.uihooks;

import com.n9mtq4.console.lib.BaseConsole;
import com.n9mtq4.console.lib.ConsoleListener;
import com.n9mtq4.console.lib.events.AdditionActionEvent;
import com.n9mtq4.console.lib.events.ConsoleActionEvent;
import com.n9mtq4.console.lib.events.SentObjectEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by will on 7/28/15 at 3:53 PM.<br>
 * This class does some advanced and sneaky things to let
 * us process the play button action BEFORE the minecraft launcher
 * can. Because swing randomizes the order of ActionListeners we 
 * can't just add us the first one in the list. We have to
 * remove all other listeners, while keeping a copy of the
 * listeners for us. When our ActionListener gets sent the event,
 * we send it to all the BaseConsole listener and then if none of them
 * set the canceled flag to true, then we will send it to the listeners
 * we removed from before.
 */
public class GameLaunchHookUnsafe extends ConsoleListener implements ActionListener {
	
	private ActionListener[] listeners;
	private BaseConsole baseConsole;
	protected SentObjectEvent sentObjectEvent;
	
	@Override
	public void onAddition(AdditionActionEvent e) {
		e.getBaseConsole().addListener(new DefaultGameLaunchEventCapture(this));
	}
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equalsIgnoreCase("playbutton")) return;
		if (!(e.getObject() instanceof JButton)) return;
		
		this.baseConsole = e.getBaseConsole();
		
		JButton playButton = (JButton) e.getObject();
		this.listeners = playButton.getActionListeners();
//		remove all the action listeners on the button.
//		we will handle them
		for (ActionListener listener : listeners) {
			playButton.removeActionListener(listener);
		}
		
//		now add us as the only action listener
		playButton.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
//		send the event to people listening with the BaseConsole first.
		baseConsole.pushObject(e, "gamelaunch");
		
		if (sentObjectEvent == null) {
			baseConsole.println("ERROR. SOMETHING HAPPENED WITH RECAPTURING THE SENTOBJECTEVENT.\n" +
					"WE WILL CONTINUE WITH SENDING THE EVENT TO THE ACTIONLISTENERS");
		}
		
//		makes sure that we send the button to mojang's listeners only if it hasn't canceled,
//		but we also have to take into account the EventCapture failing.
		if ((sentObjectEvent == null) || /*we know ? != null*/ (!sentObjectEvent.isCanceled())) {
//			then we can let mojang's launcher handle it if necessary.
			for (ActionListener listener : listeners) {
				listener.actionPerformed(e);
			}
		}
		
	}
	
	/**
	 * This class captures the SentObjectEvent and gives it to the parent.
	 * This is so we can test if a listener has canceled the game launch event
	 * */
	public static class DefaultGameLaunchEventCapture extends ConsoleListener {
		
		private final GameLaunchHookUnsafe parent;
		
		public DefaultGameLaunchEventCapture(GameLaunchHookUnsafe parent) {
			this.parent = parent;
			parent.sentObjectEvent = null;
		}
		
		@Override
		public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
			
			if (!e.getMessage().equalsIgnoreCase("gamelaunch")) return;
			if (!(e.getObject() instanceof ActionEvent)) return;
			
			parent.sentObjectEvent = e;
			
		}
		
		@Override
		public void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
			
		}
		
	}
	
}
