package com.github.kydzombie.jubilant.item;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;

public class Satchel extends JubilantItem {
    public Satchel(Identifier identifier) {
        super(identifier, true);
        setMaxStackSize(1);
    }

    @Override
    public void inventoryTick(ItemInstance itemInstance, Level level, EntityBase entity, int i, boolean bl) {
        if (!isOpen(itemInstance)) return;
        if (entity instanceof PlayerBase player) {
            if (player.getHeldItem() != itemInstance) {
                close(itemInstance);
            }
        }
    }

    @Override
    public ItemInstance use(ItemInstance itemInstance, Level level, PlayerBase player) {
        if (isOpen(itemInstance)) {
            close(itemInstance);
        } else {
            open(itemInstance);
        }

        return super.use(itemInstance, level, player);
    }

    public static boolean isOpen(ItemInstance itemInstance) {
        return itemInstance.getStationNBT().getBoolean("open");
    }

    private static void open(ItemInstance itemInstance) {
        itemInstance.getStationNBT().put("open", true);
    }

    private static void close(ItemInstance itemInstance) {
        itemInstance.getStationNBT().put("open", false);
    }
}
