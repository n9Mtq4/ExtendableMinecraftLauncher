package com.n9mtq4.exmcl.update;

import com.n9mtq4.exmcl.update.utils.Download;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by will on 10/1/15 at 4:49 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public class UpdateDownloader {
	
	private static final String UPDATE_URL = "https://github.com/n9Mtq4/ExtendableMinecraftLauncher/releases/download/a0.0.2b2/ExMCL.zip";
	
	private Download downloader;
	private ProgressBarUpdater progressBarUpdater;
	private DownloadUI downloadUI;
	
	public UpdateDownloader() {
		
		try {
			this.downloader = new Download(new URL("https://download.cyanogenmod.org/get/jenkins/128248/cm-12.1-20151001-NIGHTLY-bacon.zip"));
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		downloader.run();
		this.downloadUI = new DownloadUI();
		this.progressBarUpdater = new ProgressBarUpdater();
		progressBarUpdater.start();
		
	}
	
	private class DownloadUI {
		
		private JFrame frame;
		private JProgressBar progressBar;
		
		public DownloadUI() {
			gui();
		}
		
		private void gui() {
			
			this.frame = new JFrame("Updating...");
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			
			this.progressBar = new JProgressBar(0, UpdateDownloader.this.downloader.getSize());
			frame.add(progressBar);
			
			frame.pack();
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			
		}
		
	}
	
	private class ProgressBarUpdater extends Thread {
		
		private boolean running;
		
		public ProgressBarUpdater() {
			this.running = true;
		}
		
		@Override
		public void run() {
			
			while (running) {
				// this is long and ugly
				UpdateDownloader.this.downloadUI.progressBar.setValue(UpdateDownloader.this.downloader.getDownloaded());
				
//				if (UpdateDownloader.this.downloader.getDownloaded() >= UpdateDownloader.this.downloader.getSize()) {
//					this.running = false;
//				}
				
				try {
					Thread.sleep(500);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
}
