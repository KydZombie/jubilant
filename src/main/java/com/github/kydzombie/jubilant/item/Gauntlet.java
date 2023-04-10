package com.github.kydzombie.jubilant.item;

import com.github.kydzombie.jubilant.Jubilant;
import com.github.kydzombie.jubilant.container.ContainerGauntlet;
import com.github.kydzombie.jubilant.inventory.InventoryGauntlet;
import com.github.kydzombie.jubilant.spell.Spell;
import com.github.kydzombie.jubilant.spell.SpellRegistry;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.client.color.item.ItemColorProvider;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.Optional;

public class Gauntlet extends JubilantItem implements ItemColorProvider {
    private static final int MAX_HEX = 0xFFFFFF;

    public Gauntlet(Identifier identifier) {
        super(identifier, false);
        setMaxStackSize(1);
    }

    private static Optional<Spell> getCurrentSpell(ItemInstance itemInstance) {
        var gauntletInventory = new InventoryGauntlet(itemInstance);
        var gem = gauntletInventory.getInventoryItem(itemInstance.getStationNBT().getInt("selectedSpell"));
        return SpellRegistry.getSpell(gem);
    }

    @Override
    public ItemInstance use(ItemInstance itemInstance, Level level, PlayerBase player) {
        getCurrentSpell(itemInstance).ifPresent((spell) -> {
            if (spell.use(itemInstance, level, player).isPresent()) {
                player.swingHand();
//                System.out.println("Would have cost " + spell.cost);
            }
        });
        return super.use(itemInstance, level, player);
    }

    @Override
    public boolean useOnTile(ItemInstance itemInstance, PlayerBase player, Level level, int x, int y, int z, int facing) {
        getCurrentSpell(itemInstance).ifPresent((spell) -> {
            if (spell.useOnTile(itemInstance, player, level, x, y, z, facing).isPresent()) {
                player.swingHand();
//                System.out.println("Would have cost " + spell.cost);
            }
        });
        return super.useOnTile(itemInstance, player, level, x, y, z, facing);
    }

    @Override
    public void interactWithEntity(ItemInstance itemInstance, Living entity) {
        getCurrentSpell(itemInstance).ifPresent((spell) -> spell.interactWithEntity(itemInstance, entity));
    }

    private ItemInstance getCurrentSpellGem(ItemInstance gauntlet) {
        var gauntletInventory = new InventoryGauntlet(gauntlet);
        return gauntletInventory.getInventoryItem(gauntlet.getStationNBT().getInt("selectedSpell"));
    }

    @Override
    public int getColor(ItemInstance itemInstance, int layer) {
        if (layer < 3) {
            return Jubilant.SPELL_GEM.getColor(getCurrentSpellGem(itemInstance), layer);
        }
        return MAX_HEX;
    }
}
