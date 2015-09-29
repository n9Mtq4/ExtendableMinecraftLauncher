package com.n9mtq4.exmcl.tab.worldmanager.ui;

import net.minecraft.launcher.Launcher;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

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
	}
	
}
