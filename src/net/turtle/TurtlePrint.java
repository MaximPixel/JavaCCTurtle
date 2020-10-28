package net.turtle;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

public class TurtlePrint {
	
	public static final String[][] pattern = new String[][] {
		new String[] {
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"X      X",
				"X       ",
				"X      X",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"X      X",
				"X       ",
				"X      X",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"X      X",
				"X      X",
				"X      X",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"X      X",
				"X       ",
				"X      X",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"X      X",
				"X       ",
				"X      X",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"X      X",
				"X      X",
				"X      X",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX"
		},
		new String[] {
				"        ",
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX",
				"        "
		},
		new String[] {
				"        ",
				"        ",
				"XXXXXXXX",
				"        ",
				"        "
		},
	};
	
	public static TurtleWriter turtle = new TurtleWriter();
	
	public static final String blockName = "minecraft:cobblestone";
	
	public static void main(String... args) {
		Structure str = Structure.createFromLayers(pattern);
		str = str.getZeroStructure();
		str.print();
		
		TurtleWriter turtleCmd = new TurtleWriter();
		
		boolean reverseX = false, reverseZ = false;
		
		for (int y = 0; y < str.getYSize(); y++) {
			turtleCmd.up();
			
			for (int z = 0; z < str.getZSize(); z++) {
				int trueZ = reverseZ ? str.getZSize() - z - 1 : z;
				
				ArrayList<BlockPos> list = str.getBlocksLine(y, trueZ);
				if (list.isEmpty()) {
					continue;
				}
				
				turtleCmd.moveAt(reverseX ? str.getXSize() - 1 : 0, trueZ);
				turtleCmd.turnAt(reverseX ? EnumRot.BACK : EnumRot.FORWARD);
				
				for (int x = 0; x < str.getXSize(); x++) {
					BlockPos pos = new BlockPos(reverseX ? str.getXSize() - x - 1 : x, y, trueZ);
					if (str.getBlocks().contains(pos)) {
						turtleCmd.placeDown(blockName);
					}
					if (x != str.getXSize() - 1) {
						turtleCmd.forward();
					}
				}
				
				reverseX = !reverseX;
			}
			
			reverseZ = !reverseZ;
		}
		
		turtleCmd.moveAt(0, 0);
		turtleCmd.turnAt(EnumRot.FORWARD);
		
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
}
