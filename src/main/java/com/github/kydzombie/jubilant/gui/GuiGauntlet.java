package com.github.kydzombie.jubilant.gui;

import com.github.kydzombie.jubilant.container.ContainerGauntlet;
import com.github.kydzombie.jubilant.inventory.InventoryGauntlet;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiGauntlet extends ContainerBase {
    private final InventoryGauntlet gauntletInventory;
    private final PlayerInventory playerInventory;

    public GuiGauntlet(PlayerInventory playerInventory, InventoryGauntlet upgrades) {
        super(new ContainerGauntlet(playerInventory, upgrades));
        this.playerInventory = playerInventory;
        this.gauntletInventory = upgrades;
    }

    @Override
    public void renderForeground() {
        textManager.drawText(gauntletInventory.getContainerName(), 8, 6, 0x404040);
        textManager.drawText(playerInventory.getContainerName(), 8, containerHeight - 96 + 2, 0x404040);
    }

    @Override
    public void renderContainerBackground(float f) {
//        int n = minecraft.textureManager.getTextureId("/gui/container.png");
        int n = minecraft.textureManager.getTextureId("/assets/jubilant/stationapi/gui/gauntlet_menu.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.textureManager.bindTexture(n);
        int n3 = (width - containerWidth) / 2;
        int n4 = (height - containerHeight) / 2;
        blit(n3, n4, 0, 0, containerWidth, containerHeight);
    }

    @Override
    public void onClose() {
        gauntletInventory.writeData();
        super.onClose();
    }
}
