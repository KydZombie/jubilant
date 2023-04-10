package com.github.kydzombie.jubilant.gui;

import com.github.kydzombie.jubilant.container.ContainerSatchel;
import com.github.kydzombie.jubilant.inventory.InventorySatchel;
import com.github.kydzombie.jubilant.item.Satchel;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.opengl.GL11;

public class GuiSatchel extends ContainerBase {
    private final int rows;
    private final InventorySatchel satchelInventory;
    private final PlayerInventory playerInventory;

    public GuiSatchel(PlayerInventory playerInventory, InventorySatchel satchelInventory) {
        super(new ContainerSatchel(playerInventory, satchelInventory));
        rows = satchelInventory.getInventorySize() / 9;
        this.playerInventory = playerInventory;
        this.satchelInventory = satchelInventory;

        int n = 222;
        int n2 = n - 108;

        containerHeight = n2 + this.rows * 18;
    }

    @Override
    public void renderForeground() {
        textManager.drawText(satchelInventory.getContainerName(), 8, 6, 0x404040);
        textManager.drawText(playerInventory.getContainerName(), 8, containerHeight - 96 + 2, 0x404040);
    }

    @Override
    public void renderContainerBackground(float f) {
//        int n = minecraft.textureManager.getTextureId("/gui/container.png");
        int n = minecraft.textureManager.getTextureId("/assets/jubilant/stationapi/gui/satchel.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.textureManager.bindTexture(n);
        int n2 = (width - containerWidth) / 2;
        int n3 = (height - containerHeight) / 2;
        blit(n2, n3, 0, 0, containerWidth, rows * 18 + 17);
        blit(n2, n3 + rows * 18 + 17, 0, 126, containerWidth, 96);
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
