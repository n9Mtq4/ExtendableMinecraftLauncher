package com.n9mtq4.customlauncher.tab.mods.table;

import com.n9mtq4.customlauncher.tab.mods.ModTab;
import com.n9mtq4.customlauncher.tab.mods.data.ModData;

import javax.swing.*;

/**
 * Created by will on 7/28/15 at 11:25 AM.
 */
public final class ModsTable extends JTable {
	
	private ModData modData;
	private ModTab modTab;
	private ModsTableModel model;
	
	public ModsTable(ModData modData, ModTab modTab) {
		
		super();
		
		this.modData = modData;
		this.modTab = modTab;
		this.model = new ModsTableModel();
		
		setModel(model);
		
		getTableHeader().setReorderingAllowed(false);
		
	}
	
	public final void refreshModel() {
		
		model.refresh();
		
	}
	
	public final void refreshTab() {
		
		modTab.refresh();
		
	}
	
	public final ModData getModData() {
		return modData;
	}
	
	public final ModTab getModTab() {
		return modTab;
	}
	
	@Override
	public final ModsTableModel getModel() {
		return model;
	}
	
}
