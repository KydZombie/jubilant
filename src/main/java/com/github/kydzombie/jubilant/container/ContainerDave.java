package com.github.kydzombie.jubilant.container;

import com.github.kydzombie.jubilant.container.slot.*;
import com.github.kydzombie.jubilant.inventory.InventoryDave;
import net.minecraft.container.ContainerBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;

public class ContainerDave extends ContainerBase {
    private InventoryDave daveInventory;

    public ContainerDave(PlayerInventory playerInventory, InventoryDave daveInventory) {
        this.daveInventory = daveInventory;

        addSlot(new SlotParchment(daveInventory, 0, 134, 22));
        addSlot(new SlotQuill(daveInventory, 1, 134, 51));
        addSlot(new SlotDaveOutput(daveInventory, 2, 134, 104));

        // Player Inventory

        int offset = (6 - 4) * 18;

        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                var slotNum = column + row * 9 + 9;
                this.addSlot(new SlotSatchel(playerInventory, slotNum, 8 + column * 18, 103 + row * 18 + offset));
            }
        }

        for (int column = 0; column < 9; ++column) {
            this.addSlot(new SlotSatchel(playerInventory, column, 8 + column * 18, 161 + offset));
        }
    }

    @Override
    public boolean canUse(PlayerBase player) {
        return daveInventory.canPlayerUse(player);
    }
}
