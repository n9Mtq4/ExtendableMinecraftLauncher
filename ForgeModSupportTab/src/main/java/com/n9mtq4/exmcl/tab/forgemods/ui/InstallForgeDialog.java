package com.n9mtq4.exmcl.tab.forgemods.ui;

import com.n9mtq4.exmcl.tab.forgemods.html.Downloader;
import com.n9mtq4.logwindow.utils.StringParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by will on 7/28/15 at 10:58 PM.
 */
public final class InstallForgeDialog {
	
	/*
	* Example forge download url
	* http://adfoc.us/serve/sitelinks/?id=271228&url=http://files.minecraftforge.net/maven/net/minecraftforge/forge/1.8-11.14.3.1499/forge-1.8-11.14.3.1499-installer.jar
	* */
	
	private static final String MCVERSION_SELECTOR = "body > div.contents > div.wrapper > section:nth-child(1) > div.versions-wrapper > div > ul > li > div > ul > li > a";
	private static final String LINK_SELECTOR = "#downloadsTable > tbody > tr > td:nth-child(3) > ul > li:nth-child(2) > a:nth-child(1)";
	
	private static final String FORGE_FILES_URL = "http://files.minecraftforge.net/";
	private static final String URL_SPLIT = "&url=";
	
	private final ForgeTab forgeTab;
	private JFrame frame;
	private JButton select;
	private JTable table;
	private JScrollPane scroll;
	
	public InstallForgeDialog(ForgeTab forgeTab) {
		
		this.forgeTab = forgeTab;
		gui();
		
	}
	
	/**
	 * Handling the gui code to create the window to install forge.
	 * */
	private void gui() {
		
		frame = new JFrame("Select Forge Versions");
		
		table = new JTable(getListOfForges(), new Object[]{"MC Version", "Forge Version", "Url"});
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
				JOptionPane.showMessageDialog(frame, "After installing forge, a launcher restart\nis required.", "Info", JOptionPane.INFORMATION_MESSAGE);
//				launcher.refreshVersionsAndProfiles();
				File f = download(e);
				run(f);
				frame.dispose();
			}
		});
		
	}
	
	/**
	 * Tries to run the downloaded forge installer
	 * should work with most versions
	 * */
	private void run(File file) {
		
		Runtime re = Runtime.getRuntime();
		try{
			re.exec("java -jar " + file.getAbsolutePath());
		} catch (IOException e){
			e.printStackTrace();
			forgeTab.getBaseConsole().printStackTrace(e);
			JOptionPane.showMessageDialog(forgeTab, "Error launching forge installer", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Downloads the forge mod installer
	 * */
	private File download(final ActionEvent e) {
		
		int row = table.getSelectedRow();
//		String mcVersion = (String) table.getValueAt(row, 0);
		String forgeVersion = (String) table.getValueAt(row, 1);
//		String url = makeUrl(mcVersion, forgeVersion);
		String url = (String) table.getValueAt(row, 2);
		System.out.println(url);
		
		File tmpDir = new File("tmp/");
		//noinspection ResultOfMethodCallIgnored
		tmpDir.mkdirs();
		File file = new File(tmpDir, "forge_" + forgeVersion + ".jar");
		Downloader.downloadFile(url, file);
		return file;
		
	}
	
	private static Object[][] getListOfForges() {
		
		ArrayList<Object[]> versions = new ArrayList<Object[]>();
		
		try {
			
			Document index = Jsoup.connect(FORGE_FILES_URL).get();
			
			Elements mcVersions = index.select(MCVERSION_SELECTOR);
			
			Elements linksIndex = index.select(LINK_SELECTOR);
			
			for (Element version : linksIndex) {
				
				String adLink = version.attr("href");
				String link = adLink.split(URL_SPLIT)[1];
				
				StringParser parser = new StringParser(link);
				String chop = parser.getBetween("/maven/net/minecraftforge/forge/", "-installer.jar");
				chop = chop.split("/")[0];
				String[] tokens = chop.split("-");
				
				versions.add(new Object[]{tokens[0], tokens[1], link});
				
			}
			
			for (Element mcVersion : mcVersions) {
				
				try {
					Document doc = Jsoup.connect(mcVersion.attr("href")).get();
					Elements links = doc.select(LINK_SELECTOR);
					
					for (Element version : links) {
						
						String adLink = version.attr("href");
						String link = adLink.split(URL_SPLIT)[1];
						
						StringParser parser = new StringParser(link);
						String chop = parser.getBetween("/maven/net/minecraftforge/forge/", "-installer.jar");
						chop = chop.split("/")[0];
						String[] tokens = chop.split("-");
						
						versions.add(new Object[]{tokens[0], tokens[1], link});
						
					}
					
				}catch (Exception e) {
//					prevent a bad formatting on an older version to mess with things
				}
				
			}
			
			Object[][] oVersions = new Object[versions.size()][3];
			versions.toArray(oVersions);
			return oVersions;
			
		}catch (Exception all) {
			all.printStackTrace();
			return new Object[][] {{"Error Downloading Forge List", "Please try again"}};
		}
		
	}
	
}
