package com.github.kydzombie.jubilant.gui;

import com.github.kydzombie.jubilant.container.ContainerSatchelUpgrades;
import com.github.kydzombie.jubilant.inventory.InventorySatchelUpgrades;
import com.github.kydzombie.jubilant.item.Satchel;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiSatchelUpgrades extends ContainerBase {
    private final InventorySatchelUpgrades satchelInventory;
    private final PlayerInventory playerInventory;

    public GuiSatchelUpgrades(PlayerInventory playerInventory, InventorySatchelUpgrades upgrades) {
        super(new ContainerSatchelUpgrades(playerInventory, upgrades));
        this.playerInventory = playerInventory;
        this.satchelInventory = upgrades;
    }

    @Override
    public void renderForeground() {
        this.textManager.drawText(satchelInventory.getContainerName(), 8, 6, 0x404040);
        this.textManager.drawText(playerInventory.getContainerName(), 8, containerHeight - 96 + 2, 0x404040);
    }

    @Override
    public void renderContainerBackground(float f) {
//        int n = this.minecraft.textureManager.getTextureId("/gui/container.png");
        int n = this.minecraft.textureManager.getTextureId("/assets/jubilant/stationapi/gui/satchel_upgrades.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.textureManager.bindTexture(n);
        int n3 = (this.width - this.containerWidth) / 2;
        int n4 = (this.height - this.containerHeight) / 2;
        this.blit(n3, n4, 0, 0, this.containerWidth, this.containerHeight);
    }

    @Override
    public void onClose() {
        satchelInventory.writeData();

        var item = playerInventory.getHeldItem();
        if (item != null && item.getType() instanceof Satchel) {
            Satchel.close(item);
        }
        super.onClose();
    }
}
