package com.github.kydzombie.jubilant.container;

import com.github.kydzombie.jubilant.container.slot.SlotSatchel;
import com.github.kydzombie.jubilant.container.slot.SlotUpgradeGem;
import com.github.kydzombie.jubilant.inventory.InventorySatchelUpgrades;
import net.minecraft.container.ContainerBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;

public class ContainerSatchelUpgrades extends ContainerBase {
    private InventorySatchelUpgrades satchelInventory;

    public ContainerSatchelUpgrades(PlayerInventory playerInventory, InventorySatchelUpgrades upgradeInventory) {
        this.satchelInventory = upgradeInventory;

        addSlot(new SlotUpgradeGem(upgradeInventory, 0, 8, 35));
        addSlot(new SlotUpgradeGem(upgradeInventory, 1, 42, 35));
        addSlot(new SlotUpgradeGem(upgradeInventory, 2, 80, 35));
        addSlot(new SlotUpgradeGem(upgradeInventory, 3, 118, 35));
        addSlot(new SlotUpgradeGem(upgradeInventory, 4, 152, 35));

        // Player Inventory

        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                var slotNum = column + row * 9 + 9;
                this.addSlot(new SlotSatchel(playerInventory, slotNum, 8 + column * 18, 84 + row * 18));
            }
        }

        for (int column = 0; column < 9; ++column) {
            this.addSlot(new SlotSatchel(playerInventory, column, 8 + column * 18, 142));
        }
    }

    @Override
    public boolean canUse(PlayerBase player) {
        return satchelInventory.canPlayerUse(player);
    }
}
