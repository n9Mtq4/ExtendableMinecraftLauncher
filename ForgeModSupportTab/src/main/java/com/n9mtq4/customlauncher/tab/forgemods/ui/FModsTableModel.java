package com.n9mtq4.customlauncher.tab.forgemods.ui;

import com.n9mtq4.customlauncher.tab.forgemods.data.ModData;
import com.n9mtq4.customlauncher.tab.forgemods.data.ModProfile;

import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by will on 7/28/15 at 11:22 AM.
 */
public class FModsTableModel extends DefaultTableModel implements TableModelListener, TableColumnModelListener, ComponentListener {
	
	private static final int ENABLED_COLUMN_WIDTH = 80;
	
	private ModData modData;
	private ForgeTab forgeTab;
	private FModsTable table;
	
	public FModsTableModel(ModData modData, ForgeTab forgeTab, FModsTable table) {
		this.modData = modData;
		this.forgeTab = forgeTab;
		this.table = table;
		addTableModelListener(this);
		table.getColumnModel().addColumnModelListener(this);
		table.addComponentListener(this);
		refresh();
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
		
		ModProfile selectedProfile = modData.profiles.get(modData.selectedProfile);
		Object[][] t = new Object[selectedProfile.getModList().size()][2];
		
		for (int i = 0; i < selectedProfile.getModList().size(); i++) {
			t[i][0] = selectedProfile.getModList().get(i).isEnabled();
			t[i][1] = selectedProfile.getModList().get(i).getName();
		}
		
		Object[] headers = new Object[]{"Enabled", "Mod Name"};
		setDataVector(t, headers);
		
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
