package com.github.kydzombie.jubilant.spell;

import net.minecraft.block.BlockBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;

import java.util.Optional;

public class FireSpell extends Spell {
    public FireSpell(int cost) {
        super(cost);
    }

    private boolean canPlaceFire(Level level, int x, int y, int z) {
        var block = BlockBase.BY_ID[level.getTileId(x, y, z)];
        var meta = level.getTileMeta(x, y, z);

        return block == null || (block != BlockBase.FIRE && !block.isSolid(level, x, y, z, meta) && block.getHardness() != -1.0F);
    }

    @Override
    public Optional<Integer> useOnTile(ItemInstance itemInstance, PlayerBase caster, Level level, int x, int y, int z, int facing) {
        if (!canPlaceFire(level, x, y, z)) switch(facing) {
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

        if (canPlaceFire(level, x, y, z) && level.setTile(x, y, z, BlockBase.FIRE.id)) return Optional.of(cost);

        return Optional.empty();
    }

    @Override
    public Optional<Integer> interactWithEntity(ItemInstance itemInstance, Living entity) {
        entity.fire = 300;
        return Optional.of(cost);
    }

    @Override
    public String getName() {
        return "jubilant:fireSpell";
    }
}
