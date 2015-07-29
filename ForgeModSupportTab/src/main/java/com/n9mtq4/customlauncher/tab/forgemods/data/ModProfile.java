package com.n9mtq4.customlauncher.tab.forgemods.data;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by will on 7/28/15 at 10:59 AM.
 */
public class ModProfile implements Serializable {
	
	private static final long serialVersionUID = 3037547232647745150L;
	
	private String profileName;
	private ArrayList<ModEntry> modList;
	
	public ModProfile(String profileName) {
		
		this.profileName = profileName;
		this.modList = new ArrayList<ModEntry>();
		
	}
	
	public void addMod(File file) {
		
		ModEntry mod = new ModEntry(file);
		modList.add(mod);
		
	}
	
	public String getProfileName() {
		return profileName;
	}
	
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	
	public ArrayList<ModEntry> getModList() {
		return modList;
	}
	
	public void setModList(ArrayList<ModEntry> modList) {
		this.modList = modList;
	}
	
}
