package net.turtle.math;

import java.util.HashMap;

public class BlockState {
	private final String name;
	private final HashMap<String, String> properties;
	
	public BlockState(String name) {
		this(name, null);
	}
	
	public BlockState(String name, HashMap<String, String> properties) {
		this.name = name;
		if (properties != null && properties.isEmpty()) {
			properties = null;
		}
		this.properties = properties;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean hasProperties() {
		return properties != null;
	}
	
	public HashMap<String, String> getProperties() {
		return properties;
	}

	@Override
	public String toString() {
		return "BlockState [name=" + name + ", properties=" + properties + "]";
	}
}
