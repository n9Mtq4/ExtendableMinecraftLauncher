package com.n9mtq4.customlauncher.tab.mods.data;

import com.n9mtq4.customlauncher.tab.mods.ObjectUtils;
import net.minecraft.launcher.Launcher;
import net.minecraft.launcher.profile.Profile;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by will on 7/28/15 at 10:58 AM.
 */
public class ModData implements Serializable {
	
	private static final long serialVersionUID = 5773989385187164854L;
	private static final String modLocation = "mods.dat";
	
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
		
		Profile[] profiles = new Profile[launcher.getProfileManager().getProfiles().values().size()];
		launcher.getProfileManager().getProfiles().values().toArray(profiles);
		
		if (profiles.length == 0) {
			try {
				Thread.sleep(100);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			return createNewModData(launcher);
		}
		
		for (Profile profile : profiles) {
			
			ModProfile modProfile = new ModProfile(profile.getName());
			modData.profiles.add(modProfile);
			
		}
		
//		ModProfile defaultProfile = new ModProfile("Default");
//		modData.profiles.add(defaultProfile);
		
		return modData;
		
	}
	
	public ArrayList<ModProfile> profiles;
	
	public ModData() {
		
		this.profiles = new ArrayList<ModProfile>();
		
	}
	
	public void save() throws IOException {
		save(new File(modLocation));
	}
	
	private void save(File file) throws IOException {
		
		ObjectUtils.writeSerializable(this, file);
		
	}
	
	public Object[] getProfilesNames() {
		
		String[] profileNames = new String[this.profiles.size()];
		for (int i = 0; i < this.profiles.size(); i++) {
			profileNames[i] = this.profiles.get(i).getProfileName();
		}
		
		return profileNames;
		
	}
	
}
