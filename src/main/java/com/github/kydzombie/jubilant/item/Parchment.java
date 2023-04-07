package com.github.kydzombie.jubilant.item;

import com.github.kydzombie.jubilant.spell.FireSpell;
import com.github.kydzombie.jubilant.spell.Spell;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;

public class Parchment extends JubilantItem {
    Spell spell;
    public Parchment(Identifier identifier) {
        super(identifier, false);
//        spell = new MineSpell(10);
//        spell = new DamageSpell(10, 5);
        spell = new FireSpell(5);
    }

    @Override
    public boolean useOnTile(ItemInstance itemInstance, PlayerBase player, Level level, int x, int y, int z, int facing) {
        if (spell == null) return false;
        if (spell.useOnTile(itemInstance, player, level, x, y, z, facing).isPresent()) {
            itemInstance.count--;
        }
        return super.useOnTile(itemInstance, player, level, x, y, z, facing);
    }

    @Override
    public ItemInstance use(ItemInstance itemInstance, Level level, PlayerBase player) {
        if (spell == null) return itemInstance;
        if (spell.use(itemInstance, level, player).isPresent()) {
            itemInstance.count--;
        }
        return super.use(itemInstance, level, player);
    }

    @Override
    public void interactWithEntity(ItemInstance itemInstance, Living entity) {
        if (spell == null) return;
        if (spell.interactWithEntity(itemInstance, entity).isPresent()) {
            itemInstance.count--;
        } else {
            super.interactWithEntity(itemInstance, entity);
        }
    }

    @Override
    public String[] getTooltip(ItemInstance itemInstance, String originalTooltip) {
        return new String[] {
                originalTooltip,
                String.format(
                        TranslationStorage.getInstance().translate(getTranslationKey() + ".description"),
                        spell.getTranslatedName()
                )
        };
    }
}
