package com.n9mtq4.exmcl.tab.forgemods.data;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by will on 7/28/15 at 10:59 AM.
 */
public final class ModProfile implements Serializable {
	
	private static final long serialVersionUID = 3037547232647745150L;
	
	private String profileName;
	private ArrayList<ModEntry> modList;
	
	public ModProfile(String profileName) {
		
		this.profileName = profileName;
		this.modList = new ArrayList<ModEntry>();
		
	}
	
	public final void addMod(File file, boolean enabled) {
		
		ModEntry mod = new ModEntry(file);
		mod.setEnabled(enabled);
		modList.add(mod);
		
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
