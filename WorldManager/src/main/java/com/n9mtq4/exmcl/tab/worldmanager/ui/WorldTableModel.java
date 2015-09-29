package com.n9mtq4.exmcl.tab.worldmanager.ui;

import net.minecraft.launcher.Launcher;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by will on 9/2/15 at 11:20 PM.
 */
public final class WorldTableModel extends DefaultTableModel {
	
	private static File worldDir;
	
	private Launcher launcher;
	private ArrayList<String> worldDirs;
	
	public WorldTableModel(Launcher launcher) {
		this.launcher = launcher;
		com.mojang.launcher.Launcher launcher1 = launcher.getLauncher();
		worldDir = new File(launcher1.getWorkingDirectory(), "saves");
		updateWorldList();
	}
	
	@Override
	public final boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override
	public final String getColumnName(int column) {
		return "World Name";
	}
	
	@Override
	public final Object getValueAt(int row, int column) {
		
		return worldDirs.get(row);
		
	}
	
	private void updateWorldList() {
		
		File[] worlds = worldDir.listFiles();
		this.worldDirs = new ArrayList<String>();
		for (File world : worlds) {
			if (world.isDirectory()) worldDirs.add(world.getName());
		}
		setRowCount(worlds.length - 1);
		setColumnCount(1);
		
	}
	
}
