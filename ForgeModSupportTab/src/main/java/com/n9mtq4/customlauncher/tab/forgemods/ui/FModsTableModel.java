package com.n9mtq4.customlauncher.tab.forgemods.ui;

import javax.swing.table.DefaultTableModel;

/**
 * Created by will on 7/28/15 at 11:22 AM.
 */
public class FModsTableModel extends DefaultTableModel {
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return super.isCellEditable(row, column);
	}
	
	public void refresh() {
		
	}
	
}
