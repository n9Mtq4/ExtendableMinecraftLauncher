package com.n9mtq4.customlauncher.tab.worldmanager.ui;

import net.minecraft.launcher.Launcher;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by will on 9/2/15 at 11:20 PM.
 */
public class WorldTableModel extends DefaultTableModel {
	
	private static File worldDir;
	
	private Launcher launcher;
	private ArrayList<String> worldNames;
	
	public WorldTableModel(Launcher launcher) {
		this.launcher = launcher;
		com.mojang.launcher.Launcher launcher1 = launcher.getLauncher();
		worldDir = new File(launcher1.getWorkingDirectory(), "saves");
		setWorlds();
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override
	public String getColumnName(int column) {
		return "World Name";
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		
		return worldNames.get(row);
		
	}
	
	private void setWorlds() {
		
		File[] worlds = worldDir.listFiles();
		this.worldNames = new ArrayList<String>();
		for (File world : worlds) {
			if (world.isDirectory()) worldNames.add(world.getName());
		}
		setRowCount(worlds.length);
		setColumnCount(1);
		
	}
	
}
