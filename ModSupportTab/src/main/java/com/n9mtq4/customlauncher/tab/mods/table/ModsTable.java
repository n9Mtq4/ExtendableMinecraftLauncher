package com.n9mtq4.customlauncher.tab.mods.table;

import com.n9mtq4.customlauncher.tab.mods.ModTab;
import com.n9mtq4.customlauncher.tab.mods.data.ModData;

import javax.swing.*;
/**
 * Created by will on 7/28/15 at 11:25 AM.
 */
public class ModsTable extends JTable {
	
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
	
	public void refreshModel() {
		
		model.refresh();
		
	}
	
	public void refreshTab() {
		
		modTab.refresh();
		
	}
	
	public ModData getModData() {
		return modData;
	}
	
	public ModTab getModTab() {
		return modTab;
	}
	
	@Override
	public ModsTableModel getModel() {
		return model;
	}
	
}
