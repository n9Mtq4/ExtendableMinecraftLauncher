package com.n9mtq4.customlauncher.uihooks;

import com.mojang.launcher.events.GameOutputLogProcessor;
import com.mojang.launcher.updater.DownloadProgress;
import com.mojang.launcher.versions.CompleteVersion;
import com.n9mtq4.logwindow.BaseConsole;
import com.n9mtq4.reflection.ReflectionHelper;
import com.n9mtq4.reflection.ReflectionWrapper;
import net.minecraft.launcher.Launcher;
import net.minecraft.launcher.SwingUserInterface;
import net.minecraft.launcher.game.MinecraftGameRunner;
import net.minecraft.launcher.ui.popups.login.LogInPopup;

import javax.swing.*;
import java.io.File;

/**
 * Created by will on 8/31/15 at 5:06 PM.<br>
 * A class for better hooks into the SwingUserInterface
 * Wont be forwards compatible and not necessary, so NYI.
 */
public class HookedSwingUserInterface extends SwingUserInterface {
	
	private BaseConsole baseConsole;
	private ReflectionWrapper thisWrapper;
	private ReflectionWrapper parentWrapper;
	
	public HookedSwingUserInterface(SwingUserInterface parent, BaseConsole baseConsole) {
		super((Launcher) ReflectionHelper.getObject("minecraftLauncher", parent), parent.getFrame());
		this.baseConsole = baseConsole;
		thisWrapper = ReflectionWrapper.attachToObject(this);
		parentWrapper = ReflectionWrapper.attachToObject(parent);
		thisWrapper.setField("launcherPanel", parentWrapper.getField("launcherPanel"));
	}
	
	@Override
	public void showLoginPrompt(Launcher minecraftLauncher, LogInPopup.Callback callback) {
		super.showLoginPrompt(minecraftLauncher, callback);
		baseConsole.pushObject(new Object[]{minecraftLauncher, callback}, "swinguserinterface showLoginPrompt");
	}
	
	@Override
	public void initializeFrame() {
		super.initializeFrame();
		baseConsole.pushObject(null, "swinguserinterface initializeFrame");
	}
	
	@Override
	public void showOutdatedNotice() {
		super.showOutdatedNotice();
		baseConsole.pushObject(null, "swinguserinterface showOutdatedNotice");
	}
	
	@Override
	public void showLoginPrompt() {
		super.showLoginPrompt();
		baseConsole.pushObject(null, "swinguserinterface showLoginPrompt");
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		baseConsole.pushObject(visible, "swinguserinterface setVisible");
	}
	
	@Override
	public void shutdownLauncher() {
		super.shutdownLauncher();
		baseConsole.pushObject(null, "swinguserinterface shutdownLauncher");
	}
	
	@Override
	public void setDownloadProgress(DownloadProgress downloadProgress) {
		super.setDownloadProgress(downloadProgress);
		baseConsole.pushObject(downloadProgress, "swinguserinterface setDownloadProgress");
	}
	
	@Override
	public void hideDownloadProgress() {
		super.hideDownloadProgress();
		baseConsole.pushObject(null, "swinguserinterface hideDownloadProgress");
	}
	
	@Override
	public void showCrashReport(CompleteVersion version, File crashReportFile, String crashReport) {
		super.showCrashReport(version, crashReportFile, crashReport);
		baseConsole.pushObject(new Object[]{version, crashReportFile, crashReport}, "swinguserinterface showCrashReport");
	}
	
	@Override
	public void gameLaunchFailure(String reason) {
		super.gameLaunchFailure(reason);
		baseConsole.pushObject(reason, "swinguserinterface gameLaunchFailure");
	}
	
	@Override
	public void updatePlayState() {
		super.updatePlayState();
		baseConsole.pushObject(null, "swinguserinterface updatePlayState");
	}
	
	@Override
	public GameOutputLogProcessor showGameOutputTab(MinecraftGameRunner gameRunner) {
		GameOutputLogProcessor gameOutputLogProcessor = super.showGameOutputTab(gameRunner);
		baseConsole.pushObject(gameRunner, "swinguserinterface showGameOutputTab");
		return gameOutputLogProcessor;
	}
	
	@Override
	public String getTitle() {
		String title = super.getTitle();
		baseConsole.pushObject(null, "swinguserinterface getTitle");
		return title;
	}
	
	@Override
	public JFrame getFrame() {
		JFrame frame =  super.getFrame();
		baseConsole.pushObject(null, "swinguserinterface getFrame");
		return frame;
	}
	
}
