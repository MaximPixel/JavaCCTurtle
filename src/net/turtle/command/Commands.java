package net.turtle.command;

import net.turtle.Constants;

import java.util.HashMap;

public final class Commands {

    public static BaseCommand FORWARD, BACK, UP, DOWN, TURN_LEFT, TURN_RIGHT, TURN_AROUND;
    public static TurnAtCommand TURN_AT;
    public static PlaceCommand PLACE, PLACED, PLACEU;
    public static DefineCommand DEFINE;
    public static CheckCommand CHECK;
    private static Commands instance = new Commands();
    private final HashMap<String, Command> commandsByName;

    private Commands() {
        commandsByName = new HashMap();
    }

    public static Commands getInstance() {
        return instance;
    }

    public void register() {
        FORWARD = new BaseCommand(context -> context.getTurtle().forward(), Constants.FORWARD_NAMES);
        BACK = new BaseCommand(context -> context.getTurtle().back(), Constants.BACK_NAMES);
        UP = new BaseCommand(context -> context.getTurtle().up(), Constants.UP_NAMES);
        DOWN = new BaseCommand(context -> context.getTurtle().down(), Constants.DOWN_NAMES);

        TURN_LEFT = new BaseCommand(context -> context.getTurtle().turnLeft(), Constants.TURN_LEFT_NAMES);
        TURN_RIGHT = new BaseCommand(context -> context.getTurtle().turnRight(), Constants.TURN_RIGHT_NAMES);
        TURN_AROUND = new BaseCommand(context -> context.getTurtle().turnAround(), Constants.TURN_AROUND_NAMES);

        TURN_AT = new TurnAtCommand(Constants.TURN_AT_NAMES);

        PLACE = new PlaceCommand(Constants.PLACE, PlaceCommand.PLACE);
        PLACED = new PlaceCommand(Constants.PLACED, PlaceCommand.PLACED);
        PLACEU = new PlaceCommand(Constants.PLACEU, PlaceCommand.PLACEU);

        DEFINE = new DefineCommand(Constants.DEFINE);

        CHECK = new CheckCommand(Constants.CHECK);
    }

    public void putCommand(String name, Command command) {
        name = name.toLowerCase();

        if (commandsByName.containsKey(name)) {
            throw new IllegalArgumentException("Name collision!");
        } else {
            commandsByName.put(name, command);
        }
    }

    public Command getCommandByName(String name) {
        return commandsByName.get(name.toLowerCase());
    }
}
