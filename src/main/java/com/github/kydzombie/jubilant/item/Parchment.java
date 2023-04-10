package com.github.kydzombie.jubilant.item;

import com.github.kydzombie.jubilant.spell.Spell;
import com.github.kydzombie.jubilant.spell.SpellRegistry;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.client.model.item.ItemModelPredicateProvider;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.Optional;

public class Parchment extends JubilantItem {
    public Parchment(Identifier identifier) {
        super(identifier, false);
    }

    private void setSpell(ItemInstance itemInstance, String spell) {
        itemInstance.getStationNBT().put("spell", spell);
    }

    @Override
    public boolean useOnTile(ItemInstance itemInstance, PlayerBase player, Level level, int x, int y, int z, int facing) {
        if (SpellRegistry.getSpell(itemInstance).isEmpty()) {
            setSpell(itemInstance, "jubilant:fire");
        }
        if (SpellRegistry.getSpell(itemInstance).get().useOnTile(itemInstance, player, level, x, y, z, facing).isPresent()) {
            itemInstance.count--;
            return true;
        }
        return false;
    }

    @Override
    public ItemInstance use(ItemInstance itemInstance, Level level, PlayerBase player) {
        if (SpellRegistry.getSpell(itemInstance).isEmpty()) return itemInstance;
        if (SpellRegistry.getSpell(itemInstance).get().use(itemInstance, level, player).isPresent()) {
            itemInstance.count--;
            player.swingHand();
        }

        return super.use(itemInstance, level, player);
    }

    @Override
    public void interactWithEntity(ItemInstance itemInstance, Living entity) {
        if (SpellRegistry.getSpell(itemInstance).isEmpty()) return;
        if (SpellRegistry.getSpell(itemInstance).get().interactWithEntity(itemInstance, entity).isPresent()) {
            itemInstance.count--;
        } else {
            super.interactWithEntity(itemInstance, entity);
        }
    }

    @Override
    public String[] getTooltip(ItemInstance itemInstance, String originalTooltip) {
        var spell = SpellRegistry.getSpell(itemInstance);
        return new String[] {
                originalTooltip,
                "  " + (spell.isPresent()?
                        String.format(
                                TranslationStorage.getInstance().translate(getTranslationKey() + ".description"),
                                spell.get().getTranslatedName()
                        ) :
                        TranslationStorage.getInstance().translate("spell.jubilant:unassignedMessage"))
        };
    }
}
