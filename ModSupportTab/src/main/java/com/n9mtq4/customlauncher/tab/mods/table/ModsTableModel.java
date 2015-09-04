package com.n9mtq4.customlauncher.tab.mods.table;

import javax.swing.table.DefaultTableModel;

/**
 * Created by will on 7/28/15 at 11:22 AM.
 */
public final class ModsTableModel extends DefaultTableModel {
	
	@Override
	public final boolean isCellEditable(int row, int column) {
		return super.isCellEditable(row, column);
	}
	
	public final void refresh() {
		
	}
	
}
