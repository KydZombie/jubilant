package com.github.kydzombie.jubilant.container;

import com.github.kydzombie.jubilant.inventory.InventorySatchel;
import com.github.kydzombie.jubilant.item.Satchel;
import net.minecraft.container.ContainerBase;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;

public class ContainerSatchel extends ContainerBase {
    private InventorySatchel satchelInventory;
    private int rows;

    public ContainerSatchel(PlayerInventory playerInventory, InventorySatchel satchelInventory) {
        this.satchelInventory = satchelInventory;

        this.rows = satchelInventory.getInventorySize() / 9;
        int offset = (rows - 4) * 18;
        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(satchelInventory, column + row * 9, 8 + column * 18, 18 + row * 18));
            }
        }

        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 103 + row * 18 + offset));
            }
        }

        for (int column = 0; column < 9; ++column) {
            this.addSlot(new Slot(playerInventory, column, 8 + column * 18, 161 + offset));
        }

//        for (int column = 0; column < 3; ++column) {
//            for (int row = 0; row < 9; ++row) {
//                this.addSlot(new Slot(playerInventory, row + column * 9 + 9, 8 + row * 18, 84 + column * 18));
//            }
//        }
//
//        for (int column = 0; column < 9; ++column) {
//            this.addSlot(new Slot(playerInventory, column, 8 + column * 18, 142));
//        }
//
//        for (int column = 0; column < 3; ++column) {
//            for (int row = 0; row < 9; ++row) {
//                this.addSlot(new Slot(satchelInventory, row + column * 9 + 9, 8 + row * 18, 84 + column * 18));
//            }
//        }
    }

    @Override
    public boolean canUse(PlayerBase player) {
        return satchelInventory.canPlayerUse(player);
    }

    @Override
    public void onClosed(PlayerBase player) {
        super.onClosed(player);

        var item = player.getHeldItem();
        if (item.getType() instanceof Satchel) {
            Satchel.close(item);
        }
    }
}
