package com.n9mtq4.exmcl.tab.worldmanager;

/**
 * Created by will on 10/3/15 at 3:39 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public class WorldValues {
	
	public static final String[] DIFFICULTIES = {"Peaceful", "Easy", "Normal", "Hard"};
	
	public static String convertToBoolean(Object object) {
		return ((Byte) object) == 1 ? "true" : "false";
	}
	
}
