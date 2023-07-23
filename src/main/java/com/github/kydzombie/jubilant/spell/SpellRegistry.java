package com.github.kydzombie.jubilant.spell;

import net.minecraft.item.ItemInstance;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

public class SpellRegistry {
    private static HashMap<String, Spell> spells = new HashMap();

    public static void registerSpell(String id, Spell spell) {
        spells.put(id, spell);
    }

    public static void registerSpell(Spell spell) {
        spells.put(spell.getName(), spell);
    }

    public static Optional<Spell> getSpell(String id) {
        if (spells.containsKey(id)) {
            return Optional.of(spells.get(id));
        } else {
            return Optional.empty();
        }
    }

    public static Optional<Spell> getSpell(ItemInstance itemInstance) {
        if (itemInstance == null) return Optional.empty();
        return getSpell(itemInstance.getStationNBT().getString("spell"));
    }

    public static String getRandomSpellName() {
        var randNumber = new Random().nextInt(spells.keySet().size());
        return (String) spells.keySet().toArray()[randNumber];
    }
}
