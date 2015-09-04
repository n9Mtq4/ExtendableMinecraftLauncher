package com.n9mtq4.customlauncher.tab.forgemods.data;

import com.n9mtq4.customlauncher.tab.forgemods.utils.ObjectUtils;
import net.minecraft.launcher.Launcher;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by will on 7/28/15 at 10:58 AM.
 */
public final class ModData implements Serializable {
	
	private static final long serialVersionUID = 3165243445562402989L;
	private static final String modLocation = "data/forgemods.dat";
	
	public static ModData load(Launcher launcher) {
		
		File file = new File(modLocation);
		if (file.exists()) {
			
			try {
				ModData modData = ObjectUtils.readSerializable(file);
				return modData;
			}catch (Exception e) {
				
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "There was an error loading the ModData.\n" +
						"We are generating a new one.", "Error", JOptionPane.ERROR_MESSAGE);
				
				return createNewModData(launcher);
				
			}
			
		}else {
			
			return createNewModData(launcher);
			
		}
		
	}
	
	private static ModData createNewModData(Launcher launcher) {
		
		ModData modData = new ModData();
		
		ModProfile defaultProfile = new ModProfile("Default");
		modData.profiles.add(defaultProfile);
		
		return modData;
		
	}
	
	public final ArrayList<ModProfile> profiles;
	public int selectedProfile;
	
	public ModData() {
		
		this.profiles = new ArrayList<ModProfile>();
		this.selectedProfile = 0;
		
	}
	
	public final ModProfile getProfileByName(String name) {
		for (ModProfile profile : profiles) {
			if (profile.getProfileName().equals(name)) return profile;
		}
		return null;
	}
	
	public final void save() throws IOException {
		save(new File(modLocation));
	}
	
	private void save(File file) throws IOException {
		
		//noinspection ResultOfMethodCallIgnored
		file.getParentFile().mkdirs();
		ObjectUtils.writeSerializable(this, file);
		
	}
	
	public final Object[] getProfilesNames() {
		
		String[] profileNames = new String[this.profiles.size()];
		for (int i = 0; i < this.profiles.size(); i++) {
			profileNames[i] = this.profiles.get(i).getProfileName();
		}
		
		return profileNames;
		
	}
	
}
