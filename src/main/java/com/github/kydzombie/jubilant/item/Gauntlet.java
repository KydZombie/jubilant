package com.github.kydzombie.jubilant.item;

import com.github.kydzombie.extendedinventory.TrinketRenderHelper;
import com.github.kydzombie.extendedinventory.item.Trinket;
import com.github.kydzombie.extendedinventory.item.TrinketType;
import com.github.kydzombie.jubilant.Jubilant;
import com.github.kydzombie.jubilant.container.ContainerGauntlet;
import com.github.kydzombie.jubilant.inventory.InventoryGauntlet;
import com.github.kydzombie.jubilant.spell.Spell;
import com.github.kydzombie.jubilant.spell.SpellRegistry;
import net.minecraft.client.render.entity.PlayerRenderer;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.client.color.item.ItemColorProvider;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.Optional;

public class Gauntlet extends JubilantItem implements Trinket {

    public Gauntlet(Identifier identifier) {
        super(identifier, false);
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
    public TrinketType[] getTrinketTypes(ItemInstance item) {
        return new TrinketType[] { TrinketType.GLOVE };
    }

    @Override
    public void renderFirstPerson(PlayerBase player, PlayerRenderer renderer, ItemInstance item, float field_2404, float field_2403, float f, int slot) {
        TrinketRenderHelper.renderGlove(player, renderer, "/assets/jubilant/stationapi/textures/misc/gauntlet.png", field_2404, field_2403, f);
    }
}
