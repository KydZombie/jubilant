package com.github.kydzombie.jubilant.spell;

import net.minecraft.block.BlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;

import java.util.Optional;

public abstract class BlockPlacingSpell extends Spell {
    private final BlockBase blockToPlace;
    private final boolean canPlaceInsideSelf;

    public BlockPlacingSpell(int cost, BlockBase block, boolean canPlaceInsideSelf) {
        super(cost);
        this.blockToPlace = block;
        this.canPlaceInsideSelf = canPlaceInsideSelf;
    }

    private boolean canPlaceBlock(Level level, PlayerBase player, int x, int y, int z) {
        var block = BlockBase.BY_ID[level.getTileId(x, y, z)];
        var meta = level.getTileMeta(x, y, z);

        if (!canPlaceInsideSelf && Math.floor(player.x) == x && Math.floor(player.y - player.getEyeHeight()) == y && Math.floor(player.z) == z) return false;

        return block == null || (block != blockToPlace && !block.isSolid(level, x, y, z, meta) && block.getHardness() != -1.0F);
    }

    @Override
    public Optional<Integer> useOnTile(ItemInstance itemInstance, PlayerBase caster, Level level, int x, int y, int z, int facing) {
        if (!canPlaceBlock(level, caster, x, y, z)) switch(facing) {
            case 0 -> y -= 1;
            case 1 -> y += 1;
            case 2 -> z -= 1;
            case 3 -> z += 1;
            case 4 -> x -= 1;
            case 5 -> x += 1;
            default -> {
                return Optional.empty();
            }
        }

        if (canPlaceBlock(level, caster, x, y, z) && level.setTile(x, y, z, blockToPlace.id)) return Optional.of(cost);

        return Optional.empty();
    }
}
