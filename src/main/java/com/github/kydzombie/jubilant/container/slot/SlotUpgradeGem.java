package com.github.kydzombie.jubilant.container.slot;

import com.github.kydzombie.jubilant.item.gem.UpgradeGem;
import net.minecraft.container.slot.Slot;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemInstance;

public class SlotUpgradeGem extends Slot {
    public SlotUpgradeGem(InventoryBase inventoryBase, int slotIndex, int x, int y) {
        super(inventoryBase, slotIndex, x, y);
    }

    @Override
    public boolean canInsert(ItemInstance itemInstance) {
        return itemInstance != null && itemInstance.getType() instanceof UpgradeGem;
    }
}
