package net.turtle;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;

import net.turtle.command.Commands;
import net.turtle.math.BlockPos;
import net.turtle.print.IPrintMethod;
import net.turtle.print.SpiralLayersPrint;
import net.turtle.print.ZigzagLayersPrint;
import net.turtle.turtle.Turtle;
import net.turtle.turtle.TurtleWriter;

public class Print {
	
	public static void main(String... args) {
		Structure str = ExampleStructures.EXAMPLE;
		
		Commands.getInstance().register();
		
		str.print();

		printTest(str, new ZigzagLayersPrint(), new File("output/zigzag.ts"));
		printTest(str, new SpiralLayersPrint(), new File("output/spiral.ts"));
	}
	
	public static void printTest(Structure str, IPrintMethod printMethod, File file) {
		System.out.println();
		System.out.println(printMethod.getClass().getSimpleName());
		
		TurtleWriter turtleWriter = new TurtleWriter();
		
		str.getRequiredBlocks().forEach((block, count) -> {
			turtleWriter.checkBlocks(block, count);
		});
		
		turtleWriter.define("a", "minecraft:cobblestone");
		printMethod.print(str, turtleWriter);
		
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
		
		System.out.println("Spent fuel: " + turtleWriter.getSpentFuel());
		
		try {
			saveCode(file, turtleWriter.getCommands());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveCode(File file, Collection<String> commands) throws IOException {
		System.out.println("Saving " + file.getName() + " with " + commands.size() + " lines...");
		Files.write(file.toPath(), commands, Charset.forName("UTF-8"));
	}
}
