package net.turtle.command;

import net.turtle.BaseResult;
import net.turtle.IResult;
import net.turtle.turtle.ITurtle;

public class PlaceCommand extends Command {
	
	public static final int PLACE = 0, PLACED = 1, PLACEU = 2;
	
	private final int placeType;
	
	public PlaceCommand(String[] names, int placeType) {
		super(names[0], names);
		this.placeType = placeType;
	}

	@Override
	public IResult execute(CommandContext context) {
		String[] args = context.getArguments();
		
		if (args.length >= 1) {
			ITurtle turtle = context.getTurtle();
			
			if (placeType == PLACE) {
				turtle.place(args[0]);
				return IResult.FULL_SUCCESSFUL;
			} else if (placeType == PLACED) {
				turtle.placeDown(args[0]);
				return IResult.FULL_SUCCESSFUL;
			} else if (placeType == PLACEU) {
				turtle.placeUp(args[0]);
				return IResult.FULL_SUCCESSFUL;
			} else {
				return new BaseResult(false, "Unknown place type " + placeType);
			}
		} else {
			return new BaseResult(false, getBaseName() + " <blockname>");
		}
	}
}
