package com.github.kydzombie.jubilant.gui;

import com.github.kydzombie.jubilant.Jubilant;
import com.github.kydzombie.jubilant.container.ContainerDave;
import com.github.kydzombie.jubilant.inventory.InventoryDave;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemInstance;
import org.lwjgl.opengl.GL11;

public class GuiDave extends ContainerBase {
    private final InventoryDave daveInventory;
    private final PlayerInventory playerInventory;

    public GuiDave(PlayerInventory playerInventory, InventoryDave daveInventory) {
        super(new ContainerDave(playerInventory, daveInventory));
        this.daveInventory = daveInventory;
        this.playerInventory = playerInventory;

        containerHeight = (222 - 108) + 6 * 18;
    }

    private boolean checkRecipe() {
        var quill = daveInventory.getInventoryItem(InventoryDave.QUILL_SLOT);
        var parchment = daveInventory.getInventoryItem(InventoryDave.PARCHMENT_SLOT);
        if (quill == null || parchment == null) return false;
        if (quill.getType() == Jubilant.ENCHANTED_QUILL && parchment.getType() == Jubilant.PARCHMENT) {
            return true;
        }
        return false;
    }

    private void updateOutput() {
        if (daveInventory.getInventoryItem(InventoryDave.OUTPUT_SLOT) != null) return;
        var quill = daveInventory.getInventoryItem(InventoryDave.QUILL_SLOT);
        var parchment = daveInventory.getInventoryItem(InventoryDave.PARCHMENT_SLOT);
        if (quill == null || parchment == null) return;
        if (quill.getType() == Jubilant.ENCHANTED_QUILL && quill.getDurability() > 0 && parchment.getType() == Jubilant.PARCHMENT) {
            quill.applyDamage(1, null);
            parchment.count--;
            var inscribedParchment = new ItemInstance(Jubilant.INSCRIBED_PARCHMENT);
            inscribedParchment.getStationNBT().put("spell", "jubilant:fire");
            daveInventory.setInventoryItem(InventoryDave.OUTPUT_SLOT, inscribedParchment);
        } else {
            daveInventory.setInventoryItem(InventoryDave.OUTPUT_SLOT, null);
        }
    }

    @Override
    public void tick() {
        updateOutput();
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
