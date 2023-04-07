package com.github.kydzombie.jubilant.item;

import com.github.kydzombie.jubilant.spell.Spell;
import com.github.kydzombie.jubilant.spell.SpellRegistry;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.Optional;

public class Parchment extends JubilantItem {
    public Parchment(Identifier identifier) {
        super(identifier, false);
    }

    private void setSpell(ItemInstance itemInstance, String spell) {
        itemInstance.getStationNBT().put("spell", spell);
    }

    private Optional<Spell> getSpell(ItemInstance itemInstance) {
        return SpellRegistry.getSpell(itemInstance.getStationNBT().getString("spell"));
    }

    @Override
    public boolean useOnTile(ItemInstance itemInstance, PlayerBase player, Level level, int x, int y, int z, int facing) {
        if (getSpell(itemInstance).isEmpty()) {
            setSpell(itemInstance, "fire");
        }
        if (getSpell(itemInstance).get().useOnTile(itemInstance, player, level, x, y, z, facing).isPresent()) {
            itemInstance.count--;
        }
        return super.useOnTile(itemInstance, player, level, x, y, z, facing);
    }

    @Override
    public ItemInstance use(ItemInstance itemInstance, Level level, PlayerBase player) {
        if (getSpell(itemInstance).isEmpty()) return itemInstance;
        if (getSpell(itemInstance).get().use(itemInstance, level, player).isPresent()) {
            itemInstance.count--;
        }
        return super.use(itemInstance, level, player);
    }

    @Override
    public void interactWithEntity(ItemInstance itemInstance, Living entity) {
        if (getSpell(itemInstance).isEmpty()) return;
        if (getSpell(itemInstance).get().interactWithEntity(itemInstance, entity).isPresent()) {
            itemInstance.count--;
        } else {
            super.interactWithEntity(itemInstance, entity);
        }
    }

    @Override
    public String[] getTooltip(ItemInstance itemInstance, String originalTooltip) {
        var spell = getSpell(itemInstance);
        return new String[] {
                originalTooltip,
                "  " + (spell.isPresent()?
                        String.format(
                                TranslationStorage.getInstance().translate(getTranslationKey() + ".description"),
                                getSpell(itemInstance).get().getTranslatedName()
                        ) :
                        TranslationStorage.getInstance().translate("spell.jubilant:unassignedMessage"))
        };
    }
}
