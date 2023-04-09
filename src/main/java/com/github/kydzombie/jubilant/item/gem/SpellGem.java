package com.github.kydzombie.jubilant.item.gem;

import com.github.kydzombie.jubilant.spell.SpellRegistry;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;

public class SpellGem extends Gem {
    public SpellGem(Identifier identifier) {
        super(identifier);
    }

    @Override
    public ItemInstance use(ItemInstance itemInstance, Level level, PlayerBase player) {
        itemInstance.getStationNBT().put("spell", SpellRegistry.getRandomSpellName());
        return super.use(itemInstance, level, player);
    }

    @Override
    public String[] getTooltip(ItemInstance itemInstance, String originalTooltip) {
        var spell = SpellRegistry.getSpell(itemInstance);
        if (spell.isPresent()) {
            return new String[] {
                    originalTooltip,
                    spell.get().getTranslatedName()
            };
        }
        return new String[] {
                originalTooltip,
                "No Spell"
        };
    }
}
