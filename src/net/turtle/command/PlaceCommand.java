package net.turtle.command;

import net.turtle.ExceptionResult;
import net.turtle.IResult;
import net.turtle.UsageResult;
import net.turtle.turtle.ITurtle;

public class PlaceCommand extends Command {
	
	public static final int PLACE = 0, PLACED = 1, PLACEU = 2;
	
	private final int placeType;
	
	public PlaceCommand(String[] names, int placeType) {
		super(names[0], names);
		this.placeType = placeType;
		
		if (placeType != PLACE && placeType != PLACED && placeType != PLACEU) {
			throw new IllegalArgumentException("Unknow place type");
		}
	}

	@Override
	public IResult execute(CommandContext context) {
		String[] args = context.getArguments();
		
		if (args.length >= 1) {
			ITurtle turtle = context.getTurtle();
			
			String blockArg = args[0];
			
			boolean hasDefine = turtle.hasDefinedValue(blockArg);
			
			if (hasDefine || blockArg.startsWith("*") || blockArg.endsWith("*")) {
				if (hasDefine) {
					blockArg = turtle.getDefinedValue(blockArg);
				} else {
					return new ExceptionResult(String.format("\"%s\" not defined", blockArg));
				}
			}
			
			if (placeType == PLACE) {
				turtle.place(blockArg);
			} else if (placeType == PLACED) {
				turtle.placeDown(blockArg);
			} else if (placeType == PLACEU) {
				turtle.placeUp(blockArg);
			}
			
			return IResult.FULL_SUCCESSFUL;
		} else {
			return new UsageResult(this, "<blockname>");
		}
	}
}
