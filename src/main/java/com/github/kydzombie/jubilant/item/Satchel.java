package com.github.kydzombie.jubilant.item;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;

public class Satchel extends JubilantItem {
    public Satchel(Identifier identifier) {
        super(identifier, true);
        setMaxStackSize(1);
        setDurability(1);
        setHasSubItems(true);
    }

    @Override
    public ItemInstance use(ItemInstance itemInstance, Level arg2, PlayerBase arg3) {
        if (isOpen(itemInstance)) {
            close(itemInstance);
        } else {
            open(itemInstance);
        }

        return super.use(itemInstance, arg2, arg3);
    }

    private boolean isOpen(ItemInstance itemInstance) {
        return itemInstance.getDamage() > 0;
    }

    private void open(ItemInstance itemInstance) {
        itemInstance.setDamage(1);
    }

    private void close(ItemInstance itemInstance) {
        itemInstance.setDamage(0);
    }


}
