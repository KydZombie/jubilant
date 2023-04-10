package com.github.kydzombie.jubilant.item;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;

public class Dave extends JubilantBook {
    public static final int PAGE_COUNT_VARIANTS = 5 - 1;
    public Dave(Identifier identifier) {
        super(identifier);
        setDurability(PAGE_COUNT_VARIANTS);
    }

    @Override
    public ItemInstance use(ItemInstance itemInstance, Level level, PlayerBase player) {
        itemInstance.setDamage(Math.min(itemInstance.getDamage() + 1, PAGE_COUNT_VARIANTS));
        return super.use(itemInstance, level, player);
    }
}
