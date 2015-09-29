package com.n9mtq4.exmcl.tab.mods.data;

import java.io.File;
import java.io.Serializable;

/**
 * Created by will on 7/28/15 at 11:01 AM.
 */
public final class ModEntry implements Serializable {
	
	private static final long serialVersionUID = 6987202382657879507L;
	
	private File file;
	
	public ModEntry(File file) {
		
		this.file = file;
		
	}
	
}
