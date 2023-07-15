package com.github.kydzombie.jubilant.mixin;

import com.github.kydzombie.jubilant.inventory.InventoryGauntlet;
import com.github.kydzombie.jubilant.item.Gauntlet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.InGame;
import net.minecraft.client.render.entity.ItemRenderer;
import net.minecraft.client.util.ScreenScaler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemInstance;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGame.class)
public class HudOverlayMixin extends DrawableHelper {
    @Shadow private static ItemRenderer itemRenderer;
    @Shadow private Minecraft minecraft;

    private void renderGauntletHud(int width, int height, ItemInstance gauntlet, float f) {
        int hotbarOffset = (width / 2 - 91) - 82 - 8;
        zOffset = -90.0f;
        GL11.glBindTexture(3553, minecraft.textureManager.getTextureId("/assets/jubilant/stationapi/gui/gauntlet_hud.png"));
        // Render hotbar
        blit(hotbarOffset, height - 22, 0, 0, 82, 22);

        // Render selector
        blit(hotbarOffset - 1 + gauntlet.getStationNBT().getInt("selectedSpell") * 20, height - 22 - 1, 0, 22, 24, 22);

        // Render items
        var gauntletInventory = new InventoryGauntlet(gauntlet);
        for (int i = 0; i < InventoryGauntlet.SPELL_SLOTS; i++) {
            int x = hotbarOffset + 1 + i * 20 + 2;
            int y = height - 16 - 3;

            var item = gauntletInventory.getInventoryItem(i);

            if (item == null) continue;

            float f2 = (float)item.cooldown - f;
            if (f2 > 0.0f) {
                GL11.glPushMatrix();
                float f3 = 1.0f + f2 / 5.0f;
                GL11.glTranslatef(x + 8, y + 12, 0.0f);
                GL11.glScalef(1.0f / f3, (f3 + 1.0f) / 2.0f, 1.0f);
                GL11.glTranslatef(-(x + 8), -(y + 12), 0.0f);
            }
            itemRenderer.method_1487(this.minecraft.textRenderer, this.minecraft.textureManager, item, x, y);
            if (f2 > 0.0f) {
                GL11.glPopMatrix();
            }
            itemRenderer.method_1488(this.minecraft.textRenderer, this.minecraft.textureManager, item, x, y);
        }
    }

    @Inject(method = "renderHud(FZII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/RenderHelper;disableLighting()V"))
    private void injectGauntletRenderer(float f, boolean flag, int i, int j, CallbackInfo ci) {
        ScreenScaler screenScaler = new ScreenScaler(minecraft.options, minecraft.actualWidth, minecraft.actualHeight);
        int width = screenScaler.getScaledWidth();
        int height = screenScaler.getScaledHeight();

        PlayerInventory playerInventory = minecraft.player.inventory;

        var item = playerInventory.getHeldItem();
        if (item != null) {
            if (item.getType() instanceof Gauntlet) {
                renderGauntletHud(width, height, item, f);
            }
        }
    }
}
