package com.n9mtq4.customlauncher.tab.forgemods.ui;

import com.n9mtq4.customlauncher.tab.forgemods.data.ModData;
import com.n9mtq4.customlauncher.tab.forgemods.utils.FileBrowseUtils;
import net.minecraft.launcher.Launcher;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by will on 7/28/15 at 10:22 PM.
 */
public class ForgeTab extends JSplitPane implements ListSelectionListener {
	
	private Launcher launcher;
	private ModData modData;
	
	private JList list;
	private JScrollPane listScroll;
	private JPanel buttonPanel;
	private JSplitPane sideSplitPane;
	private JScrollPane tableScroll;
	protected FModsTable table;
	
	private JButton installForge;
	private JButton addProfile;
	private JButton removeProfile;
	private JButton addMod;
	private JButton removeMod;
	private JButton editMod;
	
	public ForgeTab(Launcher launcher) {
		
		super(JSplitPane.HORIZONTAL_SPLIT);
		
		this.launcher = launcher;
		this.modData = ModData.load(launcher);
		
		gui();
		refreshList();
		
	}
	
	private void gui() {
		
		this.buttonPanel = new JPanel(new GridLayout(5, 1));
		//noinspection unchecked
		this.list = new JList(modData.getProfilesNames());
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list.addListSelectionListener(this);
		this.listScroll = new JScrollPane(list);
		this.table = new FModsTable(modData, this);
		buttons();
		
		this.sideSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		this.sideSplitPane.setTopComponent(listScroll);
		this.sideSplitPane.setBottomComponent(buttonPanel);
		this.sideSplitPane.setResizeWeight(.9d);
		
		this.tableScroll = new JScrollPane(table);
		
//		TODO: NOT YET DONE MESSAGE
//		JLabel label = new JLabel("This mod tab is NOT finished. It will be soon.");
//		this.tableScroll = new JScrollPane(label);
		
		this.setLeftComponent(sideSplitPane);
		this.setRightComponent(tableScroll);
		this.setOneTouchExpandable(false);
		this.setDividerLocation(.20d);
		
		Dimension minimumSize = new Dimension(100, 50);
		sideSplitPane.setMinimumSize(minimumSize);
		tableScroll.setMinimumSize(minimumSize);
		
		table.setFillsViewportHeight(true);
		this.sideSplitPane.setDividerLocation(.9d);
		
	}
	
	private void buttons() {
		
		this.installForge = new JButton("Install Forge");
		this.addProfile = new JButton("New Profile");
		this.removeProfile = new JButton("Delete Profile");
		this.addMod = new JButton("Add Mod");
		this.removeMod = new JButton("Remove Mod");
//		this.editMod = new JButton("Edit Mod");
		
		buttonPanel.add(installForge);
		buttonPanel.add(addProfile);
		buttonPanel.add(removeProfile);
		buttonPanel.add(addMod);
		buttonPanel.add(removeMod);
//		buttonPanel.add(editMod);
		
		installForge.addActionListener(new ForgeTabButtonActionListener(this));
		addProfile.addActionListener(new ForgeTabButtonActionListener(this));
		removeProfile.addActionListener(new ForgeTabButtonActionListener(this));
		addMod.addActionListener(new ForgeTabButtonActionListener(this));
		removeMod.addActionListener(new ForgeTabButtonActionListener(this));
//		editMod.addActionListener(new ForgeTabButtonActionListener(this));
		
	}
	
	protected void buttonClicked(ActionEvent e) {
		
		JButton button = (JButton) e.getSource();
		String text = button.getText();
		
		if (text.equalsIgnoreCase("dev")) {
			System.out.println("This is a placeholder for testing.");
		}else if (text.equalsIgnoreCase("install forge")) {
			new InstallForgeDialog(this, launcher);
		}else if (text.equalsIgnoreCase("new profile")) {
			new CreateProfile(this, launcher);
		}else if (text.equalsIgnoreCase("delete profile")) {
			int sure = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete\nthe profile " + 
					modData.getProfilesNames()[modData.selectedProfile] + "?", "Delete?", JOptionPane.YES_NO_OPTION);
			if (sure == 0) {
				modData.profiles.remove(modData.selectedProfile);
				modData.selectedProfile = 0;
				refreshList();
			}
		}else if (text.equalsIgnoreCase("add mod")) {
			File location = FileBrowseUtils.promptOpen();
			if (location == null) return;
			modData.profiles.get(modData.selectedProfile).addMod(location);
			table.refreshModel();
		}else if (text.equalsIgnoreCase("remove mod")) {
			table.refreshModel();
		}else if (text.equalsIgnoreCase("edit mod")) {
//			deprecated button
		}
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		modData.selectedProfile = list.getSelectedIndex();
		
	}
	
	protected void refreshList() {
		
		//noinspection unchecked
		list.setListData(modData.getProfilesNames());
		list.setSelectedIndex(modData.selectedProfile);
		
	}
	
	public void refresh() {
		
		
		
	}
	
	private static class ForgeTabButtonActionListener implements ActionListener {
		
		private ForgeTab forgeTab;
		
		public ForgeTabButtonActionListener(ForgeTab forgeTab) {
			this.forgeTab = forgeTab;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			forgeTab.buttonClicked(e);
		}
		
	}
	
}
