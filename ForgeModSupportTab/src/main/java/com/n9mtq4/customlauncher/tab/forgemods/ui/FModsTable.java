package com.n9mtq4.customlauncher.tab.forgemods.ui;

import com.n9mtq4.customlauncher.tab.forgemods.data.ModData;

import javax.swing.*;

/**
 * Created by will on 7/28/15 at 11:25 AM.
 */
public class FModsTable extends JTable {
	
	private ModData modData;
	private ForgeTab forgeTab;
	private FModsTableModel model;
	
	public FModsTable(ModData modData, ForgeTab forgeTab) {
		
		super();
		
		this.modData = modData;
		this.forgeTab = forgeTab;
		this.model = new FModsTableModel();
		
		setModel(model);
		
		getTableHeader().setReorderingAllowed(false);
		
	}
	
	public void refreshModel() {
		
		model.refresh();
		
	}
	
	public void refreshTab() {
		
		forgeTab.refresh();
		
	}
	
	public ModData getModData() {
		return modData;
	}
	
	public ForgeTab getForgeTab() {
		return forgeTab;
	}
	
	@Override
	public FModsTableModel getModel() {
		return model;
	}
	
}
