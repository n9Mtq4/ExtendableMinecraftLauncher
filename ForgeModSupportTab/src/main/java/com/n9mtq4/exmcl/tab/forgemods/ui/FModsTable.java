package com.n9mtq4.exmcl.tab.forgemods.ui;

import com.n9mtq4.exmcl.tab.forgemods.data.ModData;
import com.n9mtq4.exmcl.tab.forgemods.utils.FileDrop;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.io.File;

/**
 * Created by will on 7/28/15 at 11:25 AM.
 */
public final class FModsTable extends JTable {
	
	private ModData modData;
	private ForgeTab forgeTab;
	private FModsTableModel model;
	
	public FModsTable(ModData modData, ForgeTab forgeTab) {
		
		super();
		
		this.modData = modData;
		this.forgeTab = forgeTab;
		this.model = new FModsTableModel(modData, forgeTab, this);
		
		setModel(model);
		
		model.fireSet();
		 
		getTableHeader().setReorderingAllowed(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		initFileDrop();
		
	}
	
	protected void fireModDataSync() {
		model.fireModDataSync();
	}
	
	private void initFileDrop() {
		
		new FileDrop(this, new FileDrop.Listener() {
			@Override
			public void filesDropped(File[] files) {
				for (File file : files) {
					modData.profiles.get(modData.selectedProfile).addMod(file);
				}
				refreshModel();
			}
		});
		
	}
	
	public final void refreshModel() {
		
		model.refresh();
		
	}
	
	public final void refreshTab() {
		
		forgeTab.refresh();
		
	}
	
	public final ModData getModData() {
		return modData;
	}
	
	public final ForgeTab getForgeTab() {
		return forgeTab;
	}
	
	@Override
	public final FModsTableModel getModel() {
		return model;
	}
	
}
