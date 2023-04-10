package com.github.kydzombie.jubilant.gui;

import com.github.kydzombie.jubilant.container.ContainerDave;
import com.github.kydzombie.jubilant.inventory.InventoryDave;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiDave extends ContainerBase {
    private final InventoryDave daveInventory;
    private final PlayerInventory playerInventory;

    public GuiDave(PlayerInventory playerInventory, InventoryDave daveInventory) {
        super(new ContainerDave(playerInventory, daveInventory));
        this.daveInventory = daveInventory;
        this.playerInventory = playerInventory;

        containerHeight = 222 + 6 * 18;
    }

    @Override
    protected void renderForeground() {
        textManager.drawText(daveInventory.getContainerName(), 8, 6, 0x404040);
        textManager.drawText(playerInventory.getContainerName(), 8, containerHeight - 96 + 2, 0x404040);
    }

    @Override
    protected void renderContainerBackground(float f) {
        int n = minecraft.textureManager.getTextureId("/assets/jubilant/stationapi/gui/dave.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        minecraft.textureManager.bindTexture(n);
        int n2 = (width - containerWidth) / 2;
        int n3 = (height - containerHeight) / 2;
        blit(n2, n3, 0, 0, containerWidth, 6 * 18 + 17);
        blit(n2, n3 + 6 * 18 + 17, 0, 126, containerWidth, 96);
    }

    @Override
    public void onClose() {
        var output = daveInventory.getInventoryItem(2);
        daveInventory.writeData();
        super.onClose();
    }
}
