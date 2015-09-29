package com.n9mtq4.exmcl.tab.settings;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Created by will on 7/27/15 at 6:41 PM.
 */
public final class SettingsTab extends JScrollPane {
	
	private JPanel panel;
	
	public SettingsTab() {
		super();
		
		this.panel = new JPanel();
		this.setViewportView(panel);
		
		gui();
		
	}
	
	private void gui() {
		
	}
	
}
