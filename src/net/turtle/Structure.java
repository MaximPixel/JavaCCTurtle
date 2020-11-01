package net.turtle;

import java.util.ArrayList;
import java.util.HashMap;

import net.turtle.math.BlockPos;

public class Structure {
	
	private HashMap<BlockPos, String> blocks = new HashMap();
	
	private int minX, minY, minZ, maxX, maxY, maxZ;
	
	private Structure() {}
	
	public static final Structure createFromLayers(String[][] layers, String blockName) {
		return createFromLayers(layers, BlockPos.ZERO, blockName);
	}
	
	public static final Structure createFromLayers(String[][] layers, BlockPos offset, String blockName) {
		Structure newStr = new Structure();
		
		for (int a = 0; a < layers.length; a++) {
			String[] layer = layers[a];
			for (int b = 0; b < layer.length; b++) {
				String line = layer[b];
				for (int c = 0; c < line.length(); c++) {
					boolean block = line.charAt(c) != ' ';
					
					if (block) {
						BlockPos pos = new BlockPos(c + offset.getX(), a + offset.getY(), b + offset.getZ());
						
						newStr.blocks.put(pos, blockName);
					}
				}
			}
		}
		newStr.refreshSizes();
		
		return newStr;
	}
	
	public static final Structure createFromLayers(ArrayList<String[]> layers, BlockPos offset, String blockName) {
		Structure newStr = new Structure();
		
		for (int a = 0; a < layers.size(); a++) {
			String[] layer = layers.get(a);
			for (int b = 0; b < layer.length; b++) {
				String line = layer[b];
				for (int c = 0; c < line.length(); c++) {
					boolean block = line.charAt(c) != ' ';
					
					if (block) {
						BlockPos pos = new BlockPos(c + offset.getX(), a + offset.getY(), b + offset.getZ());
						
						newStr.blocks.put(pos, blockName);
					}
				}
			}
		}
		newStr.refreshSizes();
		
		return newStr;
	}
	
	public Structure(HashMap<BlockPos, String> blocks) {
		this(blocks, BlockPos.ZERO);
	}
	
	public Structure(HashMap<BlockPos, String> blocks, BlockPos offset) {
		blocks.forEach(this.blocks::put);
		refreshSizes();
	}
	
	public Structure getZeroStructure() {
		return new Structure(getBlocks(), new BlockPos(-minX, -minY, -minZ));
	}
	
	public void setBlock(BlockPos pos, String blockName) {
		blocks.put(pos, blockName);
		refreshSizes();
	}
	
	public void removeBlock(BlockPos pos) {
		blocks.remove(pos);
		refreshSizes();
	}
	
	public void refreshSizes() {
		minX = Integer.MAX_VALUE;
		minY = Integer.MAX_VALUE;
		minZ = Integer.MAX_VALUE;
		maxX = Integer.MIN_VALUE;
		maxY = Integer.MIN_VALUE;
		maxZ = Integer.MIN_VALUE;
		
		for (BlockPos pos : blocks.keySet()) {
			int x = pos.getX(), y = pos.getY(), z = pos.getZ();
			if (x < minX) {
				minX = x;
			}
			if (y < minY) {
				minY = y;
			}
			if (z < minZ) {
				minZ = z;
			}
			if (x > maxX) {
				maxX = x;
			}
			if (y > maxY) {
				maxY = y;
			}
			if (z > maxZ) {
				maxZ = z;
			}
		}
	}
	
	public HashMap<BlockPos, String> getBlocks() {
		return blocks;
	}
	
	public String getBlockAt(BlockPos pos) {
		return getBlocks().get(pos);
	}

	public int getMinX() {
		return minX;
	}

	public int getMinY() {
		return minY;
	}

	public int getMinZ() {
		return minZ;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getMaxZ() {
		return maxZ;
	}
	
	public int getXSize() {
		return maxX - minX + 1;
	}
	
	public int getYSize() {
		return maxY - minY + 1;
	}
	
	public int getZSize() {
		return maxZ - minZ + 1;
	}

	public void print() {
		System.out.println("============");
		System.out.println(toString());
		for (int a = minY; a <= maxY; a++) {
			System.out.println("");
			for (int b = minZ; b <= maxZ; b++) {
				String line = "";
				for (int c = minX; c <= maxX; c++) {
					BlockPos pos = new BlockPos(c, a, b);
					
					line += blocks.containsKey(pos) ? "X" : " ";
				}
				System.out.println("\"" + line + "\"");
			}
		}
		System.out.println("============");
	}
	
	public ArrayList<BlockPos> getBlocksLine(int y, int z) {
		ArrayList<BlockPos> list = new ArrayList();
		blocks.keySet().forEach(pos -> {
			if (pos.getY() == y && pos.getZ() == z) {
				list.add(pos);
			}
		});
		return list;
	}
	
	public int getBlocksCount() {
		return blocks.size();
	}

	@Override
	public String toString() {
		return "Structure [" + getXSize() + "x" + getYSize() + "x" + getZSize() + " " + getBlocksCount() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blocks == null) ? 0 : blocks.hashCode());
		result = prime * result + maxX;
		result = prime * result + maxY;
		result = prime * result + maxZ;
		result = prime * result + minX;
		result = prime * result + minY;
		result = prime * result + minZ;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Structure other = (Structure) obj;
		if (blocks == null) {
			if (other.blocks != null)
				return false;
		} else if (!blocks.equals(other.blocks))
			return false;
		if (maxX != other.maxX)
			return false;
		if (maxY != other.maxY)
			return false;
		if (maxZ != other.maxZ)
			return false;
		if (minX != other.minX)
			return false;
		if (minY != other.minY)
			return false;
		if (minZ != other.minZ)
			return false;
		return true;
	}
}
