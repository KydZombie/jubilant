package com.github.kydzombie.jubilant.item.gem;

import com.github.kydzombie.jubilant.Jubilant;
import com.github.kydzombie.jubilant.spell.Spell;
import com.github.kydzombie.jubilant.spell.SpellRegistry;
import com.matthewperiut.accessoryapi.api.helper.AccessoryAccess;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.Optional;

public class SpellGem extends Gem {
    public SpellGem(Identifier identifier) {
        super(identifier);
    }

    private static Optional<Spell> getCurrentSpell(ItemInstance itemInstance) {
        return SpellRegistry.getSpell(itemInstance.getStationNBT().getString("spell"));
    }

    @Override
    public ItemInstance use(ItemInstance itemInstance, Level level, PlayerBase player) {
        if (!AccessoryAccess.hasAccessory(player, Jubilant.GAUNTLET)) return super.use(itemInstance, level, player);
        getCurrentSpell(itemInstance).ifPresent((spell) -> {
            if (spell.use(itemInstance, level, player).isPresent()) {
                player.swingHand();
//                System.out.println("Would have cost " + spell.cost);
            }
        });
        return itemInstance;
    }

    @Override
    public boolean useOnTile(ItemInstance itemInstance, PlayerBase player, Level level, int x, int y, int z, int facing) {
        if (!AccessoryAccess.hasAccessory(player, Jubilant.GAUNTLET)) return super.useOnTile(itemInstance, player, level, x, y, z, facing);
        getCurrentSpell(itemInstance).ifPresent((spell) -> {
            if (spell.useOnTile(itemInstance, player, level, x, y, z, facing).isPresent()) {
                player.swingHand();
//                System.out.println("Would have cost " + spell.cost);
            }
        });
        return true;
    }

    @Override
    public void interactWithEntity(ItemInstance itemInstance, Living entity) {
        if (entity instanceof PlayerBase player) {
            if (AccessoryAccess.hasAccessory(player, Jubilant.GAUNTLET)) {
                getCurrentSpell(itemInstance).ifPresent((spell) -> spell.interactWithEntity(itemInstance, entity));
            }
        }
        super.interactWithEntity(itemInstance, entity);

    }

    @Override
    public String[] getTooltip(ItemInstance itemInstance, String originalTooltip) {
        var spell = SpellRegistry.getSpell(itemInstance);
        return spell.map(value -> new String[]{
                originalTooltip,
                value.getTranslatedName()
        }).orElseGet(() -> new String[]{
                originalTooltip,
                "No Spell"
        });
    }
}
