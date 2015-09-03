package com.n9mtq4.customlauncher.tab.forgemods.ui;

import com.n9mtq4.customlauncher.tab.forgemods.GameStartHook;
import com.n9mtq4.customlauncher.tab.forgemods.data.ModData;
import com.n9mtq4.customlauncher.tab.forgemods.data.ModProfile;
import com.n9mtq4.customlauncher.tab.forgemods.utils.FileBrowseUtils;
import com.n9mtq4.customlauncher.tab.forgemods.utils.ForgeModManager;
import com.n9mtq4.logwindow.BaseConsole;
import net.minecraft.launcher.ui.tabs.LauncherTabPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by will on 7/28/15 at 10:22 PM.
 */
@SuppressWarnings("FieldCanBeLocal")
public class ForgeTab extends JSplitPane implements ListSelectionListener {
	
	private final LauncherTabPanel parent;
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
//	@Deprecated
//	private JButton editMod;
	
	public ForgeTab(LauncherTabPanel parent, BaseConsole baseConsole) {
		
		super(JSplitPane.HORIZONTAL_SPLIT);
		
		this.parent = parent;
		this.modData = ModData.load(parent.getMinecraftLauncher());
		
		gui();
		refreshList();
		
		try {
			ForgeModManager.firstRunCleanup(parent.getMinecraftLauncher(), modData, this);
			modData.save();
			refresh();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		baseConsole.addListenerAttribute(new GameStartHook(parent.getMinecraftLauncher(), modData));
//		ConsoleListener listener = ReflectionHelper.callConstructor(GameStartHook.class, launcher, modData);
//		baseConsole.addListener(listener);
		
	}
	
	private void gui() {
		
		this.buttonPanel = new JPanel(new GridLayout(5, 1));
		//noinspection unchecked
		this.list = new JList(modData.getProfilesNames());
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list.addListSelectionListener(this);
		this.listScroll = new JScrollPane(list);
		listScroll.setColumnHeaderView(new JLabel("<html><b>Profiles</b></html>"));
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
		
		addMod.setToolTipText("Add mods the the current profile.\n" +
				"You can also drag and drop mods into the table.");
		
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
			new InstallForgeDialog(this);
		}else if (text.equalsIgnoreCase("new profile")) {
//			new CreateProfile(this, launcher);
			String profileName = JOptionPane.showInputDialog(this, "What should the profile be called?");
			if (profileName == null) return;
			if (!profileName.trim().equals("")) {
				ModProfile profile = new ModProfile(profileName);
				modData.profiles.add(profile);
				refresh();
			}
		}else if (text.equalsIgnoreCase("delete profile")) {
			if (modData.profiles.get(modData.selectedProfile).getProfileName().equalsIgnoreCase("default")) {
				JOptionPane.showMessageDialog(this, "You can't delete the default profile.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int sure = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete\nthe profile " + 
					modData.getProfilesNames()[modData.selectedProfile] + "?", "Delete?", JOptionPane.YES_NO_OPTION);
			if (sure == 0) {
				modData.profiles.remove(modData.selectedProfile);
				modData.selectedProfile = 0;
				refreshList();
			}
		}else if (text.equalsIgnoreCase("add mod")) {
//			File mod = FileBrowseUtils.promptOpen(this);
//			if (mod == null) return;
//			modData.profiles.get(modData.selectedProfile).addMod(mod);
			File[] mods = FileBrowseUtils.promptOpen(this);
			if (mods.length == 0) return;
			for (File mod : mods) {
				modData.profiles.get(modData.selectedProfile).addMod(mod);
			}
			table.refreshModel();
		}else if (text.equalsIgnoreCase("remove mod")) {
			int row = table.getSelectedRow();
			ModProfile selectedProfile = modData.profiles.get(modData.selectedProfile);
			if (row < 0) {
				JOptionPane.showMessageDialog(this, "You haven't selected a mod to remove!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			selectedProfile.getModList().remove(row);
			table.refreshModel();
		}else if (text.equalsIgnoreCase("edit mod")) {
//			deprecated button
		}
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		modData.selectedProfile = list.getSelectedIndex();
		table.refreshModel();
		
	}
	
	protected void refreshList() {
		
		//noinspection unchecked
		list.setListData(modData.getProfilesNames());
		list.setSelectedIndex(modData.selectedProfile);
		
	}
	
	public void refresh() {
		
		refreshList();
		table.refreshModel();
		
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
