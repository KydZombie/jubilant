package com.github.kydzombie.jubilant.container.slot;

import com.github.kydzombie.jubilant.item.Satchel;
import net.minecraft.container.slot.Slot;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;

public class SlotSatchel extends Slot {
    private final InventoryBase inventory;
    private final int invSlot;
    public SlotSatchel(InventoryBase inventory, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
        this.inventory = inventory;
        this.invSlot = slotIndex;
    }

//    @Override
//    public boolean canInsert(ItemInstance itemInstance) {
//        return itemInstance != null && !(itemInstance.getType() instanceof Satchel);
//    }

    @Override
    public ItemInstance takeItem(int count) {
        var item = inventory.getInventoryItem(invSlot);
        if (item != null && item.getType() instanceof Satchel) {
            return Satchel.isOpen(item) ? null : inventory.takeInventoryItem(invSlot, count);
        }
        return inventory.takeInventoryItem(invSlot, count);
    }
}
