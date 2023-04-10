package com.github.kydzombie.jubilant.spell;

import net.minecraft.block.BlockBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;

import java.util.Optional;

public class FireSpell extends BlockPlacingSpell {
    public FireSpell(int cost) {
        super(cost, BlockBase.FIRE, true);
    }

    @Override
    public String getName() {
        return "jubilant:fireSpell";
    }

    @Override
    public Optional<Integer> interactWithEntity(ItemInstance itemInstance, Living entity) {
        entity.fire = 300;
        return Optional.of(cost);
    }
}
