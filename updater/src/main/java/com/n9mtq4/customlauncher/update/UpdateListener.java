package com.n9mtq4.customlauncher.update;

import com.n9mtq4.logwindow.events.EnableActionEvent;
import com.n9mtq4.logwindow.listener.EnableListener;

/**
 * Created by will on 8/1/15 at 9:34 PM.
 */
public class UpdateListener implements EnableListener {
	
	@Override
	public void onEnable(EnableActionEvent e) {
		
		new Updater();
		e.getBaseConsole().disableListenerAttribute(this);
		
	}
	
}
