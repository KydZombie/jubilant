package com.github.kydzombie.jubilant.container.slot;

import com.github.kydzombie.jubilant.Jubilant;
import net.minecraft.container.slot.Slot;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;

public class SlotQuill extends Slot {
    public SlotQuill(InventoryBase inventory, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
    }

    @Override
    public boolean canInsert(ItemInstance itemInstance) {
        return itemInstance != null && itemInstance.getType() != Jubilant.ENCHANTED_QUILL;
    }
}
