package com.github.kydzombie.jubilant.spell;

import net.minecraft.block.BlockBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;

import java.util.Optional;

public class MineSpell extends Spell {
    public MineSpell(int cost) {
        super(cost);
    }

    @Override
    public Optional<Integer> useOnTile(ItemInstance itemInstance, PlayerBase caster, Level level, int x, int y, int z, int facing) {
        var block = BlockBase.BY_ID[level.getTileId(x, y, z)];
        block.drop(level, x, y, z, facing);
        level.setTile(x, y, z, 0);
        return Optional.of(cost);
    }

    @Override
    public String getTranslationKey() {
        return "jubilant:mineSpell";
    }
}
