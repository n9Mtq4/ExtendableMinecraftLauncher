package com.n9mtq4.customlauncher.tab.mods.data;

import com.n9mtq4.customlauncher.tab.mods.ObjectUtils;

import javax.swing.*;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by will on 7/28/15 at 10:58 AM.
 */
public class ModData implements Serializable {
	
	private static final long serialVersionUID = 5773989385187164854L;
	
	public static ModData load() {
		
		File file = new File("mods.dat");
		if (file.exists()) {
			
			try {
				ModData modData = ObjectUtils.readSerializable(file);
				return modData;
			}catch (Exception e) {
				
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "There was an error loading the ModData.\n" +
						"We are generating a new one.", "Error", JOptionPane.ERROR_MESSAGE);
				
				return createNewModData();
				
			}
			
		}else {
			
			return createNewModData();
			
		}
		
	}
	
	private static ModData createNewModData() {
		
		ModData modData = new ModData();
		ModProfile defaultProfile = new ModProfile("Default");
		modData.profiles.add(defaultProfile);
		
		return modData;
		
	}
	
	public ArrayList<ModProfile> profiles;
	
	public ModData() {
		
		this.profiles = new ArrayList<ModProfile>();
		
	}
	
}
