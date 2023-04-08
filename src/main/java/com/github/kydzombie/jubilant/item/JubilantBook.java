package com.github.kydzombie.jubilant.item;

import net.modificationstation.stationapi.api.registry.Identifier;

public class JubilantBook extends JubilantItem {
    public JubilantBook(Identifier identifier) {
        super(identifier, true);
        setMaxStackSize(1);
    }
}
