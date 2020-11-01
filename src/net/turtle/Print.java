package net.turtle;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.BiConsumer;

import net.turtle.command.Commands;
import net.turtle.math.BlockPos;
import net.turtle.math.EnumRot;
import net.turtle.math.Utils;
import net.turtle.turtle.IMoveHelper;
import net.turtle.turtle.ITurtle;
import net.turtle.turtle.Turtle;
import net.turtle.turtle.TurtleWriter;

public class Print {
	
	public static void main(String... args) {
		Structure str = ExampleStructures.EXAMPLE;
		
		printTest(str, Print::printLayers);
	}
	
	public static void printTest(Structure str, BiConsumer<Structure, ITurtle> printMethod) {
		Commands.getInstance().register();
		
		str.print();
		
		HashMap<String, Integer> counts = Utils.countValues(str.getBlocks().values());
		
		counts.forEach((block, count) -> {
			System.out.println(block + " x " + count);
		});
		
		System.out.println("=========");
		
		TurtleWriter turtleWriter = new TurtleWriter();
		
		counts.forEach((block, count) -> {
			turtleWriter.checkBlocks(block, count);
		});
		
		turtleWriter.define("a", "minecraft:cobblestone");
		printMethod.accept(str, turtleWriter);
		
		Turtle turtle = new Turtle();
		
		HashMap<BlockPos, String> output = new HashMap();
		
		turtle.setBlocksListener((pos, block) -> {
			output.put(pos, block);
		});
		
		IResult result = turtle.executeCommands(turtleWriter.getCommands());
		
		if (!result.isSuccessful()) {
			System.out.println(result.getMessage());
			return;
		}
		
		Structure outputStr = new Structure(output);
		
		if (!outputStr.equals(str)) {
			System.out.println("Output structure is not equal to input structure!");
			return;
		}
		
		System.out.println("===========");
		System.out.println("Output:");
		System.out.println("===========");
		
		outputStr.print();
		
		try {
			saveCode(turtleWriter.getCommands());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveCode(Collection<String> commands) throws IOException {
		File outputFile = new File("code.ts");
		
		System.out.println("Saving code with " + commands.size() + " lines...");
		Files.write(outputFile.toPath(), commands, Charset.forName("UTF-8"));
	}
	
	public static void printLayers(Structure structure, ITurtle turtle) {
		boolean reverseX = false, reverseZ = false;
		
		IMoveHelper moveHelper = turtle.getMoveHelper();
		
		for (int y = 0; y < structure.getYSize(); y++) {
			turtle.up();
			
			for (int z = 0; z < structure.getZSize(); z++) {
				int trueZ = reverseZ ? structure.getZSize() - z - 1 : z;
				
				if (structure.getBlocksLine(y, trueZ).isEmpty()) {
					continue;
				}
				
				moveHelper.moveHorizontalAt(reverseX ? structure.getXSize() - 1 : 0, trueZ);
				moveHelper.turnAt(reverseX ? EnumRot.BACK : EnumRot.FORWARD);
				
				for (int x = 0; x < structure.getXSize(); x++) {
					BlockPos pos = new BlockPos(reverseX ? structure.getXSize() - x - 1 : x, y, trueZ);
					if (structure.getBlocks().containsKey(pos)) {
						String blockName = structure.getBlocks().get(pos);
						if (turtle.hasDefineByValue(blockName)) {
							turtle.placeDown(turtle.getDefineByValue(blockName) + "*");
						} else {
							turtle.placeDown(blockName);
						}
					}
					if (x != structure.getXSize() - 1) {
						turtle.forward();
					}
				}
				
				reverseX = !reverseX;
			}
			
			reverseZ = !reverseZ;
		}
		
		moveHelper.moveHorizontalAt(0, 0);
		moveHelper.turnAt(EnumRot.FORWARD);
	}
}
