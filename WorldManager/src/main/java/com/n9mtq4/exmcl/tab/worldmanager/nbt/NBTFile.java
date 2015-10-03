package com.n9mtq4.exmcl.tab.worldmanager.nbt;

import com.n9mtq4.reflection.ReflectionWrapper;
import nbteditor.CompoundNode;
import nbteditor.Node;
import org.jnbt.CompoundTag;
import org.jnbt.NBTInputStream;
import org.jnbt.NBTOutputStream;
import org.jnbt.Tag;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeMap;

/**
 * Created by will on 10/3/15 at 3:41 PM.
 *
 * @author Will "n9Mtq4" Bresnahan
 */
public class NBTFile {
	
	private File file;
	private NBTInputStream inputStream;
	private Node rootNode;
	private Tag rootTag;
	
	public NBTFile(File file) throws IOException {
		this.file = file;
		this.inputStream = new NBTInputStream(new FileInputStream(file));
		readSupport();
		editSupport();
	}
	
	private void readSupport() throws IOException {
		this.rootTag = inputStream.readTag();
	}
	
	private void editSupport() throws IOException {
		this.rootNode = Node.CreateNode(rootTag);
	}
	
	public void setTagAt(String[] path, final Object newValue) throws IOException {
		
		CompoundNode cn = (CompoundNode) ((TreeMap) rootNode.getValue()).get(path[0]);
		for (int i = 1; i < path.length - 1; i++) {
			cn = (CompoundNode) ((TreeMap) cn.getValue()).get(path[i]);
		}
		Node node = cn.getValue().get(path[path.length - 1]);
		
		ReflectionWrapper wrapper = ReflectionWrapper.attachToObject(node);
		wrapper.setField("value", newValue);
		
		NBTOutputStream nbtOutputStream = new NBTOutputStream(new FileOutputStream(file));
		nbtOutputStream.writeTag(rootNode.toTag());
		nbtOutputStream.close();
		
	}
	
	public Object getValueAt(String path) throws IOException {
		return getValueAt(path.split("/"));
	}
	
	public Object getValueAt(String[] path) throws IOException {
		return getTagAt(path).getValue();
	}
	
	public <E extends Tag> E getTagAt(String[] path) throws IOException {
		
		if (path.length == 0) {
			return (E) rootTag;
		}
		
		CompoundTag ct = (CompoundTag) rootTag;
		for (int i = 0; i < path.length - 1; i++) {
			ct = (CompoundTag) ct.getValue().get(path[i]);
		}
		Tag tag = ct.getValue().get(path[path.length - 1]);
		return (E) tag;
		
	}
	
}
