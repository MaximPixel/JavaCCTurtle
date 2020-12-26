package net.turtle.print;

import net.turtle.Structure;
import net.turtle.math.BlockPos;
import net.turtle.math.EnumRot;
import net.turtle.turtle.IMoveHelper;
import net.turtle.turtle.ITurtle;

public class ZigzagLayersPrint implements IPrintMethod {

    @Override
    public void print(Structure structure, ITurtle turtle) {
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
                    if (structure.hasBlockAt(pos)) {
                        String blockName = structure.getBlockAt(pos);

                        if (turtle.hasDefineByValue(blockName)) {
                            blockName = turtle.getDefineByValue(blockName) + "*";
                        }

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

        moveHelper.moveHorizontalAt(0, 0);
        moveHelper.turnAt(EnumRot.FORWARD);
    }
}
