package net.turtle;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

import net.turtle.turtle.ITurtle;
import net.turtle.turtle.Turtle;
import net.turtle.turtle.TurtleWriter;

public class TurtlePrint {
	
	public static TurtleWriter turtle = new TurtleWriter();
	
	public static final String blockName = "minecraft:cobblestone";
	
	public static void main(String... args) {
		Structure str = ExampleStructures.EXAMPLE;
		
		str.print();
		
		TurtleWriter turtleCmd = new TurtleWriter();
		
		print(str, turtleCmd);
		
		Turtle turtle = new Turtle();
		
		ArrayList<BlockPos> output = new ArrayList();
		
		turtle.setBlocksListener(new IBlocksListener() {
			@Override
			public void addBlock(BlockPos pos) {
				output.add(pos);
			}
		});
		
		turtle.executeCommands(turtleCmd.getCommands());
		
		Structure outputStr = new Structure(output);

		System.out.println("");
		System.out.println("===========");
		System.out.println("Output:");
		System.out.println("===========");
		
		outputStr.print();
		
		File outputFile = new File("code.ts");
		
		System.out.println("Saving code with " + turtleCmd.getCommands().size() + " lines...");
		
		try {
			Files.write(outputFile.toPath(), turtleCmd.getCommands(), Charset.forName("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void print(Structure structure, ITurtle turtle) {
		boolean reverseX = false, reverseZ = false;
		
		for (int y = 0; y < structure.getYSize(); y++) {
			turtle.up();
			
			for (int z = 0; z < structure.getZSize(); z++) {
				int trueZ = reverseZ ? structure.getZSize() - z - 1 : z;
				
				ArrayList<BlockPos> list = structure.getBlocksLine(y, trueZ);
				if (list.isEmpty()) {
					continue;
				}
				
				turtle.moveAt(reverseX ? structure.getXSize() - 1 : 0, trueZ);
				turtle.turnAt(reverseX ? EnumRot.BACK : EnumRot.FORWARD);
				
				for (int x = 0; x < structure.getXSize(); x++) {
					BlockPos pos = new BlockPos(reverseX ? structure.getXSize() - x - 1 : x, y, trueZ);
					if (structure.getBlocks().contains(pos)) {
						turtle.placeDown(blockName);
					}
					if (x != structure.getXSize() - 1) {
						turtle.forward();
					}
				}
				
				reverseX = !reverseX;
			}
			
			reverseZ = !reverseZ;
		}
		
		turtle.moveAt(0, 0);
		turtle.turnAt(EnumRot.FORWARD);
	}
}
