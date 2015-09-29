package com.n9mtq4.exmcl.tab.forgemods.data;

import java.io.File;
import java.io.Serializable;

/**
 * Created by will on 7/28/15 at 11:01 AM.
 */
public final class ModEntry implements Serializable {
	
	private static final long serialVersionUID = 3037764535520118680L;
	
	private File file;
	private boolean enabled;
	
	public ModEntry(File file) {
		
		this.file = file;
		this.enabled = true;
		
	}
	
	public final String getName() {
		return file.getName();
	}
	
	public final File getFile() {
		return file;
	}
	
	public final void setFile(File file) {
		this.file = file;
	}
	
	public final boolean isEnabled() {
		return enabled;
	}
	
	public final void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
