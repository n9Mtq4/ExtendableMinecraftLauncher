package com.n9mtq4.customlauncher.tab.mods.data;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by will on 7/28/15 at 10:59 AM.
 */
public final class ModProfile implements Serializable {
	
	private static final long serialVersionUID = 681991166449772962L;
	
	private String profileName;
	private ArrayList<ModEntry> modList;
	
	public ModProfile(String profileName) {
		
		this.profileName = profileName;
		this.modList = new ArrayList<ModEntry>();
		
	}
	
	public final void addMod() {
		
		File file = null;
//		TODO: open file browse
		addMod(file);
		
	}
	
	public final void addMod(File file) {
		
		ModEntry mod = new ModEntry(file);
		modList.add(mod);
		
	}
	
	public final String getProfileName() {
		return profileName;
	}
	
	public final void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	
	public final ArrayList<ModEntry> getModList() {
		return modList;
	}
	
	public final void setModList(ArrayList<ModEntry> modList) {
		this.modList = modList;
	}
	
}
