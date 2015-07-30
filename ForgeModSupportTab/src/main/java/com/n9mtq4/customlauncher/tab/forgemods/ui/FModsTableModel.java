package com.n9mtq4.customlauncher.tab.forgemods.ui;

import com.n9mtq4.customlauncher.tab.forgemods.data.ModData;
import com.n9mtq4.customlauncher.tab.forgemods.data.ModEntry;
import com.n9mtq4.customlauncher.tab.forgemods.data.ModProfile;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by will on 7/28/15 at 11:22 AM.
 */
public class FModsTableModel extends DefaultTableModel implements TableModelListener, TableColumnModelListener, ComponentListener {
	
	private static final int ENABLED_COLUMN_WIDTH = 60;
	
	private ModData modData;
	private ForgeTab forgeTab;
	private FModsTable table;
	private boolean setBefore;
	
	public FModsTableModel(ModData modData, ForgeTab forgeTab, FModsTable table) {
		this.modData = modData;
		this.forgeTab = forgeTab;
		this.table = table;
		this.setBefore = false;
	}
	
	protected void fireSet() {
		if (setBefore) return;
		setBefore = true;
		refresh();
		addTableModelListener(this);
		table.getColumnModel().addColumnModelListener(this);
		table.addComponentListener(this);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return column == 0;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
	}
	
	public void refresh() {
		
//		TODO: dirty fix that doesn't allow remembering selected profile
//		solve the issue that is creating the problem.
		if (modData.selectedProfile == -1) modData.selectedProfile = 0;
		ModProfile selectedProfile = modData.profiles.get(modData.selectedProfile);
		Object[][] t = new Object[selectedProfile.getModList().size()][2];
		
		for (int i = 0; i < selectedProfile.getModList().size(); i++) {
			t[i][0] = selectedProfile.getModList().get(i).isEnabled();
//			t[i][1] = selectedProfile.getModList().get(i).getName();
//			TODO: support for saving the mod list with the names
			t[i][1] = selectedProfile.getModList().get(i).getFile().getAbsolutePath();
		}
		
		setDataVector(t, new Object[]{"Enabled", "Mod File Location"});
		
	}
	
	private void resizeColumns() {
		table.getColumnModel().getColumn(0).setMinWidth(ENABLED_COLUMN_WIDTH);
		table.getColumnModel().getColumn(0).setMaxWidth(ENABLED_COLUMN_WIDTH);
		table.getColumnModel().getColumn(0).setPreferredWidth(ENABLED_COLUMN_WIDTH);
		table.getColumnModel().getColumn(1).setMinWidth(table.getWidth() - ENABLED_COLUMN_WIDTH);
		table.getColumnModel().getColumn(1).setMaxWidth(table.getWidth() - ENABLED_COLUMN_WIDTH);
		table.getColumnModel().getColumn(1).setPreferredWidth(table.getWidth() - ENABLED_COLUMN_WIDTH);
	}
	
	protected void fireModDataSync() {
		
		ModProfile selectedProfile = modData.profiles.get(modData.selectedProfile);
		selectedProfile.setModList(new ArrayList<ModEntry>());
		
		fireSet();
		for (int i = 0; i < table.getRowCount(); i++) {
			boolean enabled = (Boolean) table.getValueAt(i, 0);
			File file = new File((String) table.getValueAt(i, 1));
			selectedProfile.addMod(file, enabled);
		}
		
		try {
			modData.save();
		}catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(table, "Error saving the ModData!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		
//		TODO: debug print
		System.out.println("Forge Mod Table Changed");
		fireModDataSync();
		
	}
	
	@Override
	public void columnAdded(TableColumnModelEvent e) {
		
	}
	
	@Override
	public void columnRemoved(TableColumnModelEvent e) {
		
	}
	
	@Override
	public void columnMoved(TableColumnModelEvent e) {
		
	}
	
	@Override
	public void columnMarginChanged(ChangeEvent e) {
		resizeColumns();
	}
	
	@Override
	public void columnSelectionChanged(ListSelectionEvent e) {
		
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		resizeColumns();
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {
		
	}
	
	@Override
	public void componentShown(ComponentEvent e) {
		
	}
	
	@Override
	public void componentHidden(ComponentEvent e) {
		
	}
}
