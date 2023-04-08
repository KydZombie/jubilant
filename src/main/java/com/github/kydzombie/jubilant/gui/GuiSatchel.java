package com.github.kydzombie.jubilant.gui;

import com.github.kydzombie.jubilant.container.ContainerSatchel;
import com.github.kydzombie.jubilant.inventory.InventorySatchel;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.entity.player.PlayerInventory;

public class GuiSatchel extends ContainerBase {
    public GuiSatchel(PlayerInventory playerInventory, InventorySatchel satchelInventory) {
        super(new ContainerSatchel(playerInventory, satchelInventory));
    }

    @Override
    protected void renderContainerBackground(float f) {

    }
}
