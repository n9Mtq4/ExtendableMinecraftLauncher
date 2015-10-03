package com.n9mtq4.exmcl.tab.worldmanager.ui;

import net.minecraft.launcher.Launcher;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.io.File;

/**
 * Created by will on 9/2/15 at 11:20 PM.
 */
public final class WorldTable extends JTable {
	
	private final WorldTableModel model;
	
	public WorldTable(Launcher launcher) {
		super();
		this.model = new WorldTableModel(launcher);
		setModel(model);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setFillsViewportHeight(true);
		getTableHeader().setReorderingAllowed(false);
	}
	
	public final File getSelectedWorld() {
		if (getSelectedColumn() == -1) return null;
		return new File(model.getSavesDir(), (String) model.getValueAt(getSelectedRow(), 1));
	}
	
	public final void refresh() {
		model.refresh();
	}
	
	public final WorldTableModel getWorldTableModel() {
		return model;
	}
	
}
