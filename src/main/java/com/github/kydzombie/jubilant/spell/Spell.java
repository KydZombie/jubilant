package com.github.kydzombie.jubilant.spell;

import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;

import java.util.Optional;

public abstract class Spell {
    public final int cost;

    public Spell(int cost) {
        this.cost = cost;
    }

    public Optional<Integer> useOnTile(ItemInstance itemInstance, PlayerBase caster, Level level, int x, int y, int z, int facing) {
        return Optional.empty();
    }

    public Optional<Integer> use(ItemInstance itemInstance, Level level, PlayerBase caster) {
        return Optional.empty();
    }

    public Optional<Integer> interactWithEntity(ItemInstance itemInstance, Living entity) {
        return Optional.empty();
    }

    public abstract String getTranslationKey();

    public String getTranslatedName() {
        return TranslationStorage.getInstance().translate("spell." + getTranslationKey() + ".name");
    }
}
