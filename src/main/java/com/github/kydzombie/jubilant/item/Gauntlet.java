package com.github.kydzombie.jubilant.item;

import com.github.kydzombie.jubilant.Jubilant;
import com.github.kydzombie.jubilant.container.ContainerGauntlet;
import com.github.kydzombie.jubilant.inventory.InventoryGauntlet;
import com.matthewperiut.accessoryapi.api.render.AccessoryRenderer;
import com.matthewperiut.accessoryapi.api.render.HasCustomRenderer;
import com.matthewperiut.accessoryapi.api.render.builtin.ConfigurableRenderer;
import com.matthewperiut.accessoryapi.api.render.builtin.GloveRenderer;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.registry.Identifier;

public class Gauntlet extends JubilantAccessory implements HasCustomRenderer {
    private static final ConfigurableRenderer renderer = new GloveRenderer("/assets/jubilant/stationapi/textures/misc/gauntlet.png");

    public Gauntlet(Identifier identifier) {
        super(identifier, false, "gloves");
        setMaxStackSize(1);
    }

    @Override
    public ItemInstance use(ItemInstance itemInstance, Level level, PlayerBase player) {
        var gauntletInventory = new InventoryGauntlet(itemInstance);
        GuiHelper.openGUI(
                player,
                Jubilant.MOD_ID.id("openGauntlet"),
                gauntletInventory,
                new ContainerGauntlet(player.inventory, gauntletInventory)
        );
        return super.use(itemInstance, level, player);
    }

    @Override
    public AccessoryRenderer getRenderer() {
        return renderer;
    }
}
