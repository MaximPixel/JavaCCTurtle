package net.turtle.print;

import net.turtle.Structure;
import net.turtle.math.BlockPos;
import net.turtle.math.EnumRot;
import net.turtle.math.Vector2i;
import net.turtle.turtle.IMoveHelper;
import net.turtle.turtle.ITurtle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class SpiralLayersPrint implements IPrintMethod {

    private static Stream<Vector2i> getSpiralStream(int width, int height) {
        ArrayList<Vector2i> poses = getSpiralPoses(width, height);
        return poses.stream();
    }

    private static Stream<Vector2i> getReverseSpiralStream(int width, int height) {
        ArrayList<Vector2i> poses = getSpiralPoses(width, height);
        Collections.reverse(poses);
        return poses.stream();
    }

    private static ArrayList<Vector2i> getSpiralPoses(int width, int height) {
        ArrayList<Vector2i> output = new ArrayList();

        ArrayList<Vector2i> grid = new ArrayList();
        for (int a = 0; a < width; a++) {
            for (int b = 0; b < height; b++) {
                grid.add(new Vector2i(a, b));
            }
        }

        ArrayList<Vector2i> workGrid = new ArrayList(grid);

        EnumRot rot = EnumRot.FORWARD;
        Vector2i vec = new Vector2i(width / 2, height / 2);

        int step = -1;

        workGrid.remove(vec);
        output.add(vec);

        while (!workGrid.isEmpty()) {
            step++;

            for (int i = 0; i < step / 2; i++) {
                vec = vec.offset(rot, 1);
                if (grid.contains(vec)) {
                    workGrid.remove(vec);
                    output.add(vec);
                }
            }

            rot = rot.getRight();
        }

        return output;
    }

    @Override
    public void print(Structure structure, ITurtle turtle) {
        final Supplier<Stream<Vector2i>> spiral = () -> getSpiralStream(structure.getXSize(), structure.getZSize());
        final Supplier<Stream<Vector2i>> reversedSpiral = () -> getReverseSpiralStream(structure.getXSize(), structure.getZSize());

        IMoveHelper moveHelper = turtle.getMoveHelper();

        boolean reverse = false;

        for (int y = 0; y < structure.getYSize(); y++) {
            final int finalY = y;
            turtle.up();

            Consumer<Vector2i> place = vec -> {
                BlockPos pos = new BlockPos(vec.getX(), finalY, vec.getY());

                if (structure.hasBlockAt(pos)) {
                    moveHelper.moveHorizontalAt(vec.getX(), vec.getY());

                    String blockName = structure.getBlockAt(pos);

                    if (turtle.hasDefineByValue(blockName)) {
                        blockName = turtle.getDefineByValue(blockName) + "*";
                    }

                    turtle.placeDown(blockName);
                }
            };

            if (reverse) {
                reversedSpiral.get().forEach(place);
            } else {
                spiral.get().forEach(place);
            }
        }

        moveHelper.moveHorizontalAt(0, 0);
        moveHelper.turnAt(EnumRot.FORWARD);
    }
}
