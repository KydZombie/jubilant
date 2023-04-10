package com.github.kydzombie.jubilant.item;

import com.github.kydzombie.jubilant.Jubilant;
import com.github.kydzombie.jubilant.container.ContainerDave;
import com.github.kydzombie.jubilant.inventory.InventoryDave;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.registry.Identifier;

public class Dave extends JubilantBook {
    public static final int maxPages = 64;
    public Dave(Identifier identifier) {
        super(identifier);
    }

    @Override
    public ItemInstance use(ItemInstance itemInstance, Level level, PlayerBase player) {
        var daveInventory = new InventoryDave(itemInstance);
        GuiHelper.openGUI(
                player,
                Jubilant.MOD_ID.id("openDave"),
                daveInventory,
                new ContainerDave(player.inventory, daveInventory));

        return super.use(itemInstance, level, player);
    }
}
