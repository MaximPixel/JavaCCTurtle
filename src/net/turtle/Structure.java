package net.turtle;

import java.util.ArrayList;

public class Structure {
	
	private ArrayList<BlockPos> blocks = new ArrayList();
	
	private int minX, minY, minZ, maxX, maxY, maxZ;
	
	private Structure() {}
	
	public static final Structure createFromLayers(String[][] layers) {
		return createFromLayers(layers, BlockPos.ZERO);
	}
	
	public static final Structure createFromLayers(String[][] layers, BlockPos offset) {
		Structure newStr = new Structure();
		
		for (int a = 0; a < layers.length; a++) {
			String[] layer = layers[a];
			for (int b = 0; b < layer.length; b++) {
				String line = layer[b];
				for (int c = 0; c < line.length(); c++) {
					boolean block = line.charAt(c) != ' ';
					
					if (block) {
						BlockPos pos = new BlockPos(c + offset.getX(), a + offset.getY(), b + offset.getZ());
						
						if (!newStr.blocks.contains(pos)) {
							newStr.blocks.add(pos);
						}
					}
				}
			}
		}
		newStr.refreshSizes();
		
		return newStr;
	}
	
	public static final Structure createFromLayers(ArrayList<String[]> layers, BlockPos offset) {
		Structure newStr = new Structure();
		
		for (int a = 0; a < layers.size(); a++) {
			String[] layer = layers.get(a);
			for (int b = 0; b < layer.length; b++) {
				String line = layer[b];
				for (int c = 0; c < line.length(); c++) {
					boolean block = line.charAt(c) != ' ';
					
					if (block) {
						BlockPos pos = new BlockPos(c + offset.getX(), a + offset.getY(), b + offset.getZ());
						
						if (!newStr.blocks.contains(pos)) {
							newStr.blocks.add(pos);
						}
					}
				}
			}
		}
		newStr.refreshSizes();
		
		return newStr;
	}
	
	public Structure(ArrayList<BlockPos> blocks) {
		this(blocks, BlockPos.ZERO);
	}
	
	public Structure(ArrayList<BlockPos> blocks, BlockPos offset) {
		for (BlockPos b : blocks) {
			this.blocks.add(b.add(offset));
		}
		refreshSizes();
	}
	
	public Structure getZeroStructure() {
		return new Structure(getBlocks(), new BlockPos(-minX, -minY, -minZ));
	}
	
	public void addBlock(BlockPos pos) {
		if (!blocks.contains(pos)) {
			blocks.add(pos);
		}
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
		
		for (BlockPos pos : blocks) {
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
	
	public ArrayList<BlockPos> getBlocks() {
		return blocks;
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
		System.out.println("Structure " + getXSize() + " " + getYSize() + " " + getZSize() + " " + getBlocksCount());
		for (int a = minY; a <= maxY; a++) {
			System.out.println("");
			for (int b = minZ; b <= maxZ; b++) {
				String line = "";
				for (int c = minX; c <= maxX; c++) {
					BlockPos pos = new BlockPos(c, a, b);
					
					line += blocks.contains(pos) ? "X" : " ";
				}
				System.out.println("\"" + line + "\"");
			}
		}
		System.out.println("============");
	}
	
	public ArrayList<BlockPos> getBlocksLine(int y, int z) {
		ArrayList<BlockPos> list = new ArrayList();
		for (BlockPos p : blocks) {
			if (p.getY() == y && p.getZ() == z) {
				list.add(p);
			}
		}
		return list;
	}
	
	public int getBlocksCount() {
		return blocks.size();
	}

	@Override
	public String toString() {
		return "Structure [minX=" + minX + ", minY=" + minY + ", minZ=" + minZ + ", maxX=" + maxX
				+ ", maxY=" + maxY + ", maxZ=" + maxZ + "]";
	}
}
