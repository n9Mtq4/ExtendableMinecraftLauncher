package com.n9mtq4.exmcl.tab.worldmanager.ui;

import com.n9mtq4.exmcl.tab.worldmanager.WorldManagerUtils;
import com.n9mtq4.logwindow.BaseConsole;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by will on 8/31/15 at 11:42 PM.
 */
public final class WorldManagerTab extends JSplitPane {
	
	private final LauncherTabPanel parent;
	private final BaseConsole baseConsole;
	
	private JScrollPane scrollPane;
	private WorldTable table;
	private JPanel buttonArea;
	
	private JButton refresh;
	private JButton openSaveFolder;
	private JButton rename;
	private JButton toggleCheats;
	private JButton duplicate;
	private JButton moreInfo;
	
	public WorldManagerTab(LauncherTabPanel parent, BaseConsole baseConsole) {
		super();
		this.parent = parent;
		this.baseConsole = baseConsole;
		gui();
	}
	
	private void gui() {
		
		setEnabled(false);
		
		table = new WorldTable(parent.getMinecraftLauncher());
		scrollPane = new JScrollPane(table);
		setRightComponent(scrollPane);
		
		ButtonListener listener = new ButtonListener();
		this.buttonArea = new JPanel(new GridLayout(15, 1));
		
		this.refresh = new JButton("Refresh");
		buttonArea.add(refresh);
		refresh.addActionListener(listener);
		
		this.openSaveFolder = new JButton("Open Saves");
		buttonArea.add(openSaveFolder);
		openSaveFolder.addActionListener(listener);
		
		this.rename = new JButton("Rename");
		buttonArea.add(rename);
		rename.addActionListener(listener);
		
		this.toggleCheats = new JButton("Toggle Cheats");
		buttonArea.add(toggleCheats);
		toggleCheats.addActionListener(listener);
		
		this.duplicate = new JButton("Duplicate");
		buttonArea.add(duplicate);
		duplicate.addActionListener(listener);
		
		this.moreInfo = new JButton("More Info");
		buttonArea.add(moreInfo);
		moreInfo.addActionListener(listener);
		
		setLeftComponent(buttonArea);
		
	}
	
	private class ButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String buttonText = ((JButton) e.getSource()).getText();
			
			if (buttonText.equalsIgnoreCase("refresh")) {
				table.refresh();
			}else if (buttonText.equalsIgnoreCase("open saves")) {
				
				try {
					Desktop.getDesktop().open(table.getWorldTableModel().getSavesDir());
				}catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}else if (buttonText.equalsIgnoreCase("rename")) {
				File world = table.getSelectedWorld();
				if (world == null) {
					JOptionPane.showMessageDialog(WorldManagerTab.this, "You haven't selected a world", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String newName = JOptionPane.showInputDialog(WorldManagerTab.this, "What should be the new World Name?");
				if (newName == null) return;
				try {
					WorldManagerUtils.renameWorld(world, newName);
				}catch (IOException e1) {
					e1.printStackTrace();
				}
				table.refresh();
			}else if (buttonText.equalsIgnoreCase("toggle cheats")) {
				try {
					
					File world = table.getSelectedWorld();
					if (world == null) {
						JOptionPane.showMessageDialog(WorldManagerTab.this, "You haven't selected a world", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					File levelDat = new File(table.getSelectedWorld(), "level.dat");
					byte command = (Byte) WorldManagerUtils.getTagAt(levelDat, new String[]{"Data", "allowCommands"}).getValue();
					WorldManagerUtils.setTagAt(new File(table.getSelectedWorld(), "level.dat"), new String[]{"Data", "allowCommands"}, (byte) (command == 0 ? 1 : 0));
					
				}catch (IOException e1) {
					e1.printStackTrace();
				}
			}else if (buttonText.equalsIgnoreCase("more info")) {
				File world = table.getSelectedWorld();
				if (world == null) {
					JOptionPane.showMessageDialog(WorldManagerTab.this, "You haven't selected a world", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				new MoreInfoWindow(world, WorldManagerTab.this);
			}else {
				JOptionPane.showMessageDialog(table, "This feature is coming soon.", "Not yet ready", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
	}
	
}
