package com.github.kydzombie.jubilant.event.init;

import com.github.kydzombie.jubilant.container.ContainerSatchel;
import com.github.kydzombie.jubilant.gui.GuiSatchel;
import com.github.kydzombie.jubilant.inventory.InventorySatchel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import uk.co.benjiweber.expressions.tuple.BiTuple;

import static com.github.kydzombie.jubilant.Jubilant.MOD_ID;

public class GuiListener {
    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerGuiHandler(GuiHandlerRegistryEvent event) {
        event.registry.registerValueNoMessage(MOD_ID.id("openSatchel"), BiTuple.of(this::openSatchel, InventorySatchel::new));
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openSatchel(PlayerBase player, InventoryBase inventoryBase) {
        return new GuiSatchel(player.inventory, (InventorySatchel) inventoryBase);
    }
}
