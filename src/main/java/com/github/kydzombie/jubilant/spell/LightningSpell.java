package com.github.kydzombie.jubilant.spell;

import net.minecraft.entity.Lightning;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;

import java.util.Optional;

public class LightningSpell extends Spell {
    public LightningSpell(int cost) {
        super(cost);
    }

    @Override
    public String getName() {
        return "jubilant:lightning";
    }

    @Override
    public Optional<Integer> useOnTile(ItemInstance itemInstance, PlayerBase caster, Level level, int x, int y, int z, int facing) {
        if (level.spawnEntity(new Lightning(level, x, y, z))) {
            return Optional.of(cost);
        }
        return Optional.empty();
    }
}
