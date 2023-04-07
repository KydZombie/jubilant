package com.github.kydzombie.jubilant.spell;

import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.HashMap;
import java.util.Optional;

public class SpellRegistry {
    private static HashMap<String, Spell> spells = new HashMap();

    public static void registerSpell(String id, Spell spell) {
        spells.put(id, spell);
    }

    public static Optional<Spell> getSpell(String id) {
        if (spells.containsKey(id)) {
            return Optional.of(spells.get(id));
        } else {
            return Optional.empty();
        }
    }
}
