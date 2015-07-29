package com.n9mtq4.customlauncher.tab.forgemods.data;

import java.io.File;
import java.io.Serializable;

/**
 * Created by will on 7/28/15 at 11:01 AM.
 */
public class ModEntry implements Serializable {
	
	private static final long serialVersionUID = 3037764535520118680L;
	
	private File file;
	private boolean enabled;
	
	public ModEntry(File file) {
		
		this.file = file;
		this.enabled = true;
		
	}
	
	public String getName() {
		return file.getName();
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
