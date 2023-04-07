package com.github.kydzombie.jubilant.spell;

import net.minecraft.entity.Living;
import net.minecraft.item.ItemInstance;

import java.util.Optional;

public class DamageSpell extends Spell {
    private final int damage;
    public DamageSpell(int cost, int damage) {
        super(cost);
        this.damage = damage;
    }

    @Override
    public Optional<Integer> interactWithEntity(ItemInstance itemInstance, Living entity) {
        if (entity.damage(null, damage)) {
            return Optional.of(cost);
        } else {
            return Optional.empty();
        }

    }

    @Override
    public String getName() {
        return "jubilant:damageSpell";
    }
}
