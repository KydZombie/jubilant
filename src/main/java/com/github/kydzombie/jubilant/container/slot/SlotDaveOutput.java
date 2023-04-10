package com.github.kydzombie.jubilant.container.slot;

import net.minecraft.container.slot.Slot;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;

public class SlotDaveOutput extends Slot {
    public SlotDaveOutput(InventoryBase inventory, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
    }

    @Override
    public boolean canInsert(ItemInstance itemInstance) {
        return false;
    }
}
