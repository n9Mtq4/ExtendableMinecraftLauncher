package com.n9mtq4.customlauncher.tab.forgemods.utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Created by will on 7/28/15 at 10:31 PM.
 */
public class FileBrowseUtils {
	
	public static File promptOpen() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter fnef = new FileNameExtensionFilter("Mods (*.zip, *.jar)", "zip", "jar");
		chooser.setFileFilter(fnef);
		chooser.setDialogTitle("Choose Mod");
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}
		return null;
	}
	
}
