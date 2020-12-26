package net.turtle;

import net.turtle.command.Command;
import net.turtle.math.BlockPos;
import net.turtle.math.TurtlePos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        final ArrayList<TempPath> paths = new ArrayList<>();

        for (Command.MoveCommand cmd : Command.MOVE_COMMANDS) {
            paths.add(new TempPath(Collections.singletonList(cmd.acceptFunction(TurtlePos.ZERO))));
        }

        final ArrayList<BlockPos> targets = new ArrayList<>();

        targets.add(new BlockPos(0, -1, 0));
        targets.add(new BlockPos(6, 0, 0));

        while (true) {
            ArrayList<TempPath> tempPaths = new ArrayList<>(paths);

            for (TempPath path : tempPaths) {
                paths.remove(path);
                for (Command.MoveCommand cmd : Command.MOVE_COMMANDS) {
                    TempPath newPath = path.add(cmd.acceptFunction(path.getLastPos()));
                    if (newPath != null) {
                        if (newPath.isSuccess(targets)) {
                            System.out.println(newPath);
                            return;
                        }
                        paths.add(newPath);
                    }
                }
            }
        }
    }

    public static class TempPath {
        private final List<TurtlePos> positions;

        public TempPath(List<TurtlePos> positions) {
            this.positions = positions;
        }

        public TurtlePos getLastPos() {
            return positions.get(positions.size() - 1);
        }

        public TempPath add(TurtlePos pos) {
            if (positions.contains(pos)) {
                return null;
            }
            ArrayList<TurtlePos> newList = new ArrayList<>(positions);
            newList.add(pos);
            return new TempPath(newList);
        }

        public boolean contains(BlockPos pos) {
            for (TurtlePos turtlePos : positions) {
                if (turtlePos.getPos().equals(pos)) {
                    return true;
                }
            }
            return false;
        }

        public boolean isSuccess(List<BlockPos> targets) {
            for (BlockPos target : targets) {
                if (!contains(target)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public String toString() {
            return "TempPath{" +
                    "positions=" + positions +
                    '}';
        }
    }
}
