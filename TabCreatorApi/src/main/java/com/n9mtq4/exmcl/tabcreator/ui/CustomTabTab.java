package com.n9mtq4.exmcl.tabcreator.ui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;

/**
 * Created by will on 9/25/15 at 9:08 AM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public class CustomTabTab extends JPanel {
	
	private final ArrayList<Object[]> tabs;
	private int selected;
	
	private JPanel tab;
	private JComboBox tabNames;
	
	public CustomTabTab() {
		this.tabs = new ArrayList<Object[]>();
		this.selected = 0;
		this.tabNames = new JComboBox(new TabComboBoxModel());
	}
	
	private void fireStateChange() {
		((Component) tabs.get(selected)[1]).setVisible(false);
		this.selected = getSelectedIndex();
		((Component) tabs.get(selected)[1]).setVisible(true);
	}
	
	public void addTab(String title, Component component) {
		addTab(new Object[]{title, component});
	}
	
	public void addTab(Object[] object) {
		if (object.length != 2) return;
		if (!(object[0] instanceof String)) return;
		if (!(object[1] instanceof Component)) return;
		tabs.add(object);
		tab.add((Component) object[1], BorderLayout.CENTER);
		((Component) object[1]).setVisible(false);
		fireStateChange();
	}
	
	private int getSelectedIndex() {
		String name = (String) ((Object[]) tabNames.getSelectedItem())[0];
		for (int i = 0; i < tabs.size(); i++) {
			if (((String) tabs.get(i)[0]).equals(name)) return i;
		}
		return -1;
	}
	
	private class TabComboBoxModel extends DefaultComboBoxModel {
		
		public TabComboBoxModel() {
		}
		
		@Override
		public int getSize() {
			return CustomTabTab.this.tabs.size();
		}
		
		@Override
		public Object getElementAt(int index) {
			return tabs.get(index)[0];
		}
		
	}
	
}
