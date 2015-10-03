package com.n9mtq4.exmcl.tab.worldmanager;

import com.n9mtq4.reflection.ReflectionWrapper;
import nbteditor.CompoundNode;
import nbteditor.Node;
import org.jnbt.CompoundTag;
import org.jnbt.NBTInputStream;
import org.jnbt.NBTOutputStream;
import org.jnbt.StringTag;
import org.jnbt.Tag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeMap;

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
		Node rootNode = Node.CreateNode(nbtInputStream.readTag());
		
		CompoundNode cn = (CompoundNode) ((TreeMap) rootNode.getValue()).get(path[0]);
		for (int i = 1; i < path.length - 1; i++) {
			cn = (CompoundNode) ((TreeMap) cn.getValue()).get(path[i]);
		}
		Node node = cn.getValue().get(path[path.length - 1]);
		
		ReflectionWrapper wrapper = ReflectionWrapper.attachToObject(node);
		wrapper.setField("value", newValue);
		
		nbtInputStream.close();
		NBTOutputStream nbtOutputStream = new NBTOutputStream(new FileOutputStream(file));
		nbtOutputStream.writeTag(rootNode.toTag());
		nbtOutputStream.close();
		
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
