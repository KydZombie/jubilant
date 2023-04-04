package com.github.kydzombie.jubilant.item;

import net.modificationstation.stationapi.api.registry.Identifier;

public class Parchment extends JubilantItem {
    public Parchment(Identifier identifier) {
        super(identifier, true);
        setTranslationKey(identifier.toString());
    }
}
