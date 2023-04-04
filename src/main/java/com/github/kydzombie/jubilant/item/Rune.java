package com.github.kydzombie.jubilant.item;

import net.modificationstation.stationapi.api.registry.Identifier;

public class Rune extends JubilantItem {

    public Rune(Identifier identifier) {
        super(identifier, true);
        setTranslationKey(identifier.toString());
    }
}
