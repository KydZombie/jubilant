package com.github.kydzombie.jubilant.event.init;

import com.github.kydzombie.jubilant.gui.GuiDave;
import com.github.kydzombie.jubilant.gui.GuiGauntlet;
import com.github.kydzombie.jubilant.gui.GuiSatchel;
import com.github.kydzombie.jubilant.gui.GuiSatchelUpgrades;
import com.github.kydzombie.jubilant.inventory.InventoryDave;
import com.github.kydzombie.jubilant.inventory.InventoryGauntlet;
import com.github.kydzombie.jubilant.inventory.InventorySatchel;
import com.github.kydzombie.jubilant.inventory.InventorySatchelUpgrades;
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
        event.registry.registerValueNoMessage(MOD_ID.id("openSatchelUpgrades"), BiTuple.of(this::openSatchelUpgrades, InventorySatchelUpgrades::new));
        event.registry.registerValueNoMessage(MOD_ID.id("openGauntlet"), BiTuple.of(this::openGauntlet, InventoryGauntlet::new));
        event.registry.registerValueNoMessage(MOD_ID.id("openDave"), BiTuple.of(this::openDave, InventoryDave::new));
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openSatchel(PlayerBase player, InventoryBase inventoryBase) {
        return new GuiSatchel(player.inventory, (InventorySatchel) inventoryBase);
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openSatchelUpgrades(PlayerBase player, InventoryBase inventoryBase) {
        return new GuiSatchelUpgrades(player.inventory, (InventorySatchelUpgrades) inventoryBase);
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openGauntlet(PlayerBase player, InventoryBase inventoryBase) {
        return new GuiGauntlet(player.inventory, (InventoryGauntlet) inventoryBase);
    }

    @Environment(EnvType.CLIENT)
    public ScreenBase openDave(PlayerBase player, InventoryBase inventoryBase) {
        return new GuiDave(player.inventory, (InventoryDave) inventoryBase);
    }
}
