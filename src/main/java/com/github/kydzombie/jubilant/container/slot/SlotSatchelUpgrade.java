package com.github.kydzombie.jubilant.container.slot;

import net.minecraft.container.slot.Slot;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;

public class SlotSatchelUpgrade extends Slot {
    public SlotSatchelUpgrade(InventoryBase inventoryBase, int slotIndex, int x, int y) {
        super(inventoryBase, slotIndex, x, y);
    }

    @Override
    public boolean canInsert(ItemInstance itemInstance) {
        return itemInstance.itemId == ItemBase.apple.id;
//        return super.canInsert(itemInstance);
    }
}
