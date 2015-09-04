package com.n9mtq4.customlauncher.tab.mods.hook;

import com.n9mtq4.customlauncher.tab.mods.ModTab;
import net.minecraft.launcher.profile.ProfileManager;
import net.minecraft.launcher.profile.RefreshedProfilesListener;

/**
 * Created by will on 7/28/15 at 4:22 PM.<br>
 * This needs to be its own class that stays unloaded UNTIL
 * the launcher.jar is added to the classpath.
 */
public final class ProfileChangeHook implements RefreshedProfilesListener {
	
	private ModTab modTab;
	
	public ProfileChangeHook(ModTab modTab) {
		this.modTab = modTab;
	}
	
	@Override
	public final void onProfilesRefreshed(ProfileManager profileManager) {
		modTab.onProfilesRefreshed(profileManager);
	}
	
}
