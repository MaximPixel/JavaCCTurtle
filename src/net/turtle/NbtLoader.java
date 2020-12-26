package net.turtle;

import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.IntTag;
import net.querz.nbt.tag.ListTag;
import net.querz.nbt.tag.StringTag;
import net.turtle.math.BlockPos;
import net.turtle.math.BlockState;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class NbtLoader {

    public static void main(String[] args) throws IOException {
        File file = new File("input/snowy_tannery_1.nbt");

        CompoundTag tag = (CompoundTag) NBTUtil.read(file, true).getTag();

        getPalette(tag, false);
    }

    public static HashMap<Integer, BlockState> getPalette(CompoundTag structureTag, boolean loadStates) {
        HashMap<Integer, BlockState> palette = new HashMap();

        ListTag<CompoundTag> palletesTags = (ListTag<CompoundTag>) structureTag.getListTag("palette");

        for (CompoundTag paletteTag : palletesTags) {
            String blockName = paletteTag.getString("Name");

            BlockState state;

            if (loadStates && paletteTag.containsKey("Properties")) {
                CompoundTag propertiesTag = paletteTag.getCompoundTag("Properties");

                HashMap<String, String> properties = new HashMap();

                propertiesTag.forEach((name, tag) -> {
                    properties.put(name, ((StringTag) tag).getValue());
                });

                state = new BlockState(blockName, properties);
            } else {
                state = new BlockState(blockName);
            }

            palette.put(palletesTags.indexOf(paletteTag), state);
        }

        return palette;
    }

    public static HashMap<BlockPos, Integer> getPositions(CompoundTag structureTag) {
        HashMap<BlockPos, Integer> poses = new HashMap();

        ListTag<CompoundTag> blocksTag = (ListTag<CompoundTag>) structureTag.getListTag("blocks");

        for (CompoundTag blockTag : blocksTag) {
            ListTag pos = blockTag.getListTag("pos");
            IntTag x = (IntTag) pos.get(0);
            IntTag y = (IntTag) pos.get(1);
            IntTag z = (IntTag) pos.get(2);

            BlockPos pp = new BlockPos(x.asInt(), y.asInt(), z.asInt());

            poses.put(pp, blockTag.getInt("state"));
        }

        return poses;
    }
}
