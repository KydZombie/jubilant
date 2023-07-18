package com.github.kydzombie.jubilant.container;

import com.github.kydzombie.jubilant.container.slot.SlotBuffGem;
import com.github.kydzombie.jubilant.container.slot.SlotSatchel;
import com.github.kydzombie.jubilant.container.slot.SlotSpellGem;
import com.github.kydzombie.jubilant.inventory.InventoryGauntlet;
import net.minecraft.container.ContainerBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;

public class ContainerGauntlet extends ContainerBase {
    private InventoryGauntlet gauntletInventory;

    public ContainerGauntlet(PlayerInventory playerInventory, InventoryGauntlet gauntletInventory) {
        this.gauntletInventory = gauntletInventory;

        // Buff Gem
        addSlot(new SlotBuffGem(gauntletInventory, 0, 80, 35));

        // Spell Gems
//        addSlot(new SlotSpellGem(gauntletInventory, 0, 8, 35));
//        addSlot(new SlotSpellGem(gauntletInventory, 1, 42, 35));
//        addSlot(new SlotSpellGem(gauntletInventory, 2, 118, 35));
//        addSlot(new SlotSpellGem(gauntletInventory, 3, 152, 35));

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
        return gauntletInventory.canPlayerUse(player);
    }
}
