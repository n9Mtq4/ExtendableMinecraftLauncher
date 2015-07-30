package com.n9mtq4.customlauncher.tab.mods;

import com.n9mtq4.customlauncher.tab.mods.data.ModData;
import com.n9mtq4.customlauncher.tab.mods.table.ModsTable;
import net.minecraft.launcher.Launcher;
import net.minecraft.launcher.profile.ProfileManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Created by will on 7/27/15 at 3:00 PM.
 */
public class ModTab extends JSplitPane implements ListSelectionListener {
	
	private Launcher launcher;
	private ModData modData;
	
	private JList list;
	private JScrollPane listScroll;
	private JPanel buttonPanel;
	private JSplitPane sideSplitPane;
	private JScrollPane tableScroll;
	private ModsTable table;
	
	private JButton addMod;
	private JButton removeMod;
	private JButton editMod;
	private JButton upMod;
	private JButton downMod;
	
	public ModTab(Launcher launcher) {
		
		super(JSplitPane.HORIZONTAL_SPLIT);
		
		this.launcher = launcher;
		this.modData = ModData.load(launcher);
		
		gui();
		selectOnList();
		
	}
	
	private void gui() {
		
		this.buttonPanel = new JPanel(new GridLayout(5, 1));
		//noinspection unchecked
		this.list = new JList(modData.getProfilesNames());
		this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.list.addListSelectionListener(this);
		this.listScroll = new JScrollPane(list);
		this.table = new ModsTable(modData, this);
		buttons();
		
		this.sideSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		this.sideSplitPane.setTopComponent(listScroll);
		this.sideSplitPane.setBottomComponent(buttonPanel);
		this.sideSplitPane.setResizeWeight(.9d);
		
//		this.tableScroll = new JScrollPane(table);
		
//		TODO: NOT YET DONE MESSAGE
		JTextArea label = new JTextArea("This mod tab is NOT finished. Please use the forge mod tab instead.\n" +
				"This mod tab is 80% done in developer builds, but isn't stable enough yet and can't run on its own.\n" +
				"Current Progress:\n" +
				"[x] Mod managing\n" +
				"[x] Receiving play button push\n" +
				"[ ] Making a new profile\n" +
				"[x] Unzipping version.jar\n" +
				"[x] Copying files\n" +
				"[x] Zipping version\n" +
				"[ ] Renaming version");
		label.setEditable(false);
		this.tableScroll = new JScrollPane(label);
		
		this.setLeftComponent(sideSplitPane);
		this.setRightComponent(tableScroll);
		this.setOneTouchExpandable(false);
		this.setDividerLocation(.20d);
		
		Dimension minimumSize = new Dimension(100, 50);
		sideSplitPane.setMinimumSize(minimumSize);
		tableScroll.setMinimumSize(minimumSize);
		
		table.setFillsViewportHeight(true);
		this.sideSplitPane.setDividerLocation(.8d);
		
	}
	
	private void buttons() {
		
		this.addMod = new JButton("Add Mod");
		this.removeMod = new JButton("Remove Mod");
		this.editMod = new JButton("Edit Mod");
		this.upMod = new JButton("Up");
		this.downMod = new JButton("Down");
		
		this.buttonPanel.add(addMod);
		this.buttonPanel.add(removeMod);
		this.buttonPanel.add(editMod);
		this.buttonPanel.add(upMod);
		this.buttonPanel.add(downMod);
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		String newProfileName = (String) list.getSelectedValue();
		launcher.getProfileManager().setSelectedProfile(newProfileName);
		
	}
	
	private void selectOnList() {
		
		String profileNameToSet = launcher.getProfileManager().getSelectedProfile().getName();
		list.setSelectedValue(profileNameToSet, true);
		
	}
	
	public void refresh() {
		
		
		
	}
	
	public void onProfilesRefreshed(ProfileManager profileManager) {
		
		selectOnList();
		
	}
	
}
