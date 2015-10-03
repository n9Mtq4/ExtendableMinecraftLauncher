package com.n9mtq4.exmcl.tab.worldmanager.ui;

import com.n9mtq4.exmcl.tab.worldmanager.WorldManagerUtils;
import com.n9mtq4.exmcl.tab.worldmanager.WorldValues;
import com.n9mtq4.exmcl.tab.worldmanager.nbt.NBTFile;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by will on 10/3/15 at 3:21 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public class MoreInfoWindow {
	
	private static final String[] TITLES = {"Attribute", "Value"};
	
	private Component parent;
	private File worldDir;
	private File levelDat;
	private JFrame frame;
	private JTable table;
	private WorldInfoModel model;
	private JButton close;
	
	private ArrayList<String[]> worldAttributes;
	private String worldName;
	
	public MoreInfoWindow(File worldDir, Component parent) {
		this.worldDir = worldDir;
		this.parent = parent;
		this.worldAttributes = new ArrayList<String[]>();
		this.levelDat = new File(worldDir, "level.dat");
		updateWorldName();
		gui();
		updateWorldInfo();
	}
	
	private void gui() {
		
		frame = new JFrame("Info: " + worldName);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		model = new WorldInfoModel();
		table = new JTable(model);
		table.setDragEnabled(false);
		close = new JButton("Close");
		
		frame.add(new JScrollPane(table), BorderLayout.CENTER);
		frame.add(close, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(parent);
		
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getRootPane().setDefaultButton(close);
		
	}
	
	private void updateWorldInfo() {
		try {
			
			NBTFile nbtFile = new NBTFile(levelDat);
			worldAttributes.add(e("Difficulty", WorldValues.DIFFICULTIES[(Byte) nbtFile.getValueAt("Data/Difficulty")]));
			worldAttributes.add(e("Difficulty Locked", WorldValues.convertToBoolean(nbtFile.getValueAt("Data/DifficultyLocked"))));
			worldAttributes.add(e("Hardcore", WorldValues.convertToBoolean(nbtFile.getValueAt("Data/hardcore"))));
			worldAttributes.add(e("Cheats", WorldValues.convertToBoolean(nbtFile.getValueAt("Data/allowCommands"))));
			worldAttributes.add(e("Raining", WorldValues.convertToBoolean(nbtFile.getValueAt("Data/raining"))));
			worldAttributes.add(e("Thundering", WorldValues.convertToBoolean(nbtFile.getValueAt("Data/thundering"))));
			worldAttributes.add(e("Spawn X", String.valueOf(nbtFile.getValueAt("Data/SpawnX"))));
			worldAttributes.add(e("Spawn Y", String.valueOf(nbtFile.getValueAt("Data/SpawnY"))));
			worldAttributes.add(e("Spawn Z", String.valueOf(nbtFile.getValueAt("Data/SpawnZ"))));
			
		}catch (IOException e) {
			e.printStackTrace();
			worldAttributes.add(e("Error reading file", ""));
		}
	}
	
	private void updateWorldName() {
		try {
			this.worldName = WorldManagerUtils.readWorldName(worldDir);
		}catch (IOException e) {
			e.printStackTrace();
			this.worldName = "null";
		}
		worldAttributes.add(new String[]{"World Name", worldName});
	}
	
	private class WorldInfoModel extends DefaultTableModel {
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		
		@Override
		public String getColumnName(int column) {
			return TITLES[column];
		}
		
		@Override
		public int getColumnCount() {
			return TITLES.length;
		}
		
		@Override
		public int getRowCount() {
			return worldAttributes.size();
		}
		
		@Override
		public Object getValueAt(int row, int column) {
			return worldAttributes.get(row)[column];
		}
		
	}
	
	private static String[] e(String... values) {
		return values;
	}
	
}
