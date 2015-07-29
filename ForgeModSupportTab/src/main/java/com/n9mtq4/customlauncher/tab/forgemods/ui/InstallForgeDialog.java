package com.n9mtq4.customlauncher.tab.forgemods.ui;

import com.n9mtq4.console.lib.command.ConsoleCommand;
import com.n9mtq4.console.lib.utils.JarLoader;
import com.n9mtq4.customlauncher.tab.forgemods.html.Downloader;
import net.minecraft.launcher.Launcher;
import net.minecraftforge.installer.SimpleInstaller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by will on 7/28/15 at 10:58 PM.
 */
public class InstallForgeDialog {
	
	/*
	* Example forge download url
	* http://adfoc.us/serve/sitelinks/?id=271228&url=http://files.minecraftforge.net/maven/net/minecraftforge/forge/1.8-11.14.3.1499/forge-1.8-11.14.3.1499-installer.jar
	* */
	
	private static final String FORGE_FILES_URL = "http://files.minecraftforge.net/";
	private static final String URL_PREFIX = "http://adfoc.us/serve/sitelinks/?id=271228&url=";
	private static final String URL_FORMAT = "http://files.minecraftforge.net/maven/net/minecraftforge/forge/%s/forge-%s-installer.jar";
	
	private ForgeTab forgeTab;
	private Launcher launcher;
	
	private JFrame frame;
	private JButton select;
	private JTable table;
	private JScrollPane scroll;
	
	public InstallForgeDialog(ForgeTab forgeTab, Launcher launcher) {
		
		this.forgeTab = forgeTab;
		this.launcher = launcher;
		gui();
		
	}
	
	private void gui() {
		
		frame = new JFrame("Select Forge Versions");
		
		table = new JTable(getListOfForges(), new Object[]{"MC Version", "Forge Version"});
		table.getTableHeader().setReorderingAllowed(false);
		scroll = new JScrollPane(table);
		select = new JButton("Download and Install");
		
		frame.add(scroll, BorderLayout.CENTER);
		frame.add(select, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(forgeTab);
		
		frame.getRootPane().setDefaultButton(select);
		
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File f = download(e);
				run(f);
				frame.dispose();
			}
		});
		
	}
	
	private void run(File file) {
		try {
			
			JarLoader.addFile(file);
			SimpleInstaller.main(new String[]{});
			
		}catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error. Couldn't open the forge installer.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private File download(ActionEvent e) {
		
		int row = table.getSelectedRow();
		String mcVersion = (String) table.getValueAt(row, 0);
		String forgeVersion = (String) table.getValueAt(row, 1);
		String url = makeUrl(mcVersion, forgeVersion);
		System.out.println(url);
		
		File file = new File("libs/forge_" + forgeVersion + ".jar");
		Downloader.downloadFile(url, file);
		return file;
		
	}
	
	private static String makeUrl(String mcVersion, String forgeVersion) {
		
		String forgeId = mcVersion + "-" + forgeVersion;
		return String.format(URL_FORMAT, forgeId, forgeId);
		
	}
	
	/*
	* FORGE DOWNLOADS URL PARSER STARTS HERE | FORGE DOWNLOADS URL PARSER STARTS HERE
	* FORGE DOWNLOADS URL PARSER STARTS HERE | FORGE DOWNLOADS URL PARSER STARTS HERE
	* FORGE DOWNLOADS URL PARSER STARTS HERE | FORGE DOWNLOADS URL PARSER STARTS HERE
	* FORGE DOWNLOADS URL PARSER STARTS HERE | FORGE DOWNLOADS URL PARSER STARTS HERE
	* FORGE DOWNLOADS URL PARSER STARTS HERE | FORGE DOWNLOADS URL PARSER STARTS HERE
	* */
	
	private static Object[][] getListOfForges() {
		
		try {
			
			Document doc = Jsoup.connect(FORGE_FILES_URL).get();
			Elements links = doc.select(".link > a");
			Element dlLine = links.get(6);
			
			String adLink = dlLine.attr("href");
			String link = adLink.substring(URL_PREFIX.length());
			
//			using LogWindow's parsing for more advanced things
			ConsoleCommand parser = new ConsoleCommand(link);
			String chop = parser.getBetween("/maven/net/minecraftforge/forge/", "-installer.jar");
			chop = chop.split("/")[0];
			String[] tokens = chop.split("-");
			
			return new Object[][] {{tokens[0], tokens[1]}, {"There will be the full", "list of forge dls in a later version."}};
			
		}catch (IOException e) {
			e.printStackTrace();
			return new Object[][] {{"Error Downloading Forge", "Error Downloading Forge"}};
		}
		
	}
	
}
