package com.n9mtq4.customlauncher.update;

import com.n9mtq4.customlauncher.update.utils.Downloader;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by will on 8/1/15 at 9:34 PM.
 */
//TODO: use the github api and JSON to check for update
//for now we use pastebin and Integer.parseInt
public class Updater {
	
//	private static final String UPDATE_URL = "https://api.github.com/repos/n9Mtq4/ExtendableMinecraftLauncher/releases";
//	private static final String TAG_NAME = "a0.0.2b2";
//	private static final String REGEX_PARSER = "/\\[\\n  \\{|  \\},\\n  \\{/g";
	
	private static final int LOCAL_BUILD_ID = 2;
	private static final String UPDATE_URL = "http://pastebin.com/raw.php?i=ejkgdBLA";
	private static final String DL_UPDATE_URL = "https://github.com/n9Mtq4/ExtendableMinecraftLauncher/releases";
	
	public Updater() {
		
		if (needsUpdate()) {
			showUpdateMessage();
		}
		
	}
	
	private boolean needsUpdate() {
		
		try {
			
			int remoteBuildId = Integer.parseInt(Downloader.getHTML(UPDATE_URL).trim());
			return remoteBuildId > LOCAL_BUILD_ID;
			
		}catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("Error parsing update info");
		}
		
		return false;
		
	}
	
/*	private boolean needsUpdate() {
		
		String html = Downloader.getHTML(UPDATE_URL);
		String[] releases = html.split(REGEX_PARSER);
		for (int i = 0; i < releases.length; i++) {
			releases[i] = "{\n" + releases[i];
		}
		
		System.out.println(releases[0]);
		
		JSONObject mostRecent = new JSONObject(releases[0]);
		
		return false;
		
	}
	*/
	private void showUpdateMessage() {
		
		JOptionPane.showMessageDialog(null, "An update for Extendable Minecraft Launcher is available.\nPlease download an update from\n" + DL_UPDATE_URL, "Update", JOptionPane.WARNING_MESSAGE);
		try {
			openWebpage(new URL(DL_UPDATE_URL));
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * http://stackoverflow.com/a/10967469
	 * */
	public static void openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void openWebpage(URL url) {
		try {
			openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
}
