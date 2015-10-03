package com.n9mtq4.exmcl.tab.worldmanager.ui;

import com.n9mtq4.exmcl.tab.worldmanager.WorldManagerUtils;
import net.minecraft.launcher.Launcher;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by will on 9/2/15 at 11:20 PM.
 */
public final class WorldTableModel extends DefaultTableModel {
	
	private static final String[] TITLES = {"World Name", "File Name"};
	
	private File savesDir;
	private Launcher launcher;
	private ArrayList<String[]> worldList;
	
	public WorldTableModel(Launcher launcher) {
		this.launcher = launcher;
		com.mojang.launcher.Launcher launcher1 = launcher.getLauncher();
		savesDir = new File(launcher1.getWorkingDirectory(), "saves");
		updateWorldList();
	}
	
	public final void refresh() {
		updateWorldList();
	}
	
	@Override
	public final boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override
	public int getColumnCount() {
		return TITLES.length;
	}
	
	@Override
	public int getRowCount() {
		if (worldList == null) return 0;
		return worldList.size();
	}
	
	@Override
	public final String getColumnName(int column) {
		return TITLES[column];
	}
	
	@Override
	public final Object getValueAt(int row, int column) {
		return worldList.get(row)[column];
	}
	
	private void updateWorldList() {
		
		File[] worlds = savesDir.listFiles();
		resetWorldList();
		if (worlds == null) return;
		for (File world : worlds) {
			try {
				if (world.isDirectory()) {
					String worldName = WorldManagerUtils.readWorldName(world);
					worldList.add(new String[]{worldName, world.getName()});
				}
			}catch (IOException e) {
				System.out.println("Error reading world name: " + world.getAbsolutePath());
				System.out.println("This might be NEI's weird way of saving its data inside saves/");
			}
		}
		
	}
	
	private void resetWorldList() {
		this.worldList = new ArrayList<String[]>();
	}
	
	public final File getSavesDir() {
		return savesDir;
	}
	
	public final ArrayList<String[]> getWorldList() {
		return worldList;
	}
	
}
