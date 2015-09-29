package com.n9mtq4.exmcl.tab.forgemods.utils;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Component;
import java.io.File;

/**
 * Created by will on 7/28/15 at 10:31 PM.
 */
public class FileBrowseUtils {
	
	public static File[] promptOpen(Component component) {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		FileNameExtensionFilter fnef = new FileNameExtensionFilter("Mods (*.zip, *.jar)", "zip", "jar");
		chooser.setFileFilter(fnef);
		chooser.setDialogTitle("Choose Mod(s)");
		int returnVal = chooser.showOpenDialog(component);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFiles();
		}
		return new File[]{};
	}
	
}
