package com.n9mtq4.exmcl.tab.worldmanager;

import org.jnbt.CompoundTag;
import org.jnbt.NBTInputStream;
import org.jnbt.NBTOutputStream;
import org.jnbt.StringTag;
import org.jnbt.Tag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by will on 10/3/15 at 11:21 AM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public class WorldManagerUtils {
	
	public static void renameWorld(File worldDir, String newName) throws IOException {
		
		setTagAt(new File(worldDir, "level.dat"), new String[]{"Data", "LevelName"}, newName);
		
	}
	
	public static String readWorldName(File worldDir) throws IOException {
		
		StringTag levelNameTag = getTagAt(new File(worldDir, "level.dat"), new String[]{"Data", "LevelName"});
		return levelNameTag.getValue();
		
	}
	
	public static void setTagAt(File file, String[] path, final Object newValue) throws IOException {
		
		NBTInputStream nbtInputStream = new NBTInputStream(new FileInputStream(file));
		
		Tag rootTag = nbtInputStream.readTag();
		if (path.length == 0) {
			Tag tag = new Tag(rootTag.getName()) {
				@Override
				public Object getValue() {
					return newValue;
				}
			};
			nbtInputStream.close();
			NBTOutputStream nbtOutputStream = new NBTOutputStream(new FileOutputStream(file));
			nbtOutputStream.writeTag(tag);
			nbtOutputStream.close();
		}
		
		CompoundTag ct = (CompoundTag) rootTag;
		for (int i = 0; i < path.length - 1; i++) {
			ct = (CompoundTag) ct.getValue().get(path[i]);
		}
		ct.getValue().remove(path[path.length - 1]);
		ct.getValue().put(path[path.length - 1], new Tag(path[path.length - 1]) {
			@Override
			public Object getValue() {
				return newValue;
			}
		});
		nbtInputStream.close();
		NBTOutputStream nbtOutputStream = new NBTOutputStream(new FileOutputStream(file));
		nbtOutputStream.writeTag(rootTag);
		
	}
	
	public static <E extends Tag> E getTagAt(File file, String[] path) throws IOException {
		
		NBTInputStream nbtInputStream = new NBTInputStream(new FileInputStream(file));
		if (path.length == 0) { //TODO: is this right?
			Tag tag = nbtInputStream.readTag();
			nbtInputStream.close();
			return (E) tag;
		}
		
		CompoundTag ct = (CompoundTag) nbtInputStream.readTag();
		for (int i = 0; i < path.length - 1; i++) {
			ct = (CompoundTag) ct.getValue().get(path[i]);
		}
		Tag tag = ct.getValue().get(path[path.length - 1]);
		nbtInputStream.close();
		return (E) tag;
		
	}
	
}
