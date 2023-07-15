package com.github.kydzombie.jubilant.mixin;

import com.github.kydzombie.jubilant.Jubilant;
import com.github.kydzombie.jubilant.item.Gauntlet;
import net.minecraft.client.gui.screen.container.ContainerBase;
import net.minecraft.client.gui.screen.container.PlayerInventory;
import net.minecraft.item.ItemInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PlayerInventory.class)
public abstract class InventoryRenderMixin extends ContainerBase {
    public InventoryRenderMixin(net.minecraft.container.ContainerBase arg) {
        super(arg);
    }

    @ModifyArg(method = "renderContainerBackground(F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/TextureManager;getTextureId(Ljava/lang/String;)I"))
    private String changeRendering(String originalInventoryImage) {
        for (int i = 0; i < minecraft.player.inventory.getInventorySize(); i++) {
            var item = minecraft.player.inventory.getInventoryItem(i);
            if (item != null && item.getType() instanceof Gauntlet) {
                return "/assets/jubilant/stationapi/gui/inventory_with_gauntlet.png";
            }
        }
        return originalInventoryImage;
    }
}
