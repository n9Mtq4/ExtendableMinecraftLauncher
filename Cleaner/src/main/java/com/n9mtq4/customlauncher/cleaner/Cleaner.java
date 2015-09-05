package com.n9mtq4.customlauncher.cleaner;

import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.logwindow.annotation.Async;
import com.n9mtq4.logwindow.events.ConsoleActionEvent;
import com.n9mtq4.logwindow.events.RemovalActionEvent;
import com.n9mtq4.logwindow.events.SentObjectEvent;
import com.n9mtq4.logwindow.listener.ObjectListener;
import com.n9mtq4.logwindow.listener.RemovalListener;
import com.n9mtq4.logwindow.listener.StringListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by will on 7/30/15 at 1:15 PM.<br>
 * This listener cleans up the ExMCL folder
 */
public final class Cleaner implements ObjectListener, StringListener, RemovalListener {
	
	private final List<File> toDelete = new ArrayList<File>();
	
	@Override
	public void objectReceived(SentObjectEvent e, BaseConsole baseConsole) {
		
		if (!e.getMessage().equals("add to delete")) return;
		if (!(e.getObject() instanceof File)) return;
		
//		toDelete.add((File) e.getObject());
		addToDelete((File) e.getObject());
		
	}
	
	@Async
	@Override
	public final void actionPerformed(ConsoleActionEvent consoleActionEvent, BaseConsole baseConsole) {
		
		if (consoleActionEvent.getCommand().getArg(0).equalsIgnoreCase("cleanup")) {
			clean(baseConsole);
		}
		
	}
	
//	TODO: should this be Async?
	@Override
	public final void onRemoval(RemovalActionEvent e) {
		
		clean(e.getBaseConsole());
		
	}
	
	private void addToDelete(File file) {
		
		if (file == null) return;
		toDelete.add(file);
		
	}
	
	/**
	 * Removes every file in toDelete
	 * */
	private void clean(BaseConsole baseConsole) {
		
		deleteFiles(buildToDeleteTree());
		
	}
	
	private ArrayList<File> buildToDeleteTree() {
//		change directories into children files
		ArrayList<File> files = new ArrayList<File>();
		for (File file : toDelete) {
			if (file != null) {
				files.add(file);
				if (file.isDirectory()) {
					files.addAll(buildFileTree(file));
				}
			}
		}
//		directories have to be last
		files.sort(new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				if (o1.isDirectory()) return 1;
				if (o2.isDirectory()) return -1;
				return 0;
			}
		});
		return files;
	}
	
	private void deleteFiles(ArrayList<File> files) {
		
		for (File file : files) {
			
			try {
				
				if (file == null) continue;
				if (!file.exists()) continue;
				
				boolean success = file.delete();
				System.out.println((success ? "Deleted" : "Failed to delete") + " the file: " + file.getAbsolutePath());
				
				if (!success) {
					file.deleteOnExit();
					System.out.println("Set the file to delete when the program exits.");
				}
				
			}catch (Exception e) {
//				just in case
//				when dealing loops, it is good to prevent one bad apple from
//				spoiling the rest
				e.printStackTrace();
			}
		}
		
	}
	
	private static ArrayList<File> buildFileTree(File file) {
		
		ArrayList<File> files = new ArrayList<File>();
		if (file.isDirectory()) {
			File[] children = file.listFiles();
			if (children == null) return files;
			for (File child : children) {
				files.addAll(buildFileTree(child));
			}
		}else {
			files.add(file);
		}
		return files;
		
	}
	
}
